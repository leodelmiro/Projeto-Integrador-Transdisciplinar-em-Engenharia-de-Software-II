package com.leodelmiro.cupcakes.repositories

import com.leodelmiro.cupcakes.model.Sabor
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface SaborRepository : JpaRepository<Sabor, Long> {

    @Query("SELECT sabor FROM Sabor sabor WHERE " +
            "(:nome IS NULL OR (LOWER(sabor.nome) LIKE LOWER(CONCAT('%',:nome, '%'))))")
    fun find(nome: String?, pagina: Pageable): Page<Sabor>
}