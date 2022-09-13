package com.leodelmiro.cupcakes.controllers.validacoes

import javax.validation.Constraint
import javax.validation.Payload
import kotlin.reflect.KClass

@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FIELD)
@Constraint(validatedBy = [ProdutoValidoValidator::class])
annotation class ProdutoValido(
        val message: String = "Produto deve existir",
        val groups: Array<KClass<Any>> = [],
        val payload: Array<KClass<Payload>> = []
)
