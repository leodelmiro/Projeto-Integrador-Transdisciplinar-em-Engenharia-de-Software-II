package com.leodelmiro.cupcakes.controllers

import com.leodelmiro.cupcakes.dto.EnderecoDTO
import com.leodelmiro.cupcakes.services.EnderecoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping(value = ["/enderecos"])
class EnderecoController(
        @Autowired val enderecoService: EnderecoService,
) {
    @GetMapping(value = ["/{cep}"])
    fun encontrarPorCep(@PathVariable cep: String): ResponseEntity<EnderecoDTO> =
            if (isValidCep(cep)) {
                enderecoService.encontrarCidadePorCep(cep).let {
                    ResponseEntity.ok().body(it)
                }
            } else throw IllegalArgumentException("Formato de CEP deve conter apenas 8 números")

    private fun isValidCep(cep: String) = "^[\\d]{8}".toRegex().matches(cep)

}