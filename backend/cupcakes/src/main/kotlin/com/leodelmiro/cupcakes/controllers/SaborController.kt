package com.leodelmiro.cupcakes.controllers

import com.leodelmiro.cupcakes.dto.SaborRequestDTO
import com.leodelmiro.cupcakes.dto.SaborResponseDTO
import com.leodelmiro.cupcakes.services.SaborService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import javax.validation.Valid


@RestController
@RequestMapping(value = ["/sabores"])
@Tag(name = "Sabor")
class SaborController(
        @Autowired val saborService: SaborService,
) {

    @GetMapping
    @Operation(summary = "Lista com todos os sabores")
    fun encontrarTodos(): ResponseEntity<List<SaborResponseDTO>> =
            saborService.encontrarTodos().let {
                ResponseEntity.ok().body(it)
            }

    @GetMapping("/paginado")
    @Operation(summary = "Lista com todos os sabores paginado")
    fun encontrarTodosPaginado(
            @RequestParam(value = "nome", defaultValue = "") nome: String,
            @RequestParam(value = "pagina", defaultValue = "0") pagina: Int,
            @RequestParam(value = "saboresPorPagina", defaultValue = "12") saboresPorPagina: Int,
            @RequestParam(value = "direcao", defaultValue = "ASC") direcao: String,
            @RequestParam(value = "ordernarPor", defaultValue = "nome") ordernarPor: String
    ): ResponseEntity<Page<SaborResponseDTO>> =
            saborService.encontrarTodosPaginado(
                    if (nome == "") null else nome.trim(),
                    PageRequest.of(
                            pagina,
                            if (saboresPorPagina == 0) Int.MAX_VALUE else saboresPorPagina,
                            Sort.Direction.valueOf(direcao),
                            ordernarPor
                    )
            ).let {
                ResponseEntity.ok().body(it)
            }


    @PostMapping
    @Operation(summary = "Adicionar novo sabor")
    fun adicionar(@Valid @RequestBody dto: SaborRequestDTO): ResponseEntity<SaborResponseDTO> =
            saborService.inserir(dto).let { novoSabor ->
                val uri = ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(novoSabor.id)
                        .toUri()
                ResponseEntity.created(uri).body(novoSabor)
            }

    @PutMapping(value = ["/{id}"])
    @Operation(summary = "Atualiza sabor por ID")
    fun atualizar(@PathVariable id: Long, @Valid @RequestBody dto: SaborRequestDTO)
            : ResponseEntity<SaborResponseDTO> =
            saborService.atualizar(id, dto).let { saborAtualizado ->
                ResponseEntity.ok().body(saborAtualizado)
            }

    @DeleteMapping(value = ["/{id}"])
    @Operation(summary = "Deleta sabor por ID")
    fun deletar(@PathVariable id: Long): ResponseEntity<SaborResponseDTO> =
            saborService.deletar(id).let {
                ResponseEntity.noContent().build()
            }
}