package com.leodelmiro.cupcakes.model

class Usuario(
        id: Long? = null,
        cpf: String,
        email: String,
        password: String,
        telefone: Telefone,
        endereco: Endereco,
        role: Role
)