package com.leodelmiro.cupcakes.controllers.validacoes

import com.leodelmiro.cupcakes.services.UsuarioService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

@Component
class EmailUnicoValidator (@Autowired val usuarioService: UsuarioService) : ConstraintValidator<EmailUnico, String> {
    override fun isValid(value: String, context: ConstraintValidatorContext?): Boolean =
            !usuarioService.isEmailJaExistente(value)
}
