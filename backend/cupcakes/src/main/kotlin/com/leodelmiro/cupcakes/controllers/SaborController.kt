package com.leodelmiro.cupcakes.controllers

import com.leodelmiro.cupcakes.dto.*
import com.leodelmiro.cupcakes.services.SaborService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import javax.validation.Valid


@RestController
@RequestMapping(value = ["/sabores"])
class SaborController(
        @Autowired val saborService: SaborService,
) {

    @GetMapping
    fun encontrarTodos(): ResponseEntity<List<SaborResponseDTO>> =
            saborService.encontrarTodos().let {
                ResponseEntity.ok().body(it)
            }

    // TODO DEIXAR APENAS ADMIN (WRITE)
    @PostMapping
    fun adicionar(@Valid @RequestBody dto: SaborRequestDTO): ResponseEntity<SaborResponseDTO> =
            saborService.inserir(dto).let { novoSabor ->
                val uri = ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(novoSabor.id)
                        .toUri()
                ResponseEntity.created(uri).body(novoSabor)
            }

    // TODO DEIXAR APENAS ADMIN (WRITE)
    @PutMapping(value = ["/{id}"])
    fun atualizar(@PathVariable id: Long, @Valid @RequestBody dto: SaborRequestDTO)
            : ResponseEntity<SaborResponseDTO> =
            saborService.atualizar(id, dto).let { saborAtualizado ->
                ResponseEntity.ok().body(saborAtualizado)
            }

    // TODO DEIXAR APENAS ADMIN
    @DeleteMapping(value = ["/{id}"])
    fun deletar(@PathVariable id: Long): ResponseEntity<SaborResponseDTO> =
            saborService.deletar(id).let {
                ResponseEntity.noContent().build()
            }
}