package com.leodelmiro.cupcakes.controllers.validacoes

import com.leodelmiro.cupcakes.dto.ProdutoEmPedidoDTO
import com.leodelmiro.cupcakes.services.ProdutoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

@Component
class ProdutoEstoqueValidator(@Autowired val produtoService: ProdutoService) :
        ConstraintValidator<ProdutoEstoque, ProdutoEmPedidoDTO> {
    override fun isValid(value: ProdutoEmPedidoDTO, context: ConstraintValidatorContext?): Boolean =
            produtoService.encontrarPorId(value.id).quantidade >= value.quantidade
}
