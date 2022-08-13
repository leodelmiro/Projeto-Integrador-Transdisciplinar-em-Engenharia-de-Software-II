package com.leodelmiro.cupcakes.model

import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class Endereco(
        @field:Column(nullable = false)
        val logradouro: String,
        @field:Column(nullable = false)
        val bairro: String,
        @field:Column(nullable = false)
        val cep: String,
        @field:Column(nullable = false)
        val cidade: String,
        @field:Column(nullable = false)
        val estado: String
)
