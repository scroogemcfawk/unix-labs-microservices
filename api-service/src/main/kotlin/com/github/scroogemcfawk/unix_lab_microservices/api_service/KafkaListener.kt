package com.github.scroogemcfawk.unix_lab_microservices.api_service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.PropertySource
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component


@Component
class KafkaListener {

    @Autowired
    lateinit var resultMap: HashMap<String, String>

    @KafkaListener(
        topics = ["result"],
        groupId = "api-services",

    )
    fun listen(message: String) {
        val (id, result) = message.split(" ")
        resultMap[id] = result
    }

}
