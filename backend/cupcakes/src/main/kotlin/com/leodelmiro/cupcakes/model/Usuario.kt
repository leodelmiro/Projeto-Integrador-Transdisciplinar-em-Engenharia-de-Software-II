package com.leodelmiro.cupcakes.model

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import javax.persistence.*

@Entity(name = "tb_usuario")
class Usuario(
        @field:Id
        @field:GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,
        @field:Column(nullable = false, unique = true)
        var nome: String,
        @field:Column(nullable = false, unique = true)
        val cpf: String,
        @field:Column(nullable = false, unique = true)
        val email: String,
        @field:Column(nullable = false)
        val password: String,
        @field:Embedded
        var telefone: Telefone,
        @field:Embedded
        var endereco: Endereco,
        @field:ManyToMany(fetch = FetchType.EAGER)
        @field:JoinTable(name = "tb_usuarios_role",
                joinColumns = [JoinColumn(name = "usuario_id")],
                inverseJoinColumns = [JoinColumn(name = "role_id")])
        val roles: Set<Role>,
        @field:CreationTimestamp
        @field:Column(nullable = false, updatable = false)
        val criadoEm: LocalDateTime = LocalDateTime.now(),
        @field:UpdateTimestamp
        @field:Column(nullable = true, updatable = true)
        val atualizadoEm: LocalDateTime? = null
)