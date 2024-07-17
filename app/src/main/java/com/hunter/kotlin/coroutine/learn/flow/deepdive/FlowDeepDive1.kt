package com.hunter.kotlin.coroutine.learn.flow.deepdive

import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking

fun main () {
    // 1. builder
    val flow = flow {
        emit(1)
        emit(2)
        emit(3)
    }

    // 2. collect
    runBlocking {
        flow.collect { println(it) }
    }

    listOf(1)
    mapOf(1 to 1, 2 to 2).values
}