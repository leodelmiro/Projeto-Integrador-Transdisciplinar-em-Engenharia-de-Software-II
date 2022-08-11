package com.leodelmiro.cupcakes.model

import java.time.LocalDateTime

interface Pagamento {
    val id: Long
    val horarioPagamento: LocalDateTime
}