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

        println("queue size = ${ApplicationContext.queue_size}, worker count = ${ApplicationContext.worker_count}")

        if (ApplicationContext.worker_count == 0 ||
            ((ApplicationContext.queue_size / ApplicationContext.worker_count) > 3))
        {
            ++ApplicationContext.worker_count
            Runtime.getRuntime().exec("docker run -itd --net=unix-lab-microservices_internal --rm unix-lab-microservices-calculator-service")
            println("MASTER: START WORKER")
        } else {
            addQueue.send("add", "stop")
            --ApplicationContext.worker_count
            println("MASTER: STOP WORKER")
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
