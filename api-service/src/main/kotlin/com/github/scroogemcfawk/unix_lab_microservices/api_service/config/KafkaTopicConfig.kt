package com.github.scroogemcfawk.unix_lab_microservices.api_service.config

import org.apache.kafka.clients.admin.NewTopic
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.TopicBuilder

@Configuration
open class KafkaTopicConfig {

    @Bean
    open fun addTopic(): NewTopic {
        return TopicBuilder
            .name("add")
            .partitions(32) // so up to 32 workers can listen to the topic
            .build()
    }

    @Bean
    open fun resultTopic(): NewTopic {
        return TopicBuilder.name("result")
            .build()
    }

}
