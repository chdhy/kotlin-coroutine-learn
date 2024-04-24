package com.hunter.kotlin.coroutine.learn.thread

import android.os.Handler
import android.os.Looper
import kotlin.concurrent.thread

fun main() {
    printlnWithThread("do work 1")
    switchThread4()
}

fun switchThread4() = thread {
    printlnWithThread("do work 2")
    Handler(Looper.getMainLooper()).post {
        printlnWithThread("do work 3")
    }
}