package com.leodelmiro.cupcakes.model

import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime
import javax.persistence.*

@Entity
abstract class Pagamento(
        @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long,
        @field:CreationTimestamp
        @field:Column(nullable = false, updatable = false)
        val horarioPagamento: LocalDateTime = LocalDateTime.now(),
        @Enumerated(EnumType.STRING)
        val metodoPagamento: MetodoPagamento
)