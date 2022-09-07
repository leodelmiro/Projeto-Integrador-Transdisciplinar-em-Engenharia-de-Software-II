package com.leodelmiro.cupcakes.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.leodelmiro.cupcakes.model.Pedido
import com.leodelmiro.cupcakes.model.Status
import java.math.BigDecimal
import java.time.LocalDateTime

class PedidoResponseDTO(
        val id: Long? = null,
        val status: Status,
        val valor: BigDecimal,
        val code: String,
        @field:JsonProperty("usuario")
        val usuarioEmail: String,
        val produtos: List<ProdutoResponseDTO>,
        @field:JsonProperty("horario_compra")
        val horarioDeCompra: LocalDateTime = LocalDateTime.now(),
        @field:JsonProperty("ultima_atualizacao")
        val atualizadoEm: LocalDateTime? = null
) {
    constructor(entidade: Pedido) : this(
            id = entidade.id,
            status = entidade.status,
            valor = entidade.valor,
            code = entidade.code,
            usuarioEmail = entidade.usuario.email,
            produtos = entidade.produtos.map { produto -> ProdutoResponseDTO(produto) },
            horarioDeCompra = entidade.horarioDeCompra,
            atualizadoEm = entidade.atualizadoEm
    )
}
