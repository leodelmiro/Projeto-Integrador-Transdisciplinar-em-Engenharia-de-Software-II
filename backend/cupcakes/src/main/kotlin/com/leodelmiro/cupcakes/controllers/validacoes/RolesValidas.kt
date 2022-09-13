package com.leodelmiro.cupcakes.controllers.validacoes

import javax.validation.Constraint
import javax.validation.Payload
import kotlin.reflect.KClass

@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FIELD)
@Constraint(validatedBy = [RolesValidasValidator::class])
annotation class RolesValidas(
        val message: String = "Roles devem existir",
        val groups: Array<KClass<Any>> = [],
        val payload: Array<KClass<Payload>> = []
)
