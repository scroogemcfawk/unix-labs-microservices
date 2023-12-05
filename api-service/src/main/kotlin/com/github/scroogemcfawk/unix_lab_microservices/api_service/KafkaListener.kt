package com.github.scroogemcfawk.unix_lab_microservices.api_service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component


@Component
class KafkaListener {

//    @Autowired
//    lateinit var kafka: KafkaTemplate<String, String>

    @Autowired
    lateinit var resultMap: HashMap<String, String>

    @KafkaListener(
        topics = ["result"],
        groupId = "add"
    )
    fun listen(message: String) {
        println("Client Service Received: $message")
        try {
            val (id, result) = message.split(" ")
            resultMap[id] = result
        } catch (e: Exception) {
            println("Client Service Error: ${e.message}")
        }
    }

}
