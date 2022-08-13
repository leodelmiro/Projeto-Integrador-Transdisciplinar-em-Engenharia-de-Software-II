package com.leodelmiro.cupcakes.model

import javax.persistence.*

@Entity
class Role(
        @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,
        @field:Column(nullable = false)
        val autoridade: String
)
