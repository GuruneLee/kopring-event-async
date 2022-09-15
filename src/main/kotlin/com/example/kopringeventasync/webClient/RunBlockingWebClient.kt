package com.example.kopringeventasync.webClient

import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient

@Component
class RunBlockingWebClient(
    private val webClient: WebClient
) {
    suspend fun getCoinAPI(): String? {
        return webClient.get()
            .uri("/v1/bpi/currentprice.json")
            .accept(MediaType.APPLICATION_JSON)
            .exchangeToMono {
                it.bodyToMono(String::class.java)
            }
            .awaitSingle()
        // awaitSingle 은 비동기 작업을 기다렸다가 응답을 반환한다
        // spring-reactor 스펙
    }
}