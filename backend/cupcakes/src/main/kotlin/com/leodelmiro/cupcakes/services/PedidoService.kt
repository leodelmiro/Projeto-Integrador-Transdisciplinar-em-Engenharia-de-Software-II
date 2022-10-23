package com.leodelmiro.cupcakes.services

import com.leodelmiro.cupcakes.dto.PedidoCriacaoDTO
import com.leodelmiro.cupcakes.dto.PedidoCriacaoDTO.Companion.toEntidade
import com.leodelmiro.cupcakes.dto.PedidoResponseDTO
import com.leodelmiro.cupcakes.dto.ProdutoAtualizacaoDTO
import com.leodelmiro.cupcakes.model.MetodoPagamento
import com.leodelmiro.cupcakes.model.Pedido
import com.leodelmiro.cupcakes.model.Produto
import com.leodelmiro.cupcakes.model.Status
import com.leodelmiro.cupcakes.repositories.PedidoRepository
import com.leodelmiro.cupcakes.services.exceptions.RecursoNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityNotFoundException

@Service
class PedidoService(
        @Autowired val pedidoRepository: PedidoRepository,
        @Autowired val pagamentoService: PagamentoService,
        @Autowired val usuarioService: UsuarioService,
        @Autowired val produtoService: ProdutoService,
        @Autowired val authService: AuthService
) {

    @Transactional(readOnly = true)
    fun encontrarPorId(id: Long): PedidoResponseDTO =
            pedidoRepository.findById(id)
                    .orElseThrow { RecursoNotFoundException("Id não encontrado de id: $id") }
                    .let { pedido -> PedidoResponseDTO(pedido, produtoService) }

    @Transactional(readOnly = true)
    fun encontrarTodosPorUsuario(id: Long, numero: Long?, pagina: PageRequest): List<PedidoResponseDTO> =
            authService.validataSeDeleOuAdmin(id).run {
                pedidoRepository.findAllByUsuarioId(id, numero, pagina).map { PedidoResponseDTO(it, produtoService) }
            }

    @Transactional
    fun criar(dto: PedidoCriacaoDTO): PedidoResponseDTO =
            authService.validataSeDeleOuAdmin(dto.usuarioId).run {
                dto.toEntidade(usuarioService, produtoService).apply {
                    baixaProdutoEstoque(this, produtoService)
                    pedidoRepository.save(this)
                }.let { entidade ->
                    PedidoResponseDTO(entidade, produtoService)
                }
            }

    @Transactional
    fun finalizar(id: Long, metodoPagamento: String): PedidoResponseDTO? =
            try {
                val metodoPagamentoEnum = MetodoPagamento.valueOf(metodoPagamento.uppercase())
                pagamentoService.pagar(metodoPagamentoEnum)
                pedidoRepository.getReferenceById(id).apply {
                    authService.validataSeDeleOuAdmin(this.usuario.id ?: 0)
                    this.status = Status.PAGO
                    pedidoRepository.save(this)
                }.let { entidade ->
                    PedidoResponseDTO(entidade, produtoService)
                }
            } catch (e: EntityNotFoundException) {
                throw RecursoNotFoundException("Id não encontrado de id: $id")
            } catch (e: NoSuchElementException) {
                throw IllegalArgumentException("Metodo de pagamento inexistente: $metodoPagamento")
            }

    @Transactional
    fun cancelar(id: Long): PedidoResponseDTO? =
            try {
                pedidoRepository.getReferenceById(id).apply {
                    authService.validataSeDeleOuAdmin(this.usuario.id ?: 0)
                    this.status = Status.CANCELADO
                    devolverEstoque(this, produtoService)
                    pedidoRepository.save(this)
                }.let { entidade ->
                    PedidoResponseDTO(entidade, produtoService)
                }
            } catch (e: EntityNotFoundException) {
                throw RecursoNotFoundException("Id não encontrado de id: $id")
            }

    private fun baixaProdutoEstoque(pedido: Pedido, produtoService: ProdutoService) =
            pedido.produtos.forEach { produtoEmPedido ->
                produtoService.atualizar(
                        produtoEmPedido.produto.id!!,
                        ProdutoAtualizacaoDTO(
                                quantidade = with(produtoService.encontrarEntidadePorId(produtoEmPedido.produto.id!!)) {
                                    return@with this.quantidade - encontraProdutoPorPedido(pedido).quantidade
                                }
                        )
                )
            }


    private fun devolverEstoque(pedido: Pedido, produtoService: ProdutoService) =
            pedido.produtos.forEach { produtoEmPedido ->
                produtoService.atualizar(
                        produtoEmPedido.produto.id!!,
                        ProdutoAtualizacaoDTO(
                                quantidade = with(produtoService.encontrarEntidadePorId(produtoEmPedido.produto.id!!)) {
                                    return@with this.quantidade + encontraProdutoPorPedido(pedido).quantidade
                                }
                        )
                )
            }

    private fun Produto.encontraProdutoPorPedido(pedido: Pedido) =
            pedido.produtos.first { produtoPedido -> produtoPedido.produto.id == this.id }
}

