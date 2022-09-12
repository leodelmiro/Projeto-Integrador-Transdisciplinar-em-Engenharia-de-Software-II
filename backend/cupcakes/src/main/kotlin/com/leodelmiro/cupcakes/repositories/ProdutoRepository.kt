package com.leodelmiro.cupcakes.repositories

import com.leodelmiro.cupcakes.model.Produto
import com.leodelmiro.cupcakes.model.Sabor
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.math.BigDecimal

@Repository
interface ProdutoRepository : JpaRepository<Produto, Long> {
    @Query("SELECT produto FROM Produto produto INNER JOIN produto.sabores sabores WHERE " +
            "(:sabor IS NULL OR sabores IN :sabor) AND " +
            "(:precoMin IS NULL OR produto.preco >= :precoMin) AND " +
            "(:precoMax IS NULL OR produto.preco <= :precoMax)")
    fun find(sabor: Sabor?, precoMin: BigDecimal?, precoMax: BigDecimal?, pagina: Pageable): Page<Produto>
}