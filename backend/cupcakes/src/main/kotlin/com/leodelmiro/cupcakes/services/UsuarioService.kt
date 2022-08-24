package com.leodelmiro.cupcakes.services

import com.leodelmiro.cupcakes.dto.UsuarioAtualizacaoDTO
import com.leodelmiro.cupcakes.dto.UsuarioAtualizacaoDTO.Companion.toEntidade
import com.leodelmiro.cupcakes.dto.UsuarioInclusaoDTO
import com.leodelmiro.cupcakes.dto.UsuarioInclusaoDTO.Companion.toEntidade
import com.leodelmiro.cupcakes.dto.UsuarioResponseDTO
import com.leodelmiro.cupcakes.repositories.RoleRepository
import com.leodelmiro.cupcakes.repositories.UsuarioRepository
import com.leodelmiro.cupcakes.services.exceptions.DatabaseException
import com.leodelmiro.cupcakes.services.exceptions.RecursoNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityNotFoundException

@Service
class UsuarioService(@Autowired val userRepository: UsuarioRepository, @Autowired val roleRepository: RoleRepository) {

    @Transactional(readOnly = true)
    fun findById(id: Long): UsuarioResponseDTO =
            userRepository.findById(id)
                    .orElseThrow { RecursoNotFoundException("Entity not found") }
                    .let { usuario -> UsuarioResponseDTO(usuario) }

    @Transactional
    fun insert(dto: UsuarioInclusaoDTO): UsuarioResponseDTO? =
            dto.toEntidade(roleRepository).apply {
                // TODO ENCRYPT DA SENHA ANTES DE SALVAR
                userRepository.save(this)
            }.let { entidade ->
                UsuarioResponseDTO(entidade)
            }


    @Transactional
    fun update(id: Long, dto: UsuarioAtualizacaoDTO): UsuarioResponseDTO? =
            try {
                userRepository.getReferenceById(id).apply {
                    userRepository.save(dto.toEntidade(this))
                }.let { entidade ->
                    UsuarioResponseDTO(entidade)
                }
            } catch (e: EntityNotFoundException) {
                throw RecursoNotFoundException("Id não encontrado de id: $id")
            }


    @Transactional
    fun delete(id: Long) = try {
        userRepository.deleteById(id)
    } catch (e: EmptyResultDataAccessException) {
        throw RecursoNotFoundException("Id não encontrado de id: $id")
    } catch (e: DataIntegrityViolationException) {
        throw DatabaseException("Violação de integridade do banco")
    }
}
