package com.leodelmiro.cupcakes.controllers.validacoes

import com.leodelmiro.cupcakes.services.ProdutoService
import com.leodelmiro.cupcakes.services.UsuarioService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

@Component
class ProdutoValidoValidator (@Autowired val produtoService: ProdutoService) : ConstraintValidator<ProdutoValido, Long> {
    override fun isValid(value: Long, context: ConstraintValidatorContext?): Boolean =
            produtoService.isProdutoExistente(value)
}
