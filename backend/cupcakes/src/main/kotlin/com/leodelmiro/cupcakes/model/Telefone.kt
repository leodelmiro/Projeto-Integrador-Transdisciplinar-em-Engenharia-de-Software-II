package com.leodelmiro.cupcakes.model

import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
data class Telefone(
        @field:Column(nullable = false)
        val ddd: String,
        @field:Column(nullable = false)
        val numero: String
)
