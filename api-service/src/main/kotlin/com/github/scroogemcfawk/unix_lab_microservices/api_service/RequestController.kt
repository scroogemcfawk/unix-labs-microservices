package com.github.scroogemcfawk.unix_lab_microservices.api_service


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
    lateinit var addQueue: KafkaTemplate<String, String>

    var requestCounter = 0L

//    val log = LoggerFactory.getLogger("RequestController")

    @GetMapping("test")
    fun test(): String {
        return "Hello"
    }

    @GetMapping("add")
    fun add(@RequestParam left: Int, @RequestParam right: Int): String {
        addQueue.send("add", "$requestCounter $left $right")
        ++ApplicationContext.queue_size

        when (ApplicationContext.queue_size / ApplicationContext.worker_count) {
            in 5..Int.MAX_VALUE -> {
                Runtime.getRuntime().exec("docker run -itd --rm unix-lab-microservices-calculator-service")
            }

            in 2..5 -> {
                // continue
            }

            else -> {
                // stop worker
                // first worker that reads message will exit
                addQueue.send("add", "stop")
            }
        }

        return "You request number: ${requestCounter++}"
    }

    @GetMapping("result")
    fun result(@RequestParam requestNumber: String): String {

        return if (requestNumber in resultMap) {
            resultMap[requestNumber]!!
        } else {
            "Result is not ready yet."
        }
    }
}
