package com.leodelmiro.cupcakes.model

import java.math.BigDecimal
import java.time.LocalDateTime

class Pedido(
        horarioDeCompra: LocalDateTime = LocalDateTime.now(),
        status: Status,
        valor: BigDecimal,
        code: String?
)