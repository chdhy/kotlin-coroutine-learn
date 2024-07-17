package com.hunter.kotlin.coroutine.learn.flow.deepdive

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking

fun main() {
    val map = mapOf("1" to 1, "2" to 2, "3" to 3)

    // 1. builder
    val valuesFlow = map.values.asFlow()
    val keysFlow = map.keys.asFlow()
    val entriesFlow = map.entries.asFlow()

    // 2. collect
    runBlocking {
        valuesFlow.collect { println(it) }
        keysFlow.collect { println(it) }
        entriesFlow.collect { println(it) }
    }
}