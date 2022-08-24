package com.leodelmiro.cupcakes.dto

import com.leodelmiro.cupcakes.dto.EnderecoDTO.Companion.toEntidade
import com.leodelmiro.cupcakes.dto.TelefoneDTO.Companion.toEntidade
import com.leodelmiro.cupcakes.model.Role
import com.leodelmiro.cupcakes.model.Usuario
import com.leodelmiro.cupcakes.repositories.RoleRepository

class UsuarioInclusaoDTO(
        val nome: String,
        val cpf: String,
        val email: String,
        val password: String,
        val telefone: TelefoneDTO,
        val endereco: EnderecoDTO,
        val roles: List<Long>
) {
    // TODO VALIDAÇÃO

    companion object {
        fun UsuarioInclusaoDTO.toEntidade(roleRepository: RoleRepository): Usuario = Usuario(
                nome = this.nome,
                cpf = this.cpf,
                email = this.email,
                password = this.password,
                telefone = this.telefone.toEntidade(),
                endereco = this.endereco.toEntidade(),
                roles = roles.map { roleId -> roleRepository.getReferenceById(roleId) }.toSet()
        )
    }
}