package com.leodelmiro.cupcakes.model

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.math.BigDecimal
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "tb_pedido")
class Pedido(
        @field:Id
        @field:GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,
        @field:Enumerated(EnumType.STRING)
        var status: Status,
        @field:Column(nullable = false, updatable = false)
        val valor: BigDecimal,
        @field:Column(nullable = false, updatable = false)
        val code: String,
        @field:ManyToOne
        @field:JoinColumn(name = "usuario_id")
        val usuario: Usuario,
        @field:OneToMany(mappedBy = "pedido", cascade = [CascadeType.ALL])
        val produtos: MutableList<ProdutoPedido>,
        @field:CreationTimestamp
        @field:Column(nullable = false, updatable = false)
        val horarioDeCompra: LocalDateTime = LocalDateTime.now(),
        @field:UpdateTimestamp
        @field:Column(nullable = true, updatable = true)
        val atualizadoEm: LocalDateTime? = null
)