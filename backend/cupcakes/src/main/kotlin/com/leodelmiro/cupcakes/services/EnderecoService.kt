package com.leodelmiro.cupcakes.services

import com.leodelmiro.cupcakes.apis.EnderecoClient
import com.leodelmiro.cupcakes.dto.EnderecoDTO
import com.leodelmiro.cupcakes.dto.EnderecoClientResponse.Companion.toEnderecoDTO
import com.leodelmiro.cupcakes.services.exceptions.RecursoNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class EnderecoService(@Autowired val cepApi: EnderecoClient) {

    @Transactional
    fun encontrarCidadePorCep(cep: String): EnderecoDTO? =
            try {
                cepApi.findCityByCep(cep)?.toEnderecoDTO()
            } catch (e: Exception) {
                throw RecursoNotFoundException("Ocorreu um erro ao recuperar Cidade!")
            }
}
