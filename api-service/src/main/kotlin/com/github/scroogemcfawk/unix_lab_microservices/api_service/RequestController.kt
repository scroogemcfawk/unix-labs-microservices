package com.github.scroogemcfawk.unix_lab_microservices.api_service


import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/")
class RequestController{

    @Autowired
    lateinit var resultMap: HashMap<String, String>

    @Autowired
    lateinit var kafkaAdd: KafkaTemplate<String, String>

    private var requestCounter = 0L

    private val log: Logger = LoggerFactory.getLogger("RequestController")

    @GetMapping("test")
    fun test(): String {
        return "Hello"
    }

    @GetMapping("add")
    fun add(@RequestParam left: Int, @RequestParam right: Int): String {
        log.info("Request: add($left, $right) -> [$requestCounter]")

        kafkaAdd.send("add", "$requestCounter $left $right")

        return "You request number: ${requestCounter++}"
    }

    @GetMapping("result")
    fun result(@RequestParam requestNumber: String): String {
        log.info("Request: result($requestNumber)")

        return if (requestNumber in resultMap) {
            resultMap[requestNumber]!!
        } else {
            "Result is not ready yet."
        }
    }
}
