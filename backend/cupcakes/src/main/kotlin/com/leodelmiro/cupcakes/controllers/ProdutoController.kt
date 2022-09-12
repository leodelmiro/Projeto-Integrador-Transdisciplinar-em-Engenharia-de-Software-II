package com.leodelmiro.cupcakes.controllers

import com.leodelmiro.cupcakes.dto.ProdutoAtualizacaoDTO
import com.leodelmiro.cupcakes.dto.ProdutoInclusaoDTO
import com.leodelmiro.cupcakes.dto.ProdutoResponseDTO
import com.leodelmiro.cupcakes.services.ProdutoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.math.BigDecimal
import javax.validation.Valid


@RestController
@RequestMapping(value = ["/produtos"])
class ProdutoController(
        @Autowired val produtoService: ProdutoService
) {

    @GetMapping
    fun encontrarTodos(
            @RequestParam(value = "saborId", defaultValue = "0") saborId: Long,
            @RequestParam(value = "min", defaultValue = "") precoMin: String,
            @RequestParam(value = "max", defaultValue = "") precoMax: String,
            @RequestParam(value = "pagina", defaultValue = "0") pagina: Int,
            @RequestParam(value = "produtosPorPagina", defaultValue = "12") produtosPorPagina: Int,
            @RequestParam(value = "direcao", defaultValue = "ASC") direcao: String,
            @RequestParam(value = "ordernarPor", defaultValue = "nome") ordernarPor: String
    ): ResponseEntity<Page<ProdutoResponseDTO>> =
            produtoService.encontrarTodosPaginado(
                    if (saborId == 0L) null else saborId,
                    if (precoMin == "") null else BigDecimal(precoMin),
                    if (precoMax == "") null else BigDecimal(precoMax),
                    PageRequest.of(
                            pagina,
                            if (produtosPorPagina == 0) Int.MAX_VALUE else produtosPorPagina,
                            Sort.Direction.valueOf(direcao),
                            ordernarPor
                    )
            ).let {
                ResponseEntity.ok().body(it)
            }

    @GetMapping(value = ["/{id}"])
    fun encontrarPorId(@PathVariable id: Long): ResponseEntity<ProdutoResponseDTO>? =
            produtoService.encontrarPorId(id).let {
                ResponseEntity.ok().body(it)
            }

    // TODO DEIXAR APENAS ADMIN (WRITE)
    @PostMapping
    fun inserir(@Valid @RequestBody dto: ProdutoInclusaoDTO): ResponseEntity<ProdutoResponseDTO>? =
            produtoService.inserir(dto).let { novoProduto ->
                val uri = ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(novoProduto.id)
                        .toUri()
                ResponseEntity.created(uri).body(novoProduto)
            }

    // TODO DEIXAR APENAS ADMIN (WRITE)
    @PutMapping(value = ["/{id}"])
    fun atualizar(@PathVariable id: Long, @Valid @RequestBody dto: ProdutoAtualizacaoDTO)
            : ResponseEntity<ProdutoResponseDTO>? =
            produtoService.atualizar(id, dto).let { produtoAtualizado ->
                ResponseEntity.ok().body(produtoAtualizado)
            }

    // TODO DEIXAR APENAS ADMIN
    @DeleteMapping(value = ["/{id}"])
    fun deletar(@PathVariable id: Long): ResponseEntity<ProdutoResponseDTO>? =
            produtoService.deletar(id).let {
                ResponseEntity.noContent().build()
            }

}