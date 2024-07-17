package com.hunter.kotlin.coroutine.learn.flow.deepdive

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val flow1 = flowOf(1, 2, 3)
    val flow2 = flowOf('A', 'B', 'C')
        .map {
            delay(100)
            it
        }
    val flow3 = flowOf("一", "二", "三")

    merge(flow1, flow2, flow3)
        .collect { print(it) }
}
