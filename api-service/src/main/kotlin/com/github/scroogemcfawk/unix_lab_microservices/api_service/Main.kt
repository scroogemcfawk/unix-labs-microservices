package com.github.scroogemcfawk.unix_lab_microservices.api_service

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication


@SpringBootApplication
open class ClientService

fun main(args: Array<String>) {
    runApplication<ClientService>(*args)
}
