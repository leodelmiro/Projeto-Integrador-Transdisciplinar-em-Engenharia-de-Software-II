package com.leodelmiro.cupcakes.services

import com.leodelmiro.cupcakes.dto.EnderecoDTO.Companion.toEntidade
import com.leodelmiro.cupcakes.dto.TelefoneDTO.Companion.toEntidade
import com.leodelmiro.cupcakes.dto.UsuarioAtualizacaoDTO
import com.leodelmiro.cupcakes.dto.UsuarioInclusaoDTO
import com.leodelmiro.cupcakes.dto.UsuarioInclusaoDTO.Companion.toEntidade
import com.leodelmiro.cupcakes.dto.UsuarioResponseDTO
import com.leodelmiro.cupcakes.model.Usuario
import com.leodelmiro.cupcakes.repositories.RoleRepository
import com.leodelmiro.cupcakes.repositories.UsuarioRepository
import com.leodelmiro.cupcakes.services.exceptions.DatabaseException
import com.leodelmiro.cupcakes.services.exceptions.RecursoNotFoundException
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityNotFoundException

@Service
class UsuarioService(@Autowired val userRepository: UsuarioRepository,
                     @Autowired val roleRepository: RoleRepository,
                     @Autowired val passwordEncoder: BCryptPasswordEncoder,
                     @Autowired val authService: AuthService
) : UserDetailsService {

    @Transactional(readOnly = true)
    fun encontrarPorId(id: Long): UsuarioResponseDTO =
            authService.validataSeDeleOuAdmin(id).run {
                userRepository.findById(id)
                        .orElseThrow { RecursoNotFoundException("Id não encontrado de usuário id: $id") }
                        .let { usuario -> UsuarioResponseDTO(usuario) }
            }

    @Transactional(readOnly = true)
    fun encontrarPorEmail(email: String): Usuario =
            userRepository.findByEmail(email)
                    .orElseThrow { RecursoNotFoundException("Email $email não encontrado!") }

    @Transactional(readOnly = true)
    fun encontrarEntidadePorId(id: Long): Usuario =
            userRepository.findById(id)
                    .orElseThrow { RecursoNotFoundException("Id não encontrado de usuário id: $id") }

    @Transactional
    fun inserir(dto: UsuarioInclusaoDTO): UsuarioResponseDTO =
            dto.toEntidade(roleRepository, passwordEncoder).apply {
                userRepository.save(this)
            }.let { entidade ->
                UsuarioResponseDTO(entidade)
            }


    @Transactional
    fun atualizar(id: Long, dto: UsuarioAtualizacaoDTO): UsuarioResponseDTO =
            authService.validataSeDeleOuAdmin(id).run {
                try {
                    userRepository.getReferenceById(id).apply {
                        updateCamposNaoNulos(dto)
                        userRepository.save(this)
                    }.let { entidade ->
                        UsuarioResponseDTO(entidade)
                    }
                } catch (e: EntityNotFoundException) {
                    throw RecursoNotFoundException("Id não encontrado de usuário id: $id")
                }
            }

    private fun Usuario.updateCamposNaoNulos(dto: UsuarioAtualizacaoDTO) {
        dto.nome?.let { this.nome = it }
        dto.telefone?.let { it.toEntidade().also { telefone -> this.telefone = telefone } }
        dto.endereco?.let { it.toEntidade().also { endereco -> this.endereco = endereco } }
    }

    @Transactional
    fun deletar(id: Long) = try {
        userRepository.deleteById(id)
    } catch (e: EmptyResultDataAccessException) {
        throw RecursoNotFoundException("Id não encontrado de usuário id: $id")
    } catch (e: DataIntegrityViolationException) {
        throw DatabaseException("Violação de integridade do banco")
    }

    @Transactional(readOnly = true)
    fun isEmailJaExistente(email: String): Boolean = userRepository.findByEmail(email).isPresent

    @Transactional(readOnly = true)
    fun isCpfJaExistente(cpf: String): Boolean = userRepository.findByCpf(cpf).isPresent

    @Transactional(readOnly = true)
    fun isUsuarioExistente(id: Long): Boolean = userRepository.findById(id).isPresent

    override fun loadUserByUsername(username: String): UserDetails =
            userRepository.findByEmail(username)
                    .orElseThrow {
                        UsernameNotFoundException("Usuário não encontrado")
                                .also { LOGGER.error("Usuário não encontrado $username") }
                    }.also { LOGGER.info("Usuário encontrado $username") }

    companion object {
        private val LOGGER = LoggerFactory.getLogger(this::class.java)
    }

}
