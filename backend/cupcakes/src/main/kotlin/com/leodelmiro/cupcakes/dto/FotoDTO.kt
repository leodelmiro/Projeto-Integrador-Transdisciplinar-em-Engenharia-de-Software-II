package com.leodelmiro.cupcakes.dto

import org.hibernate.validator.constraints.URL

class FotoDTO(
        @URL(protocol = "http")
        val url: String
)
