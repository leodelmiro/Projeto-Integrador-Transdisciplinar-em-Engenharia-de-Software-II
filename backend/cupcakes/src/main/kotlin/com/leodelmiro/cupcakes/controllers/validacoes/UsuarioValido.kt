package com.leodelmiro.cupcakes.controllers.validacoes

import javax.validation.Constraint
import javax.validation.Payload
import kotlin.reflect.KClass

@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FIELD)
@Constraint(validatedBy = [UsuarioValidoValidator::class])
annotation class UsuarioValido(
        val message: String = "Usuario deve existir",
        val groups: Array<KClass<Any>> = [],
        val payload: Array<KClass<Payload>> = []
)
