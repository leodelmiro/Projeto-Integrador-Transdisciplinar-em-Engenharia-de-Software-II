package com.leodelmiro.cupcakes.dto

data class EnderecoClientResponse(
        val cep: String,
        val logradouro: String,
        val numero: String,
        val bairro: String,
        val localidade: String,
        val uf: String
) {
    companion object {
        fun EnderecoClientResponse.toEnderecoDTO() =
                EnderecoDTO(
                        logradouro = this.logradouro,
                        numero = this.numero,
                        bairro = this.bairro,
                        cep = this.cep,
                        cidade = this.localidade,
                        estado = this.uf
                )
    }
}
