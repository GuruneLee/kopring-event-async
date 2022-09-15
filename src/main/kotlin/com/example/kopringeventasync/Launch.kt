package com.example.kopringeventasync

import kotlinx.coroutines.*
import org.springframework.stereotype.Component

/**
 * Created by chlee on 2022/09/15.
 *
 * @author Changha Lee
 * @version kopring-event-async
 * @since kopring-event-async
 */
@Component
class Launch {
    // launch 는 Job을 반환한다.
    // Job 은 반환되는 순간 실행된다. (.cancel() 로 멈출 수 있다)
    fun launchInGlobalScope() {
        // GlobalScope 코루틴은 메인 스레드가 실행중인 동안만 코루틴의 동작을 보장받는다.
        // 비동기적으로 launch 를 실행하거나, 코루틴의 실행이 끝날 때까지 현재 스레드를 블록시키는 방법(runBlocking)을 사용해야한다.
        GlobalScope.launch {
            println("global coroutine started: ${Thread.currentThread().id}")
        }
    }

    fun launchRunBlocking() {
        runBlocking {
            launch {
                println("global runBlocking coroutine started: ${Thread.currentThread().id}")
            }
        }
    }

    fun yieldExam() {
        runBlocking {
            launch {
                log("1")
                yield()
                log("3")
                yield()
                log("5")
            }

            log("after first launch")

            launch {
                log("2")
                delay(1000L)
                log("4")
                delay(1000L)
                log("6")
            }
            log("after second launch")
        }
    }
}