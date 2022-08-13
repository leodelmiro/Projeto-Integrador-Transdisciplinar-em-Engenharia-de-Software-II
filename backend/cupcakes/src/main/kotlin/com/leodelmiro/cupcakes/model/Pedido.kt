package com.leodelmiro.cupcakes.model

import org.hibernate.annotations.CreationTimestamp
import java.math.BigDecimal
import java.time.LocalDateTime
import javax.persistence.*

@Entity
class Pedido(
        @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,
        @field:CreationTimestamp
        @field:Column(nullable = false, updatable = false)
        val horarioDeCompra: LocalDateTime = LocalDateTime.now(),
        @field:Enumerated(EnumType.STRING)
        val status: Status,
        @field:Column(nullable = false, updatable = false)
        val valor: BigDecimal,
        @field:Column(nullable = false, updatable = false)
        val code: String,
        @OneToOne
        @JoinColumn(name = "usuario_id")
        val usuario: Usuario,
        @OneToMany(mappedBy = "produto")
        val produtos: List<Produto>
)