package com.leodelmiro.cupcakes.model

import java.math.BigDecimal
import javax.persistence.*

@Entity
class Produto(
        @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,
        @field:Column(nullable = false)
        val nome: String,
        @field:Column(nullable = false)
        val quantidade: Int,
        @field:Column(nullable = false)
        val preco: BigDecimal,
        @field:Column(nullable = false)
        val descricao: String,
        @field:Column(nullable = false)
        val sabor: Sabor,
        @field:Column(nullable = false)
        val foto: Foto
)