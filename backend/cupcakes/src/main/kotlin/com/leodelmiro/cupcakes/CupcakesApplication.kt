package com.leodelmiro.cupcakes

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@EnableFeignClients
@SpringBootApplication
class CupcakesApplication

fun main(args: Array<String>) {
	runApplication<CupcakesApplication>(*args)
}
