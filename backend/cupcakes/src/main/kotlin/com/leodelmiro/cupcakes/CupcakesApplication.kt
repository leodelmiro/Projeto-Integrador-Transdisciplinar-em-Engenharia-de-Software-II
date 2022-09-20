package com.leodelmiro.cupcakes

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info
import io.swagger.v3.oas.annotations.info.License
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@EnableFeignClients
@SpringBootApplication
@OpenAPIDefinition(
        info = Info(title = "Cupcakes Backend API", description = "Cupcakes Backend REST API - Projeto Integrador",
                version = "v1.0.0",
                license = License(name = "Apache 2.0", url = "https://www.apache.org/licenses/LICENSE-2.0")
        )
)
class CupcakesApplication

fun main(args: Array<String>) {
    runApplication<CupcakesApplication>(*args)
}
