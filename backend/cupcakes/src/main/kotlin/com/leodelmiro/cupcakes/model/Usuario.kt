package com.leodelmiro.cupcakes.model

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "tb_usuario")
data class Usuario(
        @field:Id
        @field:GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,
        @field:Column(nullable = false)
        var nome: String,
        @field:Column(nullable = false, unique = true)
        val cpf: String,
        @field:Column(nullable = false, unique = true)
        val email: String,
        @field:Column(nullable = false)
        private val password: String,
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
) : UserDetails {

    fun temRole(roleName: String): Boolean = roles.map { role -> role.autoridade }.contains(roleName)

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> =
            roles.map { role -> SimpleGrantedAuthority(role.autoridade) }.toMutableList()

    override fun getPassword(): String = password

    override fun getUsername(): String = email

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = true
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Usuario

        if (id != other.id) return false
        if (nome != other.nome) return false
        if (cpf != other.cpf) return false
        if (email != other.email) return false
        if (password != other.password) return false
        if (telefone != other.telefone) return false
        if (endereco != other.endereco) return false
        if (roles != other.roles) return false
        if (criadoEm != other.criadoEm) return false
        if (atualizadoEm != other.atualizadoEm) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + nome.hashCode()
        result = 31 * result + cpf.hashCode()
        result = 31 * result + email.hashCode()
        result = 31 * result + password.hashCode()
        result = 31 * result + telefone.hashCode()
        result = 31 * result + endereco.hashCode()
        result = 31 * result + roles.hashCode()
        result = 31 * result + criadoEm.hashCode()
        result = 31 * result + (atualizadoEm?.hashCode() ?: 0)
        return result
    }
}