package com.leodelmiro.cupcakes.services

import com.leodelmiro.cupcakes.dto.ProdutoAtualizacaoDTO
import com.leodelmiro.cupcakes.dto.ProdutoInclusaoDTO
import com.leodelmiro.cupcakes.dto.ProdutoInclusaoDTO.Companion.toEntidade
import com.leodelmiro.cupcakes.dto.ProdutoResponseDTO
import com.leodelmiro.cupcakes.model.Foto
import com.leodelmiro.cupcakes.model.Produto
import com.leodelmiro.cupcakes.repositories.ProdutoRepository
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
class ProdutoService(
        @Autowired val produtoRepository: ProdutoRepository,
        @Autowired val saborRepository: SaborRepository
) {

    @Transactional(readOnly = true)
    fun encontrarTodosPaginado(
            saborId: Long? = null,
            precoMin: BigDecimal? = null,
            precoMax: BigDecimal? = null,
            pagina: PageRequest
    ): Page<ProdutoResponseDTO> {
        val sabor = saborId?.let { sabor -> saborRepository.getReferenceById(sabor) }
        return produtoRepository.find(sabor, precoMin, precoMax, pagina).map { x -> ProdutoResponseDTO(x) }
    }

    @Transactional(readOnly = true)
    fun encontrarPorId(id: Long): ProdutoResponseDTO =
            produtoRepository.findById(id)
                    .orElseThrow { RecursoNotFoundException("Id não encontrado de id: $id") }
                    .let { produto -> ProdutoResponseDTO(produto) }

    @Transactional(readOnly = true)
    fun encontrarPrecoProdutoPorId(id: Long): BigDecimal =
            produtoRepository.findById(id)
                    .orElseThrow { RecursoNotFoundException("Id não encontrado de id: $id") }
                    .preco

    @Transactional(readOnly = true)
    fun encontrarEntidadePorId(id: Long): Produto =
            produtoRepository.findById(id)
                    .orElseThrow { RecursoNotFoundException("Id não encontrado de id: $id") }

    @Transactional
    fun inserir(dto: ProdutoInclusaoDTO): ProdutoResponseDTO =
            dto.toEntidade(saborRepository).apply {
                dto.fotos.map { foto -> this.addFoto(Foto(url = foto.url, produto = this)) }
                produtoRepository.save(this)
            }.let { entidade ->
                ProdutoResponseDTO(entidade)
            }

    @Transactional
    fun atualizar(id: Long, dto: ProdutoAtualizacaoDTO): ProdutoResponseDTO? =
            try {
                produtoRepository.getReferenceById(id).apply {
                    updateCamposNaoNulos(dto)
                    produtoRepository.save(this)
                }.let { entidade ->
                    ProdutoResponseDTO(entidade)
                }
            } catch (e: EntityNotFoundException) {
                throw RecursoNotFoundException("Id não encontrado de id: $id")
            }

    private fun Produto.updateCamposNaoNulos(dto: ProdutoAtualizacaoDTO) {
        dto.nome?.let { this.nome = it }
        dto.quantidade?.let { this.quantidade = it }
        dto.preco?.let { this.preco = it }
        dto.descricao?.let { this.descricao = it }
        dto.sabores?.let {
            this.sabores = dto.sabores.map { saborId -> saborRepository.getReferenceById(saborId) }.toSet()
        }
        dto.fotos?.map { foto -> this.addFoto(Foto(url = foto.url, produto = this)) }
    }


    @Transactional
    fun deletar(id: Long) =
            try {
                produtoRepository.deleteById(id)
            } catch (e: EmptyResultDataAccessException) {
                throw RecursoNotFoundException("Id não encontrado de id: $id")
            } catch (e: DataIntegrityViolationException) {
                throw DatabaseException("Violação de integridade do banco")
            }

    @Transactional(readOnly = true)
    fun isProdutoExistente(id: Long): Boolean = produtoRepository.findById(id).isPresent
}