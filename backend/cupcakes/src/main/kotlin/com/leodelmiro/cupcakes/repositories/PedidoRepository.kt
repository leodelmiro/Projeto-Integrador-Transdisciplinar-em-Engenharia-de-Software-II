package com.leodelmiro.cupcakes.repositories

import com.leodelmiro.cupcakes.model.Pedido
import com.leodelmiro.cupcakes.model.Produto
import com.leodelmiro.cupcakes.model.Sabor
import com.leodelmiro.cupcakes.model.Usuario
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.math.BigDecimal

@Repository
interface PedidoRepository : JpaRepository<Pedido, Long> {

}