package com.example.kopringeventasync

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.springframework.stereotype.Component

/**
 * Created by chlee on 2022/09/15.
 *
 * @author Changha Lee
 * @version kopring-event-async
 * @since kopring-event-async
 */
@Component
class Async {
    // async 는 Deffered 를 반환한다.
    // 반환된 Deffered는 .await() 을 가지고 있어서, 작업을 기다릴 수 있다.
    fun sumAll() {
        runBlocking {
            val d1 = async { delay(1000L); 1 } //1초
            log("after async d1")
            val d2 = async { delay(2000L); 2 } //2초
            log("after async d2")
            val d3 = async { delay(3000L); 3 } //3초
            log("after async d3")

            log("1+2+3 = ${d1.await() + d2.await() + d3.await()}")
            log("after await all & add")
        }
    }
}
