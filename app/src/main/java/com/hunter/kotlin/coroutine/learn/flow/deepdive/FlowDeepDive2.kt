package com.hunter.kotlin.coroutine.learn.flow.deepdive

import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking

fun main() {
    // 1. builder
    val flow = flowOf(1, 2, 3)

    // 2. collect
    runBlocking {
        flow.collect { println(it) }
    }
}