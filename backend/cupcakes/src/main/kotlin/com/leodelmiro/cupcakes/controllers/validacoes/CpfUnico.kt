package com.leodelmiro.cupcakes.controllers.validacoes

import javax.validation.Constraint
import javax.validation.Payload
import kotlin.reflect.KClass

@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FIELD)
@Constraint(validatedBy = [CpfUnicoValidator::class])
annotation class CpfUnico(
        val message: String = "CPF já existente",
        val groups: Array<KClass<Any>> = [],
        val payload: Array<KClass<Payload>> = []
)