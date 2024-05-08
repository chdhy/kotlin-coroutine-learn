package com.hunter.kotlin.coroutine.learn.coroutine.structure_concurrency

import com.hunter.kotlin.coroutine.learn.thread.printlnWithThread
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

fun main() {
    val job = GlobalScope.launch {
        printlnWithThread("end")
    }
    println(job)
}
