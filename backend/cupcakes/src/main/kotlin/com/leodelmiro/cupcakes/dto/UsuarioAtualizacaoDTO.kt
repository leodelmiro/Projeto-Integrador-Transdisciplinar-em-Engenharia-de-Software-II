package com.leodelmiro.cupcakes.dto

import com.leodelmiro.cupcakes.dto.EnderecoDTO.Companion.toEntidade
import com.leodelmiro.cupcakes.dto.TelefoneDTO.Companion.toEntidade
import com.leodelmiro.cupcakes.model.Usuario

class UsuarioAtualizacaoDTO(val nome: String, val telefone: TelefoneDTO, val endereco: EnderecoDTO) {

    // TODO VALIDAÇÃO

    companion object {
        fun UsuarioAtualizacaoDTO.toEntidade(entity: Usuario): Usuario = entity.apply {
            this.nome = this@toEntidade.nome
            this.telefone = this@toEntidade.telefone.toEntidade()
            this.endereco = this@toEntidade.endereco.toEntidade()
        }
    }
}