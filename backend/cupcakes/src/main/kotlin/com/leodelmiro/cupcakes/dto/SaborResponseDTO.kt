package com.leodelmiro.cupcakes.dto

import com.leodelmiro.cupcakes.model.Sabor

data class SaborResponseDTO(
        var id: Long?,
        val nome: String
) {
    constructor(entidade: Sabor) : this(id = entidade.id, nome = entidade.nome)
}
