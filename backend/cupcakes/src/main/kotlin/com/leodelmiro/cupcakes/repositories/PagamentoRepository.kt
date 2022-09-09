package com.leodelmiro.cupcakes.repositories

import com.leodelmiro.cupcakes.model.Pagamento
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PagamentoRepository : JpaRepository<Pagamento, Long> {
}