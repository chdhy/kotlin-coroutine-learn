package com.hunter.kotlin.coroutine.learn.coroutine.dispatcher

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.asExecutor
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.runBlocking
import java.util.Collections
import kotlin.time.measureTime

fun main() = runBlocking {
    val threads = Collections.synchronizedSet<String>(mutableSetOf())

    val time = measureTime {
        List(100) {
            async(Dispatchers.Default) {
                threads.add(Thread.currentThread().name)
                // Possibly blocking call in non-blocking context could lead to thread starvation
                Thread.sleep(1000)
            }
        }.awaitAll()
    }
    println("time: $time threads.size: ${threads.size}")
}