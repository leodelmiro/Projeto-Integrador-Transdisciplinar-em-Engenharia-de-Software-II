package com.leodelmiro.cupcakes.model

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import javax.persistence.*

@Entity
class Usuario(
        @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,
        @field:Column(nullable = false, unique = true)
        val cpf: String,
        @field:Column(nullable = false, unique = true)
        val email: String,
        @field:Column(nullable = false)
        val password: String,
        @field:Embedded
        val telefone: Telefone,
        @field:Embedded
        val endereco: Endereco,
        @field:Column(nullable = false)
        val role: Set<Role>,
        @field:CreationTimestamp
        @field:Column(nullable = false, updatable = false)
        val criadoEm: LocalDateTime,
        @field:UpdateTimestamp
        @field:Column(nullable = true, updatable = true)
        val atualizadoEm: LocalDateTime
)