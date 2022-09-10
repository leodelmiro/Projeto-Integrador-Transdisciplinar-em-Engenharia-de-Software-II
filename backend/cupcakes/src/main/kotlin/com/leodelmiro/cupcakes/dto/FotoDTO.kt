package com.leodelmiro.cupcakes.dto

import org.hibernate.validator.constraints.URL

data class FotoDTO(
        @URL(protocol = "http")
        val url: String
)
