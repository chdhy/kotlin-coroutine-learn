package com.hunter.kotlin.coroutine.learn.threadpool

import com.hunter.kotlin.coroutine.learn.thread.printlnWithThread
import java.util.concurrent.BlockingQueue
import java.util.concurrent.LinkedBlockingQueue
import kotlin.concurrent.thread

class MyHandler {
    private val threadLocal: ThreadLocal<BlockingQueue<Runnable>> = ThreadLocal()
    private val blockingQueue: BlockingQueue<Runnable> = threadLocal.get() ?: LinkedBlockingQueue()

    fun postRunnable(runnable: Runnable) = blockingQueue.put(runnable)

    fun loop() {
        while (true) blockingQueue.take().run() // 当queue中没有Runnable时，take会阻塞，直到queue中有值可取
    }
}

val otherWork1 = Runnable { printlnWithThread("do work a") }
val otherWork2 = Runnable { printlnWithThread("do work X") }

fun main() {
    val myHandler = MyHandler()

    myHandler.postRunnable {
        printlnWithThread("do work 1")
        switchToBackThread(myHandler)
    }
    myHandler.postRunnable(otherWork1)
    myHandler.postRunnable(otherWork2)

    myHandler.loop()
    println("Main thread loop unexpectedly exited")
}

fun switchToBackThread(myHandler: MyHandler) = thread {
    Thread.sleep(100)
    printlnWithThread("do work 2")
    myHandler.postRunnable { printlnWithThread("do work 3") }
}