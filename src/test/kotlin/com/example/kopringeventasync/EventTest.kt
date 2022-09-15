package com.example.kopringeventasync

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

/**
 * Created by chlee on 2022/09/15.
 *
 * @author Changha Lee
 * @version kopring-event-async
 * @since kopring-event-async
 */
@SpringBootTest
class EventTest {
    @Autowired
    private lateinit var eventPublisher: EventPublisher

    @Test
    fun `event_전달_성공`() {
        eventPublisher.publishCustomEvent("message")
        println("publisher Thread: ${Thread.currentThread().id}")
        // stereo: name=karol, age=10
        // stereo Thread: 1
        // annotated: name=karol, age=10
        // annotated Thread: 1
        // publisher Thread: 1
        // publisher, listener 모두 같은 쓰레드를 사용하고 있다. (비동기 안했거덩)
    }
}