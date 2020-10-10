package com.bhuvancom.ecom

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

//@EnableSwagger2
@SpringBootApplication
class EComApplication

fun main(args: Array<String>) {
    runApplication<EComApplication>(*args)
}

