package com.github.scroogemcfawk.unix_lab_microservices.calculator_service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component


@Component
class KafkaListener {

    @Autowired
    lateinit var kafka: KafkaTemplate<String, String>

    @KafkaListener(
        topics = ["add"],
        groupId = "add"
    )
    fun listen(message: String) {
        println("Calculator Service Received: $message")
        try {
            val (id, left, right) = message.split(" ")
            val lv = left.toInt()
            val rv = right.toInt()
            Thread.sleep(10000)
            kafka.send("result", "$id ${lv + rv}")
        } catch (e: Exception) {
            println("Calculator Service Error: ${e.message}")
        }
    }

}