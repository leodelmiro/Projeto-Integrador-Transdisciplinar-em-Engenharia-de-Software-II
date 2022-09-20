package com.leodelmiro.cupcakes.controllers

import com.leodelmiro.cupcakes.dto.EnderecoDTO
import com.leodelmiro.cupcakes.services.EnderecoService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping(value = ["/enderecos"])
@Tag(name = "Endereço")
class EnderecoController(
        @Autowired val enderecoService: EnderecoService,
) {
    @GetMapping(value = ["/{cep}"])
    @Operation(summary = "Encontra endereço por CEP")
    fun encontrarPorCep(@PathVariable cep: String): ResponseEntity<EnderecoDTO> =
            if (isValidCep(cep)) {
                enderecoService.encontrarCidadePorCep(cep).let {
                    ResponseEntity.ok().body(it)
                }
            } else throw IllegalArgumentException("Formato de CEP deve conter apenas 8 números")

    private fun isValidCep(cep: String) = "^[\\d]{8}".toRegex().matches(cep)

}