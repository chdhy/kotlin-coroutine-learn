package com.hunter.kotlin.coroutine.learn.flow.compare

import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking

fun main() {
    listSample()
    flowSample()
}

private fun listSample() {
    val list = listOf(1, 2, 3, 4, 5)
        .map { it * it }
        .filter { it > 20 }
        .map { "*$it" }

    println(list)
}

private fun flowSample() {
    val flow = flowOf(1, 2, 3, 4, 5)
        .map { it * it }
        .filter { it > 20 }
        .map { "*$it" }

    runBlocking { println(flow.toList()) }
}
