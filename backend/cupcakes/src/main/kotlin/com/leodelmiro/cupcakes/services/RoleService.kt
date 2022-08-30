package com.leodelmiro.cupcakes.services

import com.leodelmiro.cupcakes.dto.RoleResponseDTO
import com.leodelmiro.cupcakes.repositories.RoleRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class RoleService(@Autowired val roleRepository: RoleRepository) {

    @Transactional(readOnly = true)
    fun encontrarTodos(): List<RoleResponseDTO> = roleRepository.findAll().map { role -> RoleResponseDTO(role) }
}
