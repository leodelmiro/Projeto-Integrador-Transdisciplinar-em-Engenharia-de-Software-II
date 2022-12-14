package com.leodelmiro.cupcakes.repositories

import com.leodelmiro.cupcakes.model.Usuario
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UsuarioRepository : JpaRepository<Usuario, Long> {
    fun findByEmail(email: String): Optional<Usuario>
    fun findByCpf(cpf: String): Optional<Usuario>
}