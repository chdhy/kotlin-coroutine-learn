package com.hunter.kotlin.coroutine.learn.coroutine.structure_concurrency

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

fun main() {
    val coroutineScope = CoroutineScope(CoroutineName("outer coroutine"))

    coroutineScope.launch {
        // // coroutineContext 来自于 lambda block 的 receiver - CoroutineScope
        println(coroutineContext[CoroutineName].toString()) // 1
        withContext(Dispatchers.IO) {
            println(coroutineContext[CoroutineName].toString()) // 2
        }
        withContext(CoroutineName("inner coroutine")) {
            println(coroutineContext[CoroutineName].toString()) // 3
        }
        println(coroutineContext[CoroutineName].toString()) // 4
    }

    Thread.sleep(100)
}
