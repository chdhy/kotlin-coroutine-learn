package com.hunter.kotlin.coroutine.learn.coroutine.dispatcher

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.runBlocking
import kotlin.time.measureTime

fun main() = runBlocking {
    val threads = mutableSetOf<String>()

    val time = measureTime {
        List(1000) {
            async(Dispatchers.IO) {
                threads.add(Thread.currentThread().name)
                Thread.sleep(1000)
            }
        }.awaitAll()
    }

    println("time: $time threads.size: ${threads.size}")
}
