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
internal class AsyncTest {
    @Autowired
    lateinit var async: Async

    @Test
    fun `async test`() {
        async.sumAll()

        // 모두 같은 쓰레드에서 실행되었고,
        // 각자 1,2,3 초가 걸리는 연산이었지만
        // 총 3초만 걸려서 연산을 했다

        // after async d1: 1
        // after async d2: 1
        // after async d3: 1
        // 1+2+3 = 6: 1
        // after await all & add: 1
    }
}