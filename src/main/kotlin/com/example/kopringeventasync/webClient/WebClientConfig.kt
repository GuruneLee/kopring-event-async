package com.example.kopringeventasync.webClient

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class WebClientConfig {
    @Bean
    // https://api.coindesk.com/v1/bpi/currentprice.json
    fun exampleWebClient(): WebClient {
        return WebClient.builder()
            .baseUrl("https://api.coindesk.com")
            .build()
    }
}