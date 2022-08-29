package com.leodelmiro.cupcakes.controllers

import com.leodelmiro.cupcakes.dto.UsuarioAtualizacaoDTO
import com.leodelmiro.cupcakes.dto.UsuarioInclusaoDTO
import com.leodelmiro.cupcakes.dto.UsuarioResponseDTO
import com.leodelmiro.cupcakes.services.UsuarioService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import javax.validation.Valid


@RestController
@RequestMapping(value = ["/usuarios"])
class UsuarioController(
        @Autowired val usuarioService: UsuarioService,
) {
    @GetMapping(value = ["/{id}"])
    fun encontrarPorId(@PathVariable id: Long): ResponseEntity<UsuarioResponseDTO>? =
            usuarioService.encontrarPorId(id).let {
                ResponseEntity.ok().body(it)
            }

    @PostMapping
    fun adicionar(@Valid @RequestBody dto: UsuarioInclusaoDTO): ResponseEntity<UsuarioResponseDTO>? =
            usuarioService.inserir(dto)?.let { novoUsuario ->
                val uri = ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(novoUsuario.id)
                        .toUri()
                ResponseEntity.created(uri).body(novoUsuario)
            }


    @PutMapping(value = ["/{id}"])
    fun atualizar(@PathVariable id: Long, @Valid @RequestBody dto: UsuarioAtualizacaoDTO)
            : ResponseEntity<UsuarioResponseDTO>? =
            usuarioService.atualizar(id, dto).let { usuarioAtualizado ->
                ResponseEntity.ok().body(usuarioAtualizado)
            }

    @DeleteMapping(value = ["/{id}"])
    fun deletar(@PathVariable id: Long): ResponseEntity<UsuarioResponseDTO>? =
            usuarioService.deletar(id).let {
                ResponseEntity.noContent().build()
            }

}