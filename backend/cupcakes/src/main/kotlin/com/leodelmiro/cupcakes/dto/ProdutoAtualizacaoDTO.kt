package com.leodelmiro.cupcakes.dto

import java.math.BigDecimal

data class ProdutoAtualizacaoDTO(
        val nome: String? = null,
        val quantidade: Int? = null,
        val preco: BigDecimal? = null,
        val descricao: String? = null,
        val sabores: List<Long>? = null,
        val foto: FotoDTO? = null
) {
    init {
        preco?.let { if (preco < BigDecimal(0)) throw IllegalArgumentException("Preço deve ser maior que 0") }
    }
}