package com.leodelmiro.cupcakes.model

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "tb_foto")
data class Foto(
        @field:Id
        @field:GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,
        @field:Column(nullable = false)
        val url: String,
        @field:CreationTimestamp
        @field:Column(nullable = false, updatable = false)
        val criadoEm: LocalDateTime = LocalDateTime.now(),
        @field:UpdateTimestamp
        @field:Column(nullable = true, updatable = true)
        val atualizadoEm: LocalDateTime? = null,
        @ManyToOne(fetch = FetchType.LAZY)
        @field:JoinColumn(name = "produto_id")
        val produto: Produto
)