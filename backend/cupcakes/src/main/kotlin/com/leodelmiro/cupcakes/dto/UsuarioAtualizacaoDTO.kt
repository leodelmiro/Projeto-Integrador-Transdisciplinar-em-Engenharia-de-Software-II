package com.leodelmiro.cupcakes.dto

data class UsuarioAtualizacaoDTO(
        val nome: String?,
        val telefone: TelefoneDTO?,
        val endereco: EnderecoDTO?
)