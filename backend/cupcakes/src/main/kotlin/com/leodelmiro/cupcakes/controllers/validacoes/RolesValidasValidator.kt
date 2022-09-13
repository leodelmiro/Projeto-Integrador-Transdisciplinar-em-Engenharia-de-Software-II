package com.leodelmiro.cupcakes.controllers.validacoes

import com.leodelmiro.cupcakes.services.RoleService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

@Component
class RolesValidasValidator(@Autowired val roleService: RoleService) : ConstraintValidator<RolesValidas, List<Long>> {
    override fun isValid(value: List<Long>, context: ConstraintValidatorContext?): Boolean =
            !value.map { role -> roleService.isRoleExistente(role) }.contains(false)
}
