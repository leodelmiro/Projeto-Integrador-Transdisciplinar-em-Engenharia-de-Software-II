package com.leodelmiro.cupcakes.controllers

import com.leodelmiro.cupcakes.dto.RoleResponseDTO
import com.leodelmiro.cupcakes.services.RoleService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping(value = ["/roles"])
class RoleController(
        @Autowired val roleService: RoleService,
) {

    // TODO DEIXAR APENAS ADMIN
    @GetMapping
    fun encontrarTodos(): ResponseEntity<List<RoleResponseDTO>> =
            roleService.encontrarTodos().let {
                ResponseEntity.ok().body(it)
            }
}