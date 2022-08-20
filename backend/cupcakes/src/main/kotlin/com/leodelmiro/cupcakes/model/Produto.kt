package com.leodelmiro.cupcakes.model

import java.math.BigDecimal
import javax.persistence.*

@Entity(name = "tb_produto")
class Produto(
        @field:Id
        @field:GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,
        @field:Column(nullable = false)
        val nome: String,
        @field:Column(nullable = false)
        val quantidade: Int,
        @field:Column(nullable = false)
        val preco: BigDecimal,
        @field:Column(nullable = false)
        val descricao: String,
        @field:ManyToMany(fetch = FetchType.EAGER)
        @field:JoinTable(name = "tb_produto_sabor",
                joinColumns = [JoinColumn(name = "produto_id")],
                inverseJoinColumns = [JoinColumn(name = "sabor_id")])
        val sabor: Set<Sabor>,
        @OneToMany
        @JoinColumn(name = "foto")
        val foto: List<Foto>
)