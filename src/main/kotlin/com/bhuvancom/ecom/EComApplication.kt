package com.bhuvancom.ecom

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration


//@EnableSwagger2
@EnableAutoConfiguration
@Configuration
@SpringBootApplication
@ComponentScan(basePackages = ["com.bhuvancom.*"])
class EComApplication

fun main(args: Array<String>) {
    runApplication<EComApplication>(*args)
}

