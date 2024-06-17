package com.hunter.kotlin.coroutine.learn.coroutine.dispatcher

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.runBlocking
import java.util.Collections
import kotlin.time.measureTime

fun main() = runBlocking {
    val threads = Collections.synchronizedSet<String>(mutableSetOf())

    val time = measureTime {
        val deferred1 = async {
            List(100) {
                async(Dispatchers.Default) {
                    threads.add(Thread.currentThread().name)
                    Thread.sleep(1000)
                }
            }.awaitAll()
        }

        val deferred2 = async {
            List(1000) {
                async(Dispatchers.IO) {
                    threads.add(Thread.currentThread().name)
                    Thread.sleep(1000)
                }
            }.awaitAll()
        }

        deferred1.await()
        deferred2.await()
    }
    println("time: $time threads.size: ${threads.size}")
}