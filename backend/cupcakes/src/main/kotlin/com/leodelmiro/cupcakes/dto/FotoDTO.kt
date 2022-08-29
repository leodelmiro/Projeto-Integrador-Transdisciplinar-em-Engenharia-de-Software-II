package com.leodelmiro.cupcakes.dto

import com.leodelmiro.cupcakes.model.Foto
import com.leodelmiro.cupcakes.model.Produto
import org.hibernate.validator.constraints.URL

class FotoDTO(
        @URL(protocol = "http")
        val url: String
) {
        companion object {
                fun FotoDTO.toEntidade(produto: Produto) = Foto(url = url, produto = produto)
        }
}
