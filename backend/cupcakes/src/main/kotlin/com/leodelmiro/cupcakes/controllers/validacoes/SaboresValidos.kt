package com.leodelmiro.cupcakes.controllers.validacoes

import javax.validation.Constraint
import javax.validation.Payload
import kotlin.reflect.KClass

@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FIELD)
@Constraint(validatedBy = [SaboresValidosValidator::class])
annotation class SaboresValidos(
        val message: String = "Sabores devem existir",
        val groups: Array<KClass<Any>> = [],
        val payload: Array<KClass<Payload>> = []
)
