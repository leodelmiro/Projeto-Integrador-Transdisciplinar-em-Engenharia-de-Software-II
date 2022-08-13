package com.leodelmiro.cupcakes.model

import javax.persistence.*

@Entity
class Sabor(
        @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,
        @field:Column(nullable = false)
        val nome: String
)
