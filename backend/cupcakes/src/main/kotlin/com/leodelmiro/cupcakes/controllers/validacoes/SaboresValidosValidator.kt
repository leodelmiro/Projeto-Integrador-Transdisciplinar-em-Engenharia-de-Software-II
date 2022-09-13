package com.leodelmiro.cupcakes.controllers.validacoes

import com.leodelmiro.cupcakes.services.SaborService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

@Component
class SaboresValidosValidator(@Autowired val saborService: SaborService) : ConstraintValidator<SaboresValidos, List<Long>> {
    override fun isValid(value: List<Long>, context: ConstraintValidatorContext?): Boolean =
            !value.map { sabor -> saborService.isSaborExistente(sabor) }.contains(false)
}
