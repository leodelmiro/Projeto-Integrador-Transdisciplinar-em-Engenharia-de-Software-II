package com.leodelmiro.cupcakes.model

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.math.BigDecimal
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "tb_produto")
data class Produto(
        @field:Id
        @field:GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,
        @field:Column(nullable = false)
        var nome: String,
        @field:Column(nullable = false)
        var quantidade: Int,
        @field:Column(nullable = false)
        var preco: BigDecimal,
        @field:Column(nullable = false)
        var descricao: String,
        @field:ManyToMany(fetch = FetchType.EAGER)
        @field:JoinTable(
                name = "tb_produto_sabor",
                joinColumns = [JoinColumn(name = "produto_id")],
                inverseJoinColumns = [JoinColumn(name = "sabor_id")])
        var sabores: Set<Sabor> = mutableSetOf(),
        @field:Embedded
        val foto: Foto,
        @field:CreationTimestamp
        @field:Column(nullable = false, updatable = false)
        val criadoEm: LocalDateTime = LocalDateTime.now(),
        @field:UpdateTimestamp
        @field:Column(nullable = true, updatable = true)
        val atualizadoEm: LocalDateTime? = null
) {
        fun addSabor(sabor: Sabor) = this.sabores + sabor
}