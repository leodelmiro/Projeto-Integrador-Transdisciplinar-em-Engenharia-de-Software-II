package com.leodelmiro.cupcakes.services

import com.leodelmiro.cupcakes.dto.EnderecoClientResponse.Companion.toEnderecoDTO
import com.leodelmiro.cupcakes.dto.EnderecoDTO
import com.leodelmiro.cupcakes.model.MetodoPagamento
import com.leodelmiro.cupcakes.model.Pagamento
import com.leodelmiro.cupcakes.repositories.PagamentoRepository
import com.leodelmiro.cupcakes.services.exceptions.RecursoNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PagamentoService(@Autowired val pagamentoRepository: PagamentoRepository) {

    @Transactional
    fun pagar(metodoPagamento: MetodoPagamento): Pagamento =
            pagamentoRepository.save(Pagamento(metodoPagamento = metodoPagamento))
}
