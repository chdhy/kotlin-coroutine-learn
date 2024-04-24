package com.hunter.kotlin.coroutine.learn.thread

import kotlin.concurrent.thread

fun main() {
    printlnWithThread("do work 1")

    val newThread = switchThread2()
    newThread.join()

    printlnWithThread("do work 3")
}

fun switchThread2() = thread {
    printlnWithThread("do work 2")
}