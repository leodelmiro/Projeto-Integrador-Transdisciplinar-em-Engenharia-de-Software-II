package com.leodelmiro.cupcakes.dto

import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

class SaborRequestDTO(
        @field:NotBlank(message = "Nome não pode ser em branco.")
        @field:Size(min = 3, message = "Tamanho do nome deve ter pelo menos 3 caracteres")
        val nome: String
)