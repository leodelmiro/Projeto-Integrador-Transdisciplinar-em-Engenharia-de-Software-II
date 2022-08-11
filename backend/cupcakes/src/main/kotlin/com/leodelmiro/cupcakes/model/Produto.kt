package com.leodelmiro.cupcakes.model

import java.math.BigDecimal

class Produto(
        id: Long? = null,
        nome: String,
        quantidade: Int,
        preco: BigDecimal,
        descricao: String,
        sabor: Sabor,
        foto: Foto
)