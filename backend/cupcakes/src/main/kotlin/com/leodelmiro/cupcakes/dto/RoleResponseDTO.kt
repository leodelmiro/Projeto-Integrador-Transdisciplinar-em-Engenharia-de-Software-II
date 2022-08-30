package com.leodelmiro.cupcakes.dto

import com.leodelmiro.cupcakes.model.Role

class RoleResponseDTO(
        var id: Long?,
        val autoridade: String
) {
    constructor(entidade: Role) : this(id = entidade.id, autoridade = entidade.autoridade)
}
