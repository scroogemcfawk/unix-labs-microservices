package com.github.scroogemcfawk.unix_lab_microservices.calculator_service

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.context.annotation.ComponentScan

public object ApplicationContextHolder {
    lateinit var value: ConfigurableApplicationContext
}

@SpringBootApplication
open class CalculatorService


fun main() {
    ApplicationContextHolder.value = SpringApplication.run(CalculatorService::class.java)
}
