package com.leodelmiro.cupcakes.services

import com.leodelmiro.cupcakes.model.Usuario
import com.leodelmiro.cupcakes.repositories.UsuarioRepository
import com.leodelmiro.cupcakes.services.exceptions.ForbiddenException
import com.leodelmiro.cupcakes.services.exceptions.UnauthorizedException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AuthService(@Autowired private val usuarioRepository: UsuarioRepository) {

    @Transactional(readOnly = true)
    fun authenticated(): Usuario =
            try {
                val username = SecurityContextHolder.getContext().authentication.name
                usuarioRepository.findByEmail(username).get()
            } catch (ex: Exception) {
                throw UnauthorizedException("Usuário inválido")
            }

    fun validataSeDeleOuAdmin(idUsuario: Long) = authenticated().let { usuario ->
        if ((usuario.id != idUsuario) && !(usuario.temRole("ROLE_ADMIN")))
            throw ForbiddenException("Acesso negado")
    }
}
