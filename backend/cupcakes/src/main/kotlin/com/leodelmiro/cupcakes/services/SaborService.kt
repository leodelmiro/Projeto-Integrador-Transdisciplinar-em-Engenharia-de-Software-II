package com.leodelmiro.cupcakes.services

import com.leodelmiro.cupcakes.dto.SaborRequestDTO
import com.leodelmiro.cupcakes.dto.SaborResponseDTO
import com.leodelmiro.cupcakes.model.Sabor
import com.leodelmiro.cupcakes.repositories.SaborRepository
import com.leodelmiro.cupcakes.services.exceptions.DatabaseException
import com.leodelmiro.cupcakes.services.exceptions.RecursoNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityNotFoundException

@Service
class SaborService(@Autowired val saborRepository: SaborRepository) {

    @Transactional(readOnly = true)
    fun encontrarTodos(): List<SaborResponseDTO> = saborRepository.findAll().map { sabor -> SaborResponseDTO(sabor) }

    @Transactional
    fun inserir(dto: SaborRequestDTO): SaborResponseDTO =
            Sabor(nome = dto.nome).apply {
                saborRepository.save(this)
            }.let { entidade ->
                SaborResponseDTO(entidade)
            }


    @Transactional
    fun atualizar(id: Long, dto: SaborRequestDTO): SaborResponseDTO =
            try {
                saborRepository.getReferenceById(id).apply {
                    this.nome = dto.nome
                    saborRepository.save(this)
                }.let { entidade ->
                    SaborResponseDTO(entidade)
                }
            } catch (e: EntityNotFoundException) {
                throw RecursoNotFoundException("Id não encontrado de id: $id")
            }

    @Transactional
    fun deletar(id: Long) = try {
        saborRepository.deleteById(id)
    } catch (e: EmptyResultDataAccessException) {
        throw RecursoNotFoundException("Id não encontrado de id: $id")
    } catch (e: DataIntegrityViolationException) {
        throw DatabaseException("Violação de integridade do banco")
    }
}
