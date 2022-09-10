package com.leodelmiro.cupcakes.dto

import com.leodelmiro.cupcakes.model.Telefone
import javax.validation.constraints.NotBlank

data class TelefoneDTO(
        @field:NotBlank(message = "DDD não pode ser em branco.")
        val ddd: String,
        @field:NotBlank(message = "Número não pode ser em branco.")
        val numero: String
) {
    companion object {
        fun TelefoneDTO.toEntidade() = Telefone(this.ddd, this.numero)
    }
}