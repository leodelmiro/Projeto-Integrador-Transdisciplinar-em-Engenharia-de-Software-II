package com.leodelmiro.cupcakes.dto

import com.leodelmiro.cupcakes.controllers.validacoes.CpfUnico
import com.leodelmiro.cupcakes.controllers.validacoes.EmailUnico
import com.leodelmiro.cupcakes.controllers.validacoes.RolesValidas
import com.leodelmiro.cupcakes.dto.EnderecoDTO.Companion.toEntidade
import com.leodelmiro.cupcakes.dto.TelefoneDTO.Companion.toEntidade
import com.leodelmiro.cupcakes.model.Usuario
import com.leodelmiro.cupcakes.repositories.RoleRepository
import com.leodelmiro.cupcakes.services.EncryptService
import org.hibernate.validator.constraints.br.CPF
import javax.validation.Valid
import javax.validation.constraints.*

data class UsuarioInclusaoDTO(
        @field:NotBlank(message = "Nome não pode ser em branco.")
        val nome: String,
        @field:NotBlank(message = "CPF não pode ser em branco.")
        @field:CPF(message = "CPF deve ser um cpf válido")
        @field:CpfUnico
        val cpf: String,
        @field:NotBlank(message = "Email não pode ser em branco.")
        @field:Email(message = "Email deve ser um email válido")
        @field:EmailUnico
        val email: String,
        @field:NotBlank(message = "Senha não pode ser em branco.")
        @field:Min(value = 5, message = "Senha deve ter no mínimo 5 caracteres")
        val password: String,
        @field:NotNull(message = "Telefone não pode ser nulo.")
        @field:Valid
        val telefone: TelefoneDTO,
        @field:NotNull(message = "Endereço não pode ser nulo.")
        @field:Valid
        val endereco: EnderecoDTO,
        @field:NotNull(message = "Role não pode ser nulo.")
        @field:Size(min = 1, message = "O usuário deve ter no mínimo 1 role")
        @field:RolesValidas
        val roles: List<Long>
) {
    companion object {
        fun UsuarioInclusaoDTO.toEntidade(roleRepository: RoleRepository, encryptService: EncryptService): Usuario =
                with(encryptService.createSalt()) {
                    Usuario(
                            nome = this@toEntidade.nome,
                            cpf = this@toEntidade.cpf,
                            email = this@toEntidade.email,
                            passwordSalt = this,
                            passwordHash = encryptService.encode(this@toEntidade.password, this),
                            telefone = this@toEntidade.telefone.toEntidade(),
                            endereco = this@toEntidade.endereco.toEntidade(),
                            roles = roles.map { roleId -> roleRepository.getReferenceById(roleId) }.toSet()
                    )
                }
    }
}