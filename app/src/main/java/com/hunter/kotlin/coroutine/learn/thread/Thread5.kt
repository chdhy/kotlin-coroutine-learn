package com.hunter.kotlin.coroutine.learn.thread

fun main() {
    printlnWithThread("do work 1")

    val work2 = Runnable {
        printlnWithThread("do work 2")
    }
    val newThread = Thread(work2)
    newThread.run() // 编译器警告：Calls to 'run()' should probably be replaced with 'start()'

    printlnWithThread("do work 3")
}