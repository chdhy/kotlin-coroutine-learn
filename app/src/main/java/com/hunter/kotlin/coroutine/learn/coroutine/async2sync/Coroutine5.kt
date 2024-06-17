package com.hunter.kotlin.coroutine.learn.coroutine.async2sync

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun main() {
    // 1
    val coroutineScope = CoroutineScope(CoroutineName("my"))

    // 2
    coroutineScope.launch {
        println("before delay suspend")
        delay(100)
        println("after delay suspend")
    }

    // 3
    println("before sleep")
    Thread.sleep(200)
    println("after sleep")
}