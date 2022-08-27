package com.leodelmiro.cupcakes.controllers

import com.leodelmiro.cupcakes.dto.EnderecoDTO
import com.leodelmiro.cupcakes.dto.UsuarioAtualizacaoDTO
import com.leodelmiro.cupcakes.dto.UsuarioInclusaoDTO
import com.leodelmiro.cupcakes.dto.UsuarioResponseDTO
import com.leodelmiro.cupcakes.services.EnderecoService
import com.leodelmiro.cupcakes.services.UsuarioService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import javax.validation.Valid


@RestController(value = "/enderecos")
class EnderecoController(
        @Autowired val enderecoService: EnderecoService,
) {
    @GetMapping(value = ["/{cep}"])
    fun encontrarPorCep(@PathVariable cep: String): ResponseEntity<EnderecoDTO> =
            if (isValidCep(cep)) {
                enderecoService.findCityByCep(cep).let {
                    ResponseEntity.ok().body(it)
                }
            } else throw IllegalArgumentException("Formato de CEP deve conter apenas 8 números")

    private fun isValidCep(cep: String) = "/^[0-9]{8}".toRegex().matches(cep)

}