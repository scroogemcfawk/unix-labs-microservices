package com.github.scroogemcfawk.unix_lab_microservices.calculator_service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.ComponentScan
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component


@ComponentScan("com.github.scroogemcfawk.unix_lab_microservices.calculator_service")
@Component
class KafkaListener {

    @Autowired
    lateinit var kafkaResult: KafkaTemplate<String, String>

    @KafkaListener(
        topics = ["add"],
        groupId = "calculator-services"
    )
    fun listen(message: String) {
        println("Calculator Service Received: $message")

        val (id, left, right) = message.split(" ")
        val lv = left.toInt()
        val rv = right.toInt()

        // heavy calculations
        Thread.sleep(10000)
        kafkaResult.send("result", "$id ${lv + rv}")
    }

}
