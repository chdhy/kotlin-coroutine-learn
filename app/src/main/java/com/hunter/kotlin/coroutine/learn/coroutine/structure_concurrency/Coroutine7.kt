package com.hunter.kotlin.coroutine.learn.coroutine.structure_concurrency

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun main() {
    val supervisorJobCoroutineScope = CoroutineScope(SupervisorJob())

    supervisorJobCoroutineScope.launch {
        launch { throw RuntimeException() }
        launch {
            delay(100L)
            println("will print 1?")
        }
    }

    Thread.sleep(200)

    supervisorJobCoroutineScope.launch {
        println("will print 2?")
    }

    Thread.sleep(100)
}