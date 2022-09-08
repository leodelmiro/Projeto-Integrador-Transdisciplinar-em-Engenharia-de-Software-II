package com.leodelmiro.cupcakes.repositories

import com.leodelmiro.cupcakes.model.ProdutoPedido
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProdutoPedidoRepository : JpaRepository<ProdutoPedido, Long> {
    fun findByPedidoIdAndProdutoId(pedidoId: Long, produtoId: Long): ProdutoPedido?
}