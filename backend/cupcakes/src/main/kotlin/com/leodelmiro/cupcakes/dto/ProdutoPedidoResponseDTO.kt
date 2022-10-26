package com.leodelmiro.cupcakes.dto

import com.leodelmiro.cupcakes.model.Produto
import com.leodelmiro.cupcakes.model.Sabor
import java.math.BigDecimal

data class ProdutoPedidoResponseDTO(
        val nome: String,
        val quantidade: Int,
        val preco: BigDecimal,
        val sabores: Set<Sabor>,
        val fotos: String
) {
    constructor(produto: Produto, quantidade: Int) : this(
            nome = produto.nome,
            quantidade = quantidade,
            preco = produto.preco,
            sabores = produto.sabores,
            fotos = produto.foto.url
    )
}
