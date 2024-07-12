package com.hunter.kotlin.coroutine.learn.flow.compare

import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking

fun main() {
    sequenceSample()
    println()
    flowSample()
}

private fun sequenceSample() {
    val sequence = sequenceOf(1, 2, 3, 4, 5)
        .map {
            println("x map sequence $it")
            it * it
        }
        .filter {
            println("filter sequence $it")
            it > 20
        }
        .map {
            println("* map sequence $it")
            "*$it"
        }

    println(sequence.toList())
}

private fun flowSample() {
    val flow = flowOf(1, 2, 3, 4, 5)
        .map {
            println("x map flow $it")
            it * it
        }
        .filter {
            println("filter flow $it")
            it > 20
        }
        .map {
            println("* map flow $it")
            "*$it"
        }

    runBlocking { println(flow.toList()) }
}
