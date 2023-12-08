package com.github.scroogemcfawk.unix_lab_microservices.api_service

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.retry.support.Args

object ApplicationContext {
    var queue_size: Int = 0
    var worker_count: Int = 0
}


@SpringBootApplication
open class ClientService

fun main(args: Array<String>) {
    runApplication<ClientService>(*args)
}
