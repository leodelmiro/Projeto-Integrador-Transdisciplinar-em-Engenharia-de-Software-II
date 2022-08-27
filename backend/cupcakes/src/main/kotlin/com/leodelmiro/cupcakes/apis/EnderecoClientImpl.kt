package com.leodelmiro.cupcakes.apis

import com.leodelmiro.cupcakes.dto.EnderecoClientResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod


@FeignClient(name = "enderecoClient", url = "https://viacep.com.br/ws")
interface EnderecoClientImpl: EnderecoClient {
    @RequestMapping(
            method = [RequestMethod.GET],
            value = ["/{cep}/json/"],
            consumes = ["application/json"]
    )
    override fun findCityByCep(@PathVariable("cep") cep: String?): EnderecoClientResponse?
}