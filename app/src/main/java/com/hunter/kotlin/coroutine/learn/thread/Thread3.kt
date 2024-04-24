package com.hunter.kotlin.coroutine.learn.thread

import java.util.concurrent.ConcurrentLinkedQueue
import kotlin.concurrent.thread

val work = Runnable {
    printlnWithThread("do work 1")
    switchThread3()
}

val otherWork1 = Runnable {
    Thread.sleep(100) // 避免work结束太早，newThread添加work失败
    printlnWithThread("do work a")
}

val otherWork2 = Runnable {
    printlnWithThread("do work X")
}

// prevent ConcurrentModificationException
val works = ConcurrentLinkedQueue<Runnable>()
fun main() {
    works.addAll(listOf(work, otherWork1, otherWork2))
    works.forEach { it.run() }
}

fun switchThread3() = thread {
    printlnWithThread("do work 2")
    works.add(Runnable { printlnWithThread("do work 3") })
}