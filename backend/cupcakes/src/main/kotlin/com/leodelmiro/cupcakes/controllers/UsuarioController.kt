package com.leodelmiro.cupcakes.controllers

import com.leodelmiro.cupcakes.dto.UsuarioAtualizacaoDTO
import com.leodelmiro.cupcakes.dto.UsuarioInclusaoDTO
import com.leodelmiro.cupcakes.dto.UsuarioResponseDTO
import com.leodelmiro.cupcakes.services.UsuarioService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import javax.validation.Valid


@RestController
@RequestMapping(value = ["/usuarios"])
@Tag(name = "Usuário")
class UsuarioController(
        @Autowired val usuarioService: UsuarioService,
) {
    @GetMapping(value = ["/{id}"])
    @Operation(summary = "Encontra usuário por ID")
    fun encontrarPorId(@PathVariable id: Long): ResponseEntity<UsuarioResponseDTO> =
            usuarioService.encontrarPorId(id).let {
                ResponseEntity.ok().body(it)
            }

    @PostMapping
    @Operation(summary = "Adiciona novo usuário")
    fun adicionar(@Valid @RequestBody dto: UsuarioInclusaoDTO): ResponseEntity<UsuarioResponseDTO> =
            usuarioService.inserir(dto).let { novoUsuario ->
                val uri = ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(novoUsuario.id)
                        .toUri()
                ResponseEntity.created(uri).body(novoUsuario)
            }

    @PutMapping(value = ["/{id}"])
    @Operation(summary = "Atualiza usuário por ID")
    fun atualizar(@PathVariable id: Long, @Valid @RequestBody dto: UsuarioAtualizacaoDTO)
            : ResponseEntity<UsuarioResponseDTO> =
            usuarioService.atualizar(id, dto).let { usuarioAtualizado ->
                ResponseEntity.ok().body(usuarioAtualizado)
            }

    @DeleteMapping(value = ["/{id}"])
    @Operation(summary = "Deleta usuário por ID")
    fun deletar(@PathVariable id: Long): ResponseEntity<UsuarioResponseDTO> =
            usuarioService.deletar(id).let {
                ResponseEntity.noContent().build()
            }

}