package com.leodelmiro.cupcakes.controllers.validacoes

import javax.validation.Constraint
import javax.validation.Payload
import kotlin.reflect.KClass

@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
@Constraint(validatedBy = [ProdutoEstoqueValidator::class])
annotation class ProdutoEstoque(
        val message: String = "Produto sem quantidade suficiente para pedido",
        val groups: Array<KClass<Any>> = [],
        val payload: Array<KClass<Payload>> = []
)
