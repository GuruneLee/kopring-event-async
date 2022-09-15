package com.example.kopringeventasync

import org.springframework.context.ApplicationEvent
import org.springframework.context.ApplicationEventPublisher
import org.springframework.context.ApplicationListener
import org.springframework.stereotype.Component

/**
 * Created by chlee on 2022/09/15.
 *
 * @author Changha Lee
 * @version kopring-event-async
 * @since kopring-event-async
 */
class EventModel(
    val name: String,
    val age: Int,
    private val obj: Any,
): ApplicationEvent(obj) {
}

@Component
class EventPublisher(
    private val applicationEventPublisher: ApplicationEventPublisher
) {
    fun publishCustomEvent(message: String) {
        val customSpringEvent = EventModel("karol", 10, this)
        applicationEventPublisher.publishEvent(customSpringEvent)
    }
}

@Component
class StereoEventListener: ApplicationListener<EventModel> {
    override fun onApplicationEvent(event: EventModel) {
        println("stereo: name=${event.name}, age=${event.age}")
        println("stereo Thread: ${Thread.currentThread().id}")
    }
}

@Component
class AnnotatedEventListener {
    @org.springframework.context.event.EventListener
    fun handleContextStart(event: EventModel) {
        println("annotated: name=${event.name}, age=${event.age}")
        println("annotated Thread: ${Thread.currentThread().id}")
    }
}
