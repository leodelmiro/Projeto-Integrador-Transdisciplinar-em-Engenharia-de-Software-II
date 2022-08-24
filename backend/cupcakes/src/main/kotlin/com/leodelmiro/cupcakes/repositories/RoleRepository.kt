package com.leodelmiro.cupcakes.repositories

import com.leodelmiro.cupcakes.model.Role
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RoleRepository : JpaRepository<Role, Long> {
}