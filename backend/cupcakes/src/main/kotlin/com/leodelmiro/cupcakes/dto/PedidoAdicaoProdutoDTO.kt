package com.leodelmiro.cupcakes.dto

import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive

class PedidoAdicaoProdutoDTO(
        @field:JsonProperty("produto_id")
        @field:NotNull(message = "Produto não pode ser nulo")
        @field:Positive(message = "Produto deve ter Id válido")
        val produtoId: Long,
        @field:NotNull(message = "Quantidade não pode ser nula")
        @field:Positive(message = "Quantidade deve ser maior que 0")
        val quantidade: Int,
)