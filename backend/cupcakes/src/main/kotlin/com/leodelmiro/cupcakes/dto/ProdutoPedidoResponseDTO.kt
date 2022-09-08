package com.leodelmiro.cupcakes.dto

import com.leodelmiro.cupcakes.model.Produto
import com.leodelmiro.cupcakes.model.Sabor
import java.math.BigDecimal

class ProdutoPedidoResponseDTO(
        val nome: String,
        val quantidade: Int,
        val preco: BigDecimal,
        val sabores: Set<Sabor>,
        val fotos: List<String>
) {
    constructor(produto: Produto, quantidade: Int) : this(
            nome = produto.nome,
            quantidade = quantidade,
            preco = produto.preco,
            sabores = produto.sabores,
            fotos = produto.fotos.map { foto -> foto.url }
    )
}