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


        // todo add load balancing
        // get size of unread topic messages and start/stop extra containers
        when (ApplicationContext.queue_size / ApplicationContext.worker_count) {
            in 5..Int.MAX_VALUE -> {
                // todo start extra worker
                // ++ApplicationContext.worker_count
                Runtime.getRuntime().exec("docker run -itd --rm unix-lab-microservices-calculator-service")
            }

            in 2..5 -> {
                // continue
            }

            else -> {
                // todo stop worker
                // ++ApplicationContext.worker_count
                Runtime.getRuntime().exec("docker stop \$(docker ps -a -q --filter ancestor=unix-lab-microservices-calculator-service --format=\"{{.ID}}\")")
            }
        }

        return "You request number: ${requestCounter++}"
    }
}
