package com.leodelmiro.cupcakes.model

import javax.persistence.*

@Entity(name = "tb_role")
class Role(
        @field:Id
        @field:GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,
        @field:Column(nullable = false)
        val autoridade: String
)
