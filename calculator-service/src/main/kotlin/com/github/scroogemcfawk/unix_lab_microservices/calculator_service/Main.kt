package com.github.scroogemcfawk.unix_lab_microservices.calculator_service

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication


@SpringBootApplication
open class CalculatorService


fun main() {
    SpringApplication.run(CalculatorService::class.java)
}
