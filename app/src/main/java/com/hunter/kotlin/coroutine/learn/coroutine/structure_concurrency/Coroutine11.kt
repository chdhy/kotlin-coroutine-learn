package com.hunter.kotlin.coroutine.learn.coroutine.structure_concurrency

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

fun main() {
    val coroutineScope = CoroutineScope(Dispatchers.Default)

    val job = coroutineScope.launch(start = CoroutineStart.LAZY) {
        println("lazy")
    }
    println(job)

    Thread.sleep(100)
    println("will not start automatically")

    job.start()
    Thread.sleep(100)
    println(job)
}