package com.leodelmiro.cupcakes.repositories

import com.leodelmiro.cupcakes.model.Produto
import com.leodelmiro.cupcakes.model.Sabor
import com.leodelmiro.cupcakes.model.Usuario
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SaborRepository : JpaRepository<Sabor, Long> {
}