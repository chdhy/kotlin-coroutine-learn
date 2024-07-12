package com.hunter.kotlin.coroutine.learn.flow.compare

import com.hunter.kotlin.coroutine.learn.thread.printlnWithThread
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
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
            printlnWithThread("x map sequence $it")
            it * it
        }
        .filter {
            printlnWithThread("filter sequence $it")
            it > 20
        }
        .map {
            printlnWithThread("* map sequence $it")
            "*$it"
        }

    println(sequence.toList())
}


private fun flowSample() {
    val flow = flowOf(1, 2, 3, 4, 5)
        .map {
            printlnWithThread("x map flow $it")
            it * it
        }
        .filter {
            printlnWithThread("filter flow $it")
            it > 20
        }
        .map {
            printlnWithThread("* map flow $it")
            "*$it"
        }

    runBlocking {
        flow.flowOn(Dispatchers.IO)
            .collect { printlnWithThread("collect $it") }
    }
}
