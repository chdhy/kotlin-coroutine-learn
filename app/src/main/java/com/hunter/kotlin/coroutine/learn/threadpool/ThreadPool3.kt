package com.hunter.kotlin.coroutine.learn.threadpool

import com.hunter.kotlin.coroutine.learn.thread.printlnWithThread
import java.lang.Exception
import java.util.concurrent.Executors

fun main() {
    doWork1()

    val executor = Executors.newSingleThreadExecutor()
    val future = executor.submit { doWork2() }
    try {
        future.get()
    } catch (exception: Exception) {
        exception.printStackTrace()
        println("do work 2 fail, cancel work 3")
        executor.shutdown()
        return
    }

    doWork3()
}

private fun doWork1() = printlnWithThread("do work 1")
private fun doWork3() = printlnWithThread("do work 3")

private fun doWork2() {
    throw RuntimeException("new thread exception")
    printlnWithThread("do work 2")
}
