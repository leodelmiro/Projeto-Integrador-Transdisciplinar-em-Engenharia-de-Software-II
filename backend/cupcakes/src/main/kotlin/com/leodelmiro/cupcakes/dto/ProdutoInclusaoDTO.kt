package com.leodelmiro.cupcakes.dto

import com.leodelmiro.cupcakes.controllers.validacoes.SaboresValidos
import com.leodelmiro.cupcakes.model.Foto
import com.leodelmiro.cupcakes.model.Produto
import com.leodelmiro.cupcakes.repositories.SaborRepository
import java.math.BigDecimal
import javax.validation.Valid
import javax.validation.constraints.*

data class ProdutoInclusaoDTO(
        @field:NotBlank(message = "Nome não pode ser em branco.")
        val nome: String,
        @field:Min(value = 1, message = "Quantidade deve ter ser no mínimo 1")
        val quantidade: Int,
        @field:PositiveOrZero(message = "Preço deve ser 0 ou maior")
        val preco: BigDecimal,
        @field:NotBlank(message = "Descrição não pode ser em branco")
        val descricao: String,
        @field:NotNull(message = "Sabor não pode ser nulo.")
        @field:Size(min = 1, message = "O produto deve ter no mínimo 1 sabor")
        @field:SaboresValidos
        val sabores: List<Long>,
        @field:Valid
        @field:NotNull(message = "Foto não pode ser nula.")
        val foto: FotoDTO
) {

    companion object {
        fun ProdutoInclusaoDTO.toEntidade(saborRepository: SaborRepository) = Produto(
                nome = this.nome,
                quantidade = this.quantidade,
                preco = this.preco,
                descricao = this.descricao,
                sabores = this.sabores.map { saborId -> saborRepository.getReferenceById(saborId) }.toSet(),
                foto = Foto(url = this.foto.url)
        )
    }
}