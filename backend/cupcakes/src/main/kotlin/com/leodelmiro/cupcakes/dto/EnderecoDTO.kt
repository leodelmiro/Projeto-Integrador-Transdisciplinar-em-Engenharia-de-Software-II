package com.leodelmiro.cupcakes.dto

import com.leodelmiro.cupcakes.model.Endereco
import com.leodelmiro.cupcakes.model.Telefone

class EnderecoDTO(
        val logradouro: String,
        val bairro: String,
        val cep: String,
        val cidade: String,
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