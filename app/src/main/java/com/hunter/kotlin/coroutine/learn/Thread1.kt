package com.hunter.kotlin.coroutine.learn

import kotlin.concurrent.thread

fun main() {
    printlnWithThread("do work 1")
    switchThread()
    printlnWithThread("do work 3")
}

fun switchThread() = thread {
    printlnWithThread("do work 2")
}

fun printlnWithThread(message: String) {
    println("${Thread.currentThread().name}: $message")
}