package com.leodelmiro.cupcakes.model

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.math.BigDecimal
import java.time.LocalDateTime
import javax.persistence.*

@Entity(name = "tb_pedido")
class Pedido(
        @field:Id
        @field:GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,
        @field:Enumerated(EnumType.STRING)
        val status: Status,
        @field:Column(nullable = false, updatable = false)
        val valor: BigDecimal,
        @field:Column(nullable = false, updatable = false)
        val code: String,
        @field:ManyToOne
        @field:JoinColumn(name = "usuario_id")
        val usuario: Usuario,
        @field:OneToMany
        @field:JoinColumn(name = "produto_id")
        val produtos: List<Produto>,
        @field:CreationTimestamp
        @field:Column(nullable = false, updatable = false)
        val horarioDeCompra: LocalDateTime = LocalDateTime.now(),
        @field:UpdateTimestamp
        @field:Column(nullable = true, updatable = true)
        val atualizadoEm: LocalDateTime? = null
)