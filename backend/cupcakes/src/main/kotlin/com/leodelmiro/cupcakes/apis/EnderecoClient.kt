package com.leodelmiro.cupcakes.apis

import com.leodelmiro.cupcakes.dto.EnderecoClientResponse


interface EnderecoClient {
    fun findCityByCep(cep: String?): EnderecoClientResponse?
}