package com.leodelmiro.cupcakes.dto

import com.fasterxml.jackson.annotation.JsonIgnore
import com.leodelmiro.cupcakes.model.Foto
import com.leodelmiro.cupcakes.model.Produto
import com.leodelmiro.cupcakes.model.Sabor
import java.math.BigDecimal

class ProdutoResponseDTO(
        @field:JsonIgnore
        val id: Long? = null,
        val nome: String,
        val quantidade: Int,
        val preco: BigDecimal,
        val descricao: String,
        val sabores: Set<Sabor>,
        val fotos: List<Foto>
) {
    constructor(entidade: Produto) : this(
            nome = entidade.nome,
            quantidade = entidade.quantidade,
            preco = entidade.preco,
            descricao = entidade.descricao,
            sabores = entidade.sabores,
            fotos = entidade.fotos,
    )
}
