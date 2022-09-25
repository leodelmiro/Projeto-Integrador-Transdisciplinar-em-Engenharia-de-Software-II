package com.leodelmiro.cupcakes.components

import com.leodelmiro.cupcakes.services.UsuarioService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken
import org.springframework.security.oauth2.common.OAuth2AccessToken
import org.springframework.security.oauth2.provider.OAuth2Authentication
import org.springframework.security.oauth2.provider.token.TokenEnhancer
import org.springframework.stereotype.Component


@Component
class JwtTokenEnhancer(@Autowired private val usuarioService: UsuarioService) : TokenEnhancer {

    override fun enhance(accessToken: OAuth2AccessToken, authentication: OAuth2Authentication): OAuth2AccessToken {
        with(usuarioService.encontrarPorEmail(authentication.name)) {
            val token = accessToken as DefaultOAuth2AccessToken
            token.additionalInformation = mutableMapOf(
                    Pair("usuario_id", this.id),
                    Pair("usuario_nome", this.nome)
            ) as Map<String, Any>
            return token
        }
    }
}