package com.hunter.kotlin.coroutine.learn.threadpool

import com.hunter.kotlin.coroutine.learn.thread.printlnWithThread
import java.lang.RuntimeException
import kotlin.concurrent.thread

fun main() {
    doWork1()
    thread { doWork2() }
    doWork3()
}

private fun doWork1() = printlnWithThread("do work 1")
private fun doWork2() {
    throw RuntimeException("new thread exception")
    printlnWithThread("do work 2")
}

private fun doWork3() {
    printlnWithThread("do work 3")
    throw RuntimeException("main thread exception")
}
