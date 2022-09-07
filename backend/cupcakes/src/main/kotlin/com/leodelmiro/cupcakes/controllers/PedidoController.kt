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

    @PostMapping
    fun criar(@Valid @RequestBody dto: PedidoCriacaoDTO): ResponseEntity<PedidoResponseDTO>? =
            pedidoService.inserir(dto).let { novoPedido ->
                val uri = ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(novoPedido.id)
                        .toUri()
                ResponseEntity.created(uri).body(novoPedido)
            }

//    @GetMapping(value = ["/{usuario}"])
//    fun encontrarTodosPorUsuario(@PathVariable id: Long): ResponseEntity<List<PedidoResponseDTO>>? =
//            pedidoService.encontrarTodosPorUsuario(id).let {
//                ResponseEntity.ok().body(it)
//            }
//
//    @PostMapping(value = ["/{id}/produtos"])
//    // TODO RECEBER DADOS DE UM PRODUTO (ID PRODUTO + QUANTIDADE)
//    fun adicionarProduto(@PathVariable id: Long, @Valid @RequestBody dto: PedidoAdicaoProdutoDTO)
//            : ResponseEntity<PedidoResponseDTO>? =
//            pedidoService.adicionarProduto(id, dto).let { produtoAtualizado ->
//                ResponseEntity.ok().body(produtoAtualizado)
//            }
//
//    @DeleteMapping(value = ["/{id}/produtos/{idProduto}"])
//    // TODO RECEBER DADOS DE UM PRODUTO
//    fun removerProduto(@PathVariable id: Long, @PathVariable idProduto: Long): ResponseEntity<ProdutoResponseDTO>? =
//            pedidoService.removerProduto(id, idProduto.let {
//                ResponseEntity.noContent().build()
//            }
//
//    @DeleteMapping(value = ["/{id}/produtos"])
//    fun removerTodosProdutos(@PathVariable id: Long): ResponseEntity<ProdutoResponseDTO> =
//            pedidoService.removerTodosProdutos(id).let {
//                ResponseEntity.noContent().build()
//            }
//
//    @PostMapping(value = ["/{id}/finalizar"])
//    fun finalizar(@PathVariable id: Long, @Valid @RequestBody dto: ProdutoAtualizacaoDTO)
//            : ResponseEntity<PedidoResponseDTO> =
//            pedidoService.finalizar(id, dto).let { produtoAtualizado ->
//                ResponseEntity.ok().body(produtoAtualizado)
//            }
//
//    @PostMapping(value = ["/{id}/cancelar"])
//    fun cancelar(@PathVariable id: Long, @Valid @RequestBody dto: ProdutoAtualizacaoDTO)
//            pedidoService.cancelar(id, dto).let { produtoAtualizado ->
//                ResponseEntity.ok().body(produtoAtualizado)
//            }
}