package com.hunter.kotlin.coroutine.learn.flow.compare

import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking

fun main() {
    listSample()
    println()
    flowSample()
}

private fun listSample() {
    val multiplyMap = listOf(1, 2, 3, 4, 5)
        .map { it * it }
    val filter20 = multiplyMap
        .filter { it > 20 }
    val starMap = filter20
        .map {
            println("map list $it")
            "*$it"
        }

    println("before print list")
    println(starMap)
}

private fun flowSample() {
    val flow = flowOf(1, 2, 3, 4, 5)
        .map { it * it }
        .filter { it > 20 }
        .map {
            println("map flow $it")
            "*$it"
        }

    println("before print flow.toList")
    runBlocking { println(flow.toList()) }
}
