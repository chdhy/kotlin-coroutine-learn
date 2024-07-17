package com.hunter.kotlin.coroutine.learn.flow.deepdive

import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    flowOf(1, 2, 3, 4)
        .onEach { println("onEach1 $it") }
        .onStart { println("onStart") }
        .onCompletion { println("onCompletion") }
        .map { if (it % 3 == 0) throw IllegalArgumentException() else it }
        .catch {
            println("catch $it")
            emit(-1)
        }
        .onEach {
            println("onEach2 $it")
            if (it == -1) throw RuntimeException()
        }
        .collect { println(it) }
}
