package com.leodelmiro.cupcakes.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.Ordered
import org.springframework.core.env.Environment
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.filter.CorsFilter


@Configuration
@EnableResourceServer
class ResourceServerConfig(
        @Autowired private val jwtTokenStore: JwtTokenStore,
        @Autowired private val environment: Environment,
) : ResourceServerConfigurerAdapter() {

    override fun configure(resources: ResourceServerSecurityConfigurer) {
        resources.tokenStore(jwtTokenStore)
    }

    override fun configure(http: HttpSecurity) {
        if (environment.activeProfiles.contains("test")) http.headers().frameOptions().disable();

        http.authorizeRequests()
                .antMatchers(*PUBLICO).permitAll()
                .antMatchers(HttpMethod.POST, USUARIO_ENDPOINT).permitAll()
                .antMatchers(HttpMethod.GET, *PUBLICO_OR_VENDEDOR).permitAll()
                .antMatchers(HttpMethod.PUT, USUARIO_ENDPOINT).hasAnyRole("USUARIO", "VENDEDOR", "ADMIN")
                .antMatchers(HttpMethod.GET, USUARIO_ENDPOINT).hasAnyRole("USUARIO", "VENDEDOR", "ADMIN")
                .antMatchers(HttpMethod.GET, *USUARIO_OR_VENDEDOR).hasAnyRole("USUARIO", "VENDEDOR", "ADMIN")
                .antMatchers(*PUBLICO_OR_VENDEDOR).hasAnyRole("VENDEDOR", "ADMIN")
                .antMatchers(*ADMIN).hasAnyRole("ADMIN")
                .anyRequest().authenticated()

        http.cors().configurationSource(corsConfigurationSource());
    }

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val corsConfig = CorsConfiguration().apply {
            this.allowedOriginPatterns = listOf("*")
            this.allowedMethods = listOf("POST", "GET", "PUT", "DELETE", "PATCH")
            this.allowCredentials = true
            this.allowedHeaders = listOf("Authorization", "Content-Type")
        }

        return UrlBasedCorsConfigurationSource().apply {
            this.registerCorsConfiguration("/**", corsConfig)
        }
    }

    @Bean
    fun corsFilter(): FilterRegistrationBean<CorsFilter> =
            FilterRegistrationBean(CorsFilter(corsConfigurationSource())).apply {
                this.setOrder(Ordered.HIGHEST_PRECEDENCE)
            }

    companion object {
        private const val USUARIO_ENDPOINT = "/usuarios/**"
        private val PUBLICO: Array<String> = arrayOf("/oauth/token", "/h2-console/**")
        private val PUBLICO_OR_VENDEDOR = arrayOf("/produtos/**", "/sabores/**", "/enderecos/**")
        private val USUARIO_OR_VENDEDOR = arrayOf("/pedidos/**")
        private val ADMIN = arrayOf("/roles/**", "/usuarios/**", "/pedidos/**")
    }
}