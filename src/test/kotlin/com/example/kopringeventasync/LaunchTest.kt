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
internal class LaunchTest {
    @Autowired
    private lateinit var coroutine: Launch

    @Test
    fun `Launch Test`() {
        println("test started: ${Thread.currentThread().id}")

        coroutine.launchInGlobalScope()

        println("launchInGlobalScope() excuted: ${Thread.currentThread().id}")
        Thread.sleep(5000L)
        print("test terminated: ${Thread.currentThread().id}")

        // coroutine 에서 시작한 작업은 다른 스레드에서 실행되을 확인할 수 있다.
        // test started: 1
        // launchInGlobalScope() excuted: 1
        // global coroutine started: 16
        // test terminated: 1
    }

    @Test
    fun `runBlockingExam Test`() {
        println("test started: ${Thread.currentThread().id}")

        coroutine.launchRunBlocking()

        println("launchRunBlocking() executed: ${Thread.currentThread().id}")
        // 이걸 없애보자 - Thread.sleep(5000L)
        print("test terminated: ${Thread.currentThread().id}")

        // runBlocking 작업이 끝난 후에야 그 다음줄이 실행되어졌다.
        // 그런데, 모든 작업이 한 쓰레드에서 이루어졌다. 이러면 코루틴을 쓰나 마나 아닌가?
        // 그렇지 않다, 코루틴들은 서로 yeild() 해주며 협력할 수 있기 때문이다. - 멀티 태스킹 가능!

        // test started: 1
        // global runBlocking coroutine started: 1
        // launchInGlobalScope() excuted: 1
        // test terminated: 1
    }

    @Test
    fun `yieldExam Test`() {
        log("test started")
        coroutine.yieldExam()
        log("launch with yield executed")
        print("test terminated: ${Thread.currentThread().id}")

        // launch는 즉시 반환된다
        // runBlocking은 내부 코루틴이 모두 끝난 다음 반환된다
        // delay() 를 사용한 코루틴은 그 시간이 지날 떄까지 다른 코루틴에게 실행을 양보한다
        // 첫번째 루틴이 1 찍고 둘 째 루틴에 yield() -> 둘 째 루틴이 2 찍고 yield하고 1초 기다림 -> 그 사이에 첫 째 루틴이 3,5 찍음 (delay중엔 yield 튕김)
        // ( delay() 대신 yield() 를 썼으면 1,2,3,4,5,6 순차 로그 찍혔을 것 )

        // test started: 1
        // after first launch: 1
        // after second launch: 1
        // 1: 1
        // 2: 1
        // 3: 1
        // 5: 1
        // 4: 1
        // 6: 1
        // launch with yield executed: 1
        // test terminated: 1
    }

}