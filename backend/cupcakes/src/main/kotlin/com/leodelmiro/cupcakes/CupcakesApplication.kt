package com.leodelmiro.cupcakes

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CupcakesApplication

fun main(args: Array<String>) {
	runApplication<CupcakesApplication>(*args)
}
