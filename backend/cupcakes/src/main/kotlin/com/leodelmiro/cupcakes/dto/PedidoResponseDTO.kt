package com.leodelmiro.cupcakes.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.leodelmiro.cupcakes.model.Pedido
import com.leodelmiro.cupcakes.model.Status
import com.leodelmiro.cupcakes.services.ProdutoService
import java.math.BigDecimal
import java.time.LocalDateTime

data class PedidoResponseDTO(
        val id: Long? = null,
        val status: Status,
        val valor: BigDecimal,
        val code: String,
        @field:JsonProperty("usuario")
        val usuarioEmail: String,
        val produtos: List<ProdutoPedidoResponseDTO>,
        @field:JsonProperty("horario_compra")
        val horarioDeCompra: LocalDateTime = LocalDateTime.now(),
        @field:JsonProperty("ultima_atualizacao")
        val atualizadoEm: LocalDateTime? = null
) {
    constructor(entidade: Pedido, produtoService: ProdutoService) : this(
            id = entidade.id,
            status = entidade.status,
            valor = entidade.valor,
            code = entidade.code,
            usuarioEmail = entidade.usuario.email,
            produtos = entidade.produtos.map { produtoPedido ->
                ProdutoPedidoResponseDTO(
                        produtoService.encontrarEntidadePorId(produtoPedido.produto.id!!),
                        produtoPedido.quantidade)
            },
            horarioDeCompra = entidade.horarioDeCompra,
            atualizadoEm = entidade.atualizadoEm
    )
}
