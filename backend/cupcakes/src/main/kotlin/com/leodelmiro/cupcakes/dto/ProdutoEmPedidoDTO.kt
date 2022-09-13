package com.leodelmiro.cupcakes.dto

import com.leodelmiro.cupcakes.controllers.validacoes.ProdutoEstoque
import com.leodelmiro.cupcakes.controllers.validacoes.ProdutoValido
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive

@ProdutoEstoque
data class ProdutoEmPedidoDTO(
        @field:NotNull(message = "Id não pode ser nulo")
        @field:Positive(message = "Id deve ser maior que 0")
        @field:ProdutoValido
        val id: Long,
        @field:NotNull(message = "Quantidade não pode ser nula")
        @field:Positive(message = "Quantidade deve ser maior que 0")
        val quantidade: Int
)