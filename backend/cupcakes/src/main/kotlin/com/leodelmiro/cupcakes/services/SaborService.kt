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
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal
import javax.persistence.EntityNotFoundException

@Service
class SaborService(@Autowired val saborRepository: SaborRepository) {

    @Transactional(readOnly = true)
    fun encontrarTodos(): List<SaborResponseDTO> = saborRepository.findAll().map { sabor -> SaborResponseDTO(sabor) }

    @Transactional(readOnly = true)
    fun encontrarTodosPaginado(
            nome: String? = null,
            pagina: PageRequest
    ): Page<SaborResponseDTO> = saborRepository.find(nome, pagina).map { sabor -> SaborResponseDTO(sabor) }

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
                throw RecursoNotFoundException("Id n??o encontrado de id: $id")
            }

    @Transactional
    fun deletar(id: Long) = try {
        saborRepository.deleteById(id)
    } catch (e: EmptyResultDataAccessException) {
        throw RecursoNotFoundException("Id n??o encontrado de id: $id")
    } catch (e: DataIntegrityViolationException) {
        throw DatabaseException("Viola????o de integridade do banco")
    }

    @Transactional(readOnly = true)
    fun isSaborExistente(id: Long): Boolean = saborRepository.findById(id).isPresent
}
