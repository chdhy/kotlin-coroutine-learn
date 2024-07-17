package com.hunter.kotlin.coroutine.learn.flow.deepdive

import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val flow1 = flowOf(1, 2, 3, 4)
    val flow2 = flowOf('A', 'B', 'C')

    flow1
        .zip(flow2) { v1, v2 ->
            "$v1:$v2"
        }
        .collect { println(it) }
}

