package com.leodelmiro.cupcakes.model

import java.math.BigDecimal
import javax.persistence.*

@Entity(name = "tb_produto")
class Produto(
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
        @field:OneToMany(
                mappedBy = "produto",
                fetch = FetchType.EAGER,
                cascade = [CascadeType.ALL],
                orphanRemoval = true
        )
        val fotos: List<Foto> = mutableListOf()
) {
        fun addSabor(sabor: Sabor) = this.sabores + sabor
        fun addFoto(foto: Foto) = this.fotos + foto
}