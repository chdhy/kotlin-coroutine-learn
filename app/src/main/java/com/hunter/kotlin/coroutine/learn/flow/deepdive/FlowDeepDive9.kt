package com.hunter.kotlin.coroutine.learn.flow.deepdive

import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    flowOf(1, 2, 3)
        .map { it * it }
        .collect { println(it) }
}

