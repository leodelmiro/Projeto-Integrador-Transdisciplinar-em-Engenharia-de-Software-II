package com.leodelmiro.cupcakes.dto

import com.leodelmiro.cupcakes.model.Telefone

class TelefoneDTO(val ddd: String, val numero: String) {
    companion object {
        fun TelefoneDTO.toEntidade() = Telefone(this.ddd, this.numero)
    }
}