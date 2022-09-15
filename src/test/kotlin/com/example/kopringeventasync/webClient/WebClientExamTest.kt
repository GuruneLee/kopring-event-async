package com.example.kopringeventasync.webClient

import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest


@SpringBootTest
internal class WebClientExamTest {
    @Autowired
    lateinit var webClientExam: RunBlockingWebClient
    @Test
    fun `runBlocking_webClient_success`() {
        val getCoin = runBlocking{
                webClientExam.getCoinAPI()
        }
        print(getCoin)
    }
}