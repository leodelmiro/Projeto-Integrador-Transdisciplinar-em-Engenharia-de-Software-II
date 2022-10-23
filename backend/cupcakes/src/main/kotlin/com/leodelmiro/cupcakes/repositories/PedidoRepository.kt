package com.leodelmiro.cupcakes.repositories

import com.leodelmiro.cupcakes.model.Pedido
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface PedidoRepository : JpaRepository<Pedido, Long> {

    @Query("SELECT pedido FROM Pedido pedido " +
            "INNER JOIN pedido.produtos produtos " +
            "INNER JOIN pedido.usuario usuario WHERE " +
            "(:id = usuario.id) AND " +
            "(:numero IS NULL OR :numero = pedido.id)")
    fun findAllByUsuarioId(id: Long, numero: Long?, pagina: Pageable): List<Pedido>
}