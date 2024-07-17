package com.hunter.kotlin.coroutine.learn.flow.deepdive

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking

fun main() {
    // 1. builder
    val flow = sequenceOf(1, 2, 3).asFlow()

    // 2. collect
    runBlocking {
        flow.collect { println(it) }
    }
}