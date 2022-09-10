package com.leodelmiro.cupcakes.dto

import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive

data class ProdutoEmPedidoDTO(
        // TODO VALIDAR ID PRODUTO
        @field:NotNull(message = "Id não pode ser nulo")
        @field:Positive(message = "Id deve ser maior que 0")
        val id: Long,
        @field:NotNull(message = "Quantidade não pode ser nula")
        @field:Positive(message = "Quantidade deve ser maior que 0")
        val quantidade: Int
)