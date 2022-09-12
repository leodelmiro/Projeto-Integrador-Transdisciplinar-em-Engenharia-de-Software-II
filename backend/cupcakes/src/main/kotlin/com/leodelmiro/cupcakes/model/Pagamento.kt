package com.leodelmiro.cupcakes.model

import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "tb_pagamento")
data class Pagamento(
        @field:Id
        @field:GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,
        @field:CreationTimestamp
        @field:Column(nullable = false, updatable = false)
        val horarioPagamento: LocalDateTime = LocalDateTime.now(),
        @field:Enumerated(EnumType.STRING)
        val metodoPagamento: MetodoPagamento
)