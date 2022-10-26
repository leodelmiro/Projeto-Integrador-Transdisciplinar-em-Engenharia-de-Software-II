package com.leodelmiro.cupcakes.model

import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
data class Foto(
        @field:Column(nullable = false)
        val url: String
)