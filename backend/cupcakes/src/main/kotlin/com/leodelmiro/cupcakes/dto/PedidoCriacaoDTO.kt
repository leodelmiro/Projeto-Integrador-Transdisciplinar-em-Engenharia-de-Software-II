package com.leodelmiro.cupcakes.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.leodelmiro.cupcakes.model.Pedido
import com.leodelmiro.cupcakes.model.ProdutoPedido
import com.leodelmiro.cupcakes.model.Status
import com.leodelmiro.cupcakes.services.ProdutoService
import com.leodelmiro.cupcakes.services.UsuarioService
import java.math.BigDecimal
import java.util.*
import javax.validation.Valid
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive

class PedidoCriacaoDTO(
        // TODO CRIAR VALIDAÇÃO DE STATUS VALIDO
        @field:NotBlank(message = "Status não pode ser branco ou nulo")
        val status: String,
        @field:JsonProperty("usuario_id")
        @field:NotNull(message = "Usuário não pode ser nulo")
        @field:Positive(message = "Usuário deve ter Id válido")
        val usuarioId: Long,
        val produtos: List<@Valid ProdutoEmPedidoDTO> = mutableListOf(),
) {
    companion object {
        fun PedidoCriacaoDTO.toEntidade(usuarioService: UsuarioService, produtoService: ProdutoService) =
                Pedido(
                        status = Status.valueOf(status),
                        valor = valorTotalPedido(produtoService),
                        code = UUID.randomUUID().toString(), //Código de Barra Fake
                        usuario = usuarioService.encontrarEntidadePorId(this.usuarioId),
                        produtos = mutableListOf()
                ).apply {
                    produtos.addAll(this@toEntidade.produtos.map { produto ->
                        ProdutoPedido(produto = produtoService.encontrarEntidadePorId(produto.id),
                                quantidade = produto.quantidade,
                                pedido = this
                        )
                    })
                }

        private fun PedidoCriacaoDTO.valorTotalPedido(produtoService: ProdutoService): BigDecimal =
                this.produtos
                        .map { produto -> valorDoProdutoNoPedido(produtoService, produto) }
                        .reduce { soma: BigDecimal, precoProduto ->
                            soma + precoProduto
                        }

        private fun valorDoProdutoNoPedido(produtoService: ProdutoService, produto: ProdutoEmPedidoDTO) =
                produtoService.encontrarPrecoProdutoPorId(produto.id) * produto.quantidade.toBigDecimal()
    }
}