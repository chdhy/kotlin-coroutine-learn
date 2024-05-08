package com.hunter.kotlin.coroutine.learn.coroutine.structure_concurrency

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun main() {
    // handler
    val coroutineExceptionHandler1 =
        CoroutineExceptionHandler { _, throwable -> println("record1 $throwable to log") }
    val coroutineExceptionHandler2 =
        CoroutineExceptionHandler { _, throwable -> println("record2 $throwable to log") }

    // 1
    val handlerCoroutineScope = CoroutineScope(coroutineExceptionHandler1)
    handlerCoroutineScope.launch {
        runThrow("exception 1.1")
        delay(100)
        println("will it print 1.1?")
    }
    Thread.sleep(100) // 在下面的 launch 之前抛出上面的异常
    handlerCoroutineScope.launch {
        launch(coroutineExceptionHandler2) { runThrow("exception 1.2") }
    }
    handlerCoroutineScope.launch {
        delay(100L)
        println("will it print 1.2?")
    }

    Thread.sleep(150)
    println("------------")

    // 2
    val supervisorAndHandlerCoroutineScope =
        CoroutineScope(SupervisorJob() + coroutineExceptionHandler1)
    supervisorAndHandlerCoroutineScope.launch {
        runThrow("exception 2.1")
        delay(100)
        println("will it print 2.1?")
    }
    Thread.sleep(100) // 在下面的 launch 之前抛出上面的异常
    supervisorAndHandlerCoroutineScope.launch {
        launch(coroutineExceptionHandler2) { runThrow("exception 2.2") }
    }
    supervisorAndHandlerCoroutineScope.launch {
        delay(100L)
        println("will it print 2.2?")
    }

    Thread.sleep(200)
}

fun runThrow(exception: String): Unit = throw RuntimeException(exception)