package com.leodelmiro.cupcakes.services

import com.leodelmiro.cupcakes.dto.*
import com.leodelmiro.cupcakes.dto.PedidoCriacaoDTO.Companion.toEntidade
import com.leodelmiro.cupcakes.repositories.PedidoRepository
import com.leodelmiro.cupcakes.repositories.UsuarioRepository
import com.leodelmiro.cupcakes.services.exceptions.RecursoNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.transaction.annotation.Transactional

class PedidoService(
        @Autowired val pedidoRepository: PedidoRepository,
        @Autowired val usuarioRepository: UsuarioRepository,
        @Autowired val produtoService: ProdutoService
) {

    @Transactional(readOnly = true)
    fun encontrarPorId(id: Long): PedidoResponseDTO =
            pedidoRepository.findById(id)
                    .orElseThrow { RecursoNotFoundException("Id não encontrado de id: $id") }
                    .let { pedido -> PedidoResponseDTO(pedido) }

    @Transactional
    fun inserir(dto: PedidoCriacaoDTO): PedidoResponseDTO =
            dto.toEntidade(usuarioRepository, produtoService).apply {
                pedidoRepository.save(this)
            }.let { entidade ->
                PedidoResponseDTO(entidade)
            }

//    fun encontrarPedidoDoCarrinho(id: Long): CarrinhoDeComprasDTO {
//
//    }

//
//    @Transactional
//    fun atualizar(id: Long, dto: ProdutoAtualizacaoDTO): ProdutoResponseDTO? =
//            try {
//                produtoRepository.getReferenceById(id).apply {
//                    updateCamposNaoNulos(dto)
//                    produtoRepository.save(this)
//                }.let { entidade ->
//                    ProdutoResponseDTO(entidade)
//                }
//            } catch (e: EntityNotFoundException) {
//                throw RecursoNotFoundException("Id não encontrado de id: $id")
//            }
//
//    private fun Produto.updateCamposNaoNulos(dto: ProdutoAtualizacaoDTO) {
//        dto.nome?.let { this.nome = it }
//        dto.quantidade?.let { this.quantidade = it }
//        dto.preco?.let { this.preco = it }
//        dto.descricao?.let { this.descricao = it }
//        dto.sabores?.let {
//            this.sabores = dto.sabores.map { saborId -> saborRepository.getReferenceById(saborId) }.toSet()
//        }
//        dto.fotos?.map { foto -> this.addFoto(Foto(url = foto.url, produto = this)) }
//    }
//
//
//    @Transactional
//    fun deletar(id: Long) =
//            try {
//                produtoRepository.deleteById(id)
//            } catch (e: EmptyResultDataAccessException) {
//                throw RecursoNotFoundException("Id não encontrado de id: $id")
//            } catch (e: DataIntegrityViolationException) {
//                throw DatabaseException("Violação de integridade do banco")
//            }
}

