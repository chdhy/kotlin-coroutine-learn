package com.hunter.kotlin.coroutine.learn.coroutine.structure_concurrency

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun main() {
    val commonCoroutineScope = CoroutineScope(CoroutineName("non-SupervisorJob"))
    val supervisorJobCoroutineScope = CoroutineScope(SupervisorJob())

    commonCoroutineScope.launch { throw RuntimeException() }
    commonCoroutineScope.launch {
        delay(100L)
        println("will not print")
    }

    supervisorJobCoroutineScope.launch { throw RuntimeException() }
    supervisorJobCoroutineScope.launch {
        delay(100L)
        println("will print")
    }

    Thread.sleep(200)
}