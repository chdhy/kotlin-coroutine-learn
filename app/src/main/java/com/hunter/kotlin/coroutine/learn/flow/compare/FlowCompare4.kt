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
    val list = listOf(1, 2, 3, 4, 5)
        .map {
            println("x map list $it")
            it * it
        }
        .filter {
            println("filter list $it")
            it > 20
        }
        .map {
            println("* map list $it")
            "*$it"
        }
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

    runBlocking { flow.toList() }
}
