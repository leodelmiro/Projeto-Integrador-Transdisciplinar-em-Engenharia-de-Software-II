package com.leodelmiro.cupcakes.dto

import com.leodelmiro.cupcakes.model.Produto
import com.leodelmiro.cupcakes.model.Sabor
import java.math.BigDecimal

data class ProdutoResponseDTO(
        val id: Long? = null,
        val nome: String,
        val quantidade: Int,
        val preco: BigDecimal,
        val descricao: String,
        val sabores: Set<Sabor>,
        val foto: String
) {
    constructor(entidade: Produto) : this(
            id = entidade.id,
            nome = entidade.nome,
            quantidade = entidade.quantidade,
            preco = entidade.preco,
            descricao = entidade.descricao,
            sabores = entidade.sabores,
            foto = entidade.foto.url
    )
}
