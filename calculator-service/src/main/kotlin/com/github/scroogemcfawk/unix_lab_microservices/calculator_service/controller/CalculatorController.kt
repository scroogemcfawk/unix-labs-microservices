package com.github.scroogemcfawk.unix_lab_microservices.calculator_service.controller

import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


//@RestController
//@RequestMapping("/api/v1/")
//class CalculatorController {
//    val log = LoggerFactory.getLogger(CalculatorController::class.java)
//
//    @GetMapping("add")
//    fun add(
//        @RequestParam left: Int,
//        @RequestParam right: Int
//    ): Int {
//        log.info("Performing heavy calculations...")
//        Thread.sleep(10000)
//        log.info("Done")
//        return left + right
//    }
//
//}
