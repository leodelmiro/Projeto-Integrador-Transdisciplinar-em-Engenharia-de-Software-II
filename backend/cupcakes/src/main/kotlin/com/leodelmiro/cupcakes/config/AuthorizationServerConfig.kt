package com.leodelmiro.cupcakes.config

import com.leodelmiro.cupcakes.components.JwtTokenEnhancer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurer
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore


@Configuration
@EnableAuthorizationServer
class AuthorizationServerConfig(
        @Autowired private val passwordEncoder: BCryptPasswordEncoder,
        @Autowired private val accessTokenConverter: JwtAccessTokenConverter,
        @Autowired private val tokenStore: JwtTokenStore,
        @Autowired private val authenticationManager: AuthenticationManager,
        @Autowired private val jwtTokenEnhancer: JwtTokenEnhancer,
        @Value("\${security.oauth2.client.client-secret}") private val clientSecret: String,
        @Value("\${security.oauth2.client.client-id}") private val clientId: String,
        @Value("\${jwt.duration}") private val jwtDuration: Int
) : AuthorizationServerConfigurer {

    override fun configure(security: AuthorizationServerSecurityConfigurer) {
        security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
    }

    override fun configure(clients: ClientDetailsServiceConfigurer) {
        clients.inMemory()
                .withClient(clientId)
                .secret(passwordEncoder.encode(clientSecret))
                .scopes("read", "write")
                .authorizedGrantTypes("password")
                .accessTokenValiditySeconds(jwtDuration)
    }

    override fun configure(endpoints: AuthorizationServerEndpointsConfigurer) {
        val chain = TokenEnhancerChain()
        chain.setTokenEnhancers(listOf(jwtTokenEnhancer, accessTokenConverter))

        endpoints.authenticationManager(authenticationManager)
                .tokenStore(tokenStore)
                .accessTokenConverter(accessTokenConverter)
                .tokenEnhancer(chain)
    }
}