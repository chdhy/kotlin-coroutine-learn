package com.hunter.kotlin.coroutine.learn.threadpool

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.concurrent.ScheduledThreadPoolExecutor
import java.util.concurrent.TimeUnit
import kotlin.system.measureTimeMillis

const val times = 100_000
const val delayTime = 5000L

fun main() {
    println("coroutine time: ${measureTimeMillis { coroutine() }}")
    println("threadPool time:${measureTimeMillis { threadPool() }}")
}

fun threadPool() {
    // runBlocking 只在当前线程执行，所以这里 threadPool 也只用一个线程对比
    val scheduledThreadPoolExecutor = ScheduledThreadPoolExecutor(1)
    repeat(times) {
        scheduledThreadPoolExecutor.schedule({ print(".") }, delayTime, TimeUnit.MILLISECONDS)
    }
    scheduledThreadPoolExecutor.shutdown()
    scheduledThreadPoolExecutor.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS)
}

private fun coroutine() = runBlocking {
    repeat(times) { // 启动大量的协程
        launch {
            delay(delayTime)
            print(".")
        }
    }
}