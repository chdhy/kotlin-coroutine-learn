package com.hunter.kotlin.coroutine.learn.coroutine.structure_concurrency

import com.hunter.kotlin.coroutine.learn.thread.printlnWithThread
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

fun main() {
    val coroutineScope = CoroutineScope(Dispatchers.Default)

    coroutineScope.launch {
        // 1. context[ContinuationInterceptor] = Dispatchers.Default
        printlnWithThread("work1")
        val work2 = withContext(Dispatchers.IO) {
            // 2. context[ContinuationInterceptor] = Dispatchers.IO
            printlnWithThread("do work2 ...")
            "work2"
        }
        // 3. // context[ContinuationInterceptor] = Dispatchers.Default
        printlnWithThread(work2)
    }

    Thread.sleep(100)
}
