package com.leodelmiro.cupcakes.controllers

import com.leodelmiro.cupcakes.dto.PedidoCriacaoDTO
import com.leodelmiro.cupcakes.dto.PedidoResponseDTO
import com.leodelmiro.cupcakes.services.PedidoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import javax.validation.Valid

@RestController
@RequestMapping(value = ["/pedidos"])
class PedidoController(
        @Autowired val pedidoService: PedidoService
) {

    @GetMapping(value = ["/{id}"])
    fun encontrarPorId(@PathVariable id: Long): ResponseEntity<PedidoResponseDTO>? =
            pedidoService.encontrarPorId(id).let {
                ResponseEntity.ok().body(it)
            }


    @GetMapping(value = ["/usuarios/{id}"])
    fun encontrarTodosPorUsuario(@PathVariable id: Long): ResponseEntity<List<PedidoResponseDTO>>? =
            pedidoService.encontrarTodosPorUsuario(id).let {
                ResponseEntity.ok().body(it)
            }

    @PostMapping
    fun criar(@Valid @RequestBody dto: PedidoCriacaoDTO): ResponseEntity<PedidoResponseDTO>? =
            pedidoService.criar(dto).let { novoPedido ->
                val uri = ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(novoPedido.id)
                        .toUri()
                ResponseEntity.created(uri).body(novoPedido)
            }

    @PostMapping(value = ["/{id}/pagar/{metodoPagamento}"])
    fun finalizar(@PathVariable id: Long, @PathVariable metodoPagamento: String)
            : ResponseEntity<PedidoResponseDTO> =
            pedidoService.finalizar(id, metodoPagamento).let {
                ResponseEntity.ok().body(it)
            }

    @PostMapping(value = ["/{id}/cancelar"])
    fun cancelar(@PathVariable id: Long) = pedidoService.cancelar(id).let { produtoAtualizado ->
        ResponseEntity.ok().body(produtoAtualizado)
    }
}