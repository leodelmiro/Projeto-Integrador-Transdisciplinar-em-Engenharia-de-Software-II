package com.leodelmiro.cupcakes.dto

import com.leodelmiro.cupcakes.model.Endereco
import com.leodelmiro.cupcakes.model.Role
import com.leodelmiro.cupcakes.model.Telefone
import com.leodelmiro.cupcakes.model.Usuario

data class UsuarioResponseDTO(
        val id: Long? = null,
        val nome: String,
        val cpf: String,
        val email: String,
        val telefone: Telefone,
        val endereco: Endereco,
        val role: Set<Role>
) {
    constructor(entidade: Usuario) : this(
            id = entidade.id,
            nome = entidade.nome,
            cpf = entidade.cpf,
            email = entidade.email,
            telefone = entidade.telefone,
            endereco = entidade.endereco,
            role = entidade.roles
    )
}
