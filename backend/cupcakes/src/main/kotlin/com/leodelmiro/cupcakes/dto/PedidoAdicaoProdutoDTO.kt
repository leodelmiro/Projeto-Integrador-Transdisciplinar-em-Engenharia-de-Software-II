package com.leodelmiro.cupcakes.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.leodelmiro.cupcakes.controllers.validacoes.ProdutoValido
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive

data class PedidoAdicaoProdutoDTO(
        @field:JsonProperty("produto_id")
        @field:NotNull(message = "Produto não pode ser nulo")
        @field:Positive(message = "Produto deve ter Id válido")
        @field:ProdutoValido
        val produtoId: Long,
        @field:NotNull(message = "Quantidade não pode ser nula")
        @field:Positive(message = "Quantidade deve ser maior que 0")
        val quantidade: Int,
)