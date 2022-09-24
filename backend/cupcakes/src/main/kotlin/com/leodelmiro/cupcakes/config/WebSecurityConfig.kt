package com.leodelmiro.cupcakes.config

import com.leodelmiro.cupcakes.services.UsuarioService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder


@Configuration
@EnableWebSecurity
class WebSecurityConfig(
        @Autowired private val passwordEncoder: BCryptPasswordEncoder,
        @Autowired private val usuarioService: UsuarioService
) : WebSecurityConfigurerAdapter() {

    override fun configure(web: WebSecurity) {
        web.ignoring().antMatchers("/actuator/**")
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(usuarioService).passwordEncoder(passwordEncoder)
    }

    @Bean
    override fun authenticationManager(): AuthenticationManager = super.authenticationManager()

}