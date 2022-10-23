package com.leodelmiro.cupcakes.controllers

import com.leodelmiro.cupcakes.dto.PedidoCriacaoDTO
import com.leodelmiro.cupcakes.dto.PedidoResponseDTO
import com.leodelmiro.cupcakes.services.PedidoService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import javax.validation.Valid

@RestController
@RequestMapping(value = ["/pedidos"])
@Tag(name = "Pedido")
class PedidoController(
        @Autowired val pedidoService: PedidoService
) {

    @GetMapping(value = ["/{id}"])
    @Operation(summary = "Encontra pedido por ID")
    fun encontrarPorId(@PathVariable id: Long): ResponseEntity<PedidoResponseDTO>? =
            pedidoService.encontrarPorId(id).let {
                ResponseEntity.ok().body(it)
            }


    @GetMapping(value = ["/usuarios/{id}"])
    @Operation(summary = "Lista todos os pedidos por ID do Usuário")
    fun encontrarTodosPorUsuario(@PathVariable(value = "id") usuarioId: Long,
                                 @RequestParam(value = "numero", defaultValue = "0") numero: Long,
                                 @RequestParam(value = "pagina", defaultValue = "0") pagina: Int,
                                 @RequestParam(value = "pedidosPorPagina", defaultValue = "12") pedidosPorPagina: Int,
                                 @RequestParam(value = "direcao", defaultValue = "ASC") direcao: String,
                                 @RequestParam(value = "ordernarPor", defaultValue = "id") ordernarPor: String):
            ResponseEntity<List<PedidoResponseDTO>>? =
            pedidoService.encontrarTodosPorUsuario(
                    usuarioId,
                    if (numero == 0L) null else numero,
                    PageRequest.of(
                            pagina,
                            if (pedidosPorPagina == 0) Int.MAX_VALUE else pedidosPorPagina,
                            Sort.Direction.valueOf(direcao),
                            ordernarPor
                    )).let {
                ResponseEntity.ok().body(it)
            }

    @PostMapping
    @Operation(summary = "Adiciona novo pedido")
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
    @Operation(summary = "Finaliza pedido por ID e método de pagamento")
    fun finalizar(@PathVariable id: Long, @PathVariable metodoPagamento: String)
            : ResponseEntity<PedidoResponseDTO> =
            pedidoService.finalizar(id, metodoPagamento).let {
                ResponseEntity.ok().body(it)
            }

    @PostMapping(value = ["/{id}/cancelar"])
    @Operation(summary = "Cancela pedido por ID")
    fun cancelar(@PathVariable id: Long) = pedidoService.cancelar(id).let { produtoAtualizado ->
        ResponseEntity.ok().body(produtoAtualizado)
    }
}