package com.leodelmiro.cupcakes.model

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "tb_produto_pedido")
class ProdutoPedido(
        @field:Id
        @field:GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,
        @field:ManyToOne
        @field:JoinColumn(name = "produto_id")
        val produto: Produto,
        @field:ManyToOne
        @field:JoinColumn(name = "pedido_id")
        val pedido: Pedido,
        @field:Column(nullable = false, updatable = false)
        val quantidade: Int,
        @field:CreationTimestamp
        @field:Column(nullable = false, updatable = false)
        val criadoEm: LocalDateTime = LocalDateTime.now(),
        @field:UpdateTimestamp
        @field:Column(nullable = true, updatable = true)
        val atualizadoEm: LocalDateTime? = null
)