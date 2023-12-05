package com.github.scroogemcfawk.unix_lab_microservices.api_service

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class ResultMap {

    @Bean
    open fun getMap(): HashMap<String, String> {
        return HashMap()
    }
}
