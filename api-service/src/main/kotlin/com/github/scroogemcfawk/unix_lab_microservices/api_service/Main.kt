package com.github.scroogemcfawk.unix_lab_microservices.api_service

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.retry.support.Args

object ApplicationContext {
    public var queue_size: Int = 1
    public var worker_count: Int = 1
}


@SpringBootApplication
open class ClientService

fun main(args: Array<String>) {
    runApplication<ClientService>(*args)
}
