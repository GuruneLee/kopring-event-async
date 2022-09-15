package com.example.kopringeventasync

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KopringEventAsyncApplication

fun main(args: Array<String>) {
    runApplication<KopringEventAsyncApplication>(*args)
}


fun log(message: String) {
    println("${message}: ${Thread.currentThread().id}")
}
