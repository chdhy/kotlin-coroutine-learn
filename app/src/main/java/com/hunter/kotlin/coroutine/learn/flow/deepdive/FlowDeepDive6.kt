package com.hunter.kotlin.coroutine.learn.flow.deepdive

import com.hunter.kotlin.coroutine.learn.other.printlnWithTime
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        // 1. asFLow
        val flow = ::getAccount.asFlow()

        // 2. collect
        flow.onStart { printlnWithTime("start") }
            .collect { printlnWithTime(it) }
    }
}

suspend fun getAccount(): Int {
    delay(3000)
    return 1
}