package com.leodelmiro.cupcakes.dto

import com.leodelmiro.cupcakes.model.Endereco
import javax.validation.constraints.NotBlank

class EnderecoDTO(
        @field:NotBlank(message = "Logradouro não pode ser em branco.")
        val logradouro: String,
        @field:NotBlank(message = "Bairro não pode ser em branco.")
        val bairro: String,
        @field:NotBlank(message = "CEP não pode ser em branco.")
        val cep: String,
        @field:NotBlank(message = "Cidade não pode ser em branco.")
        val cidade: String,
        @field:NotBlank(message = "Estado não pode ser em branco.")
        val estado: String
) {
    companion object {
        fun EnderecoDTO.toEntidade() = Endereco(
                this.logradouro,
                this.bairro,
                this.cep,
                this.cidade,
                this.estado)
    }
}