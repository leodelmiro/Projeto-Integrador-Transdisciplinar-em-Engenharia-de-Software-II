package com.leodelmiro.cupcakes.controllers

import com.leodelmiro.cupcakes.dto.ProdutoAtualizacaoDTO
import com.leodelmiro.cupcakes.dto.ProdutoInclusaoDTO
import com.leodelmiro.cupcakes.dto.ProdutoResponseDTO
import com.leodelmiro.cupcakes.services.ProdutoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import javax.validation.Valid

@RestController
@RequestMapping(value = ["/produtos"])
class ProdutoController(
        @Autowired val produtoService: ProdutoService
) {

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