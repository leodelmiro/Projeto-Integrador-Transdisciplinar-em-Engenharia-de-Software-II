package com.leodelmiro.cupcakes.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore


@Configuration
class AppConfig(@Value("\${jwt.secret}") private val jwtSecret: String) {
    @Bean
    fun passwordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun accessTokenConverter(): JwtAccessTokenConverter? = with(JwtAccessTokenConverter()) {
        this.setSigningKey(jwtSecret)
        this
    }

    @Bean
    fun tokenStore(): JwtTokenStore? = JwtTokenStore(accessTokenConverter())

}