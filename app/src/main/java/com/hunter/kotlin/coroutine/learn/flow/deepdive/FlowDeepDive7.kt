package com.hunter.kotlin.coroutine.learn.flow.deepdive

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking

fun main() {
    // 1. build flow
    val flow = flowOf(1, 2, 3)
    // 2. flow to suspend
    val suspendFunc = flow.asSuspend()

    // 3. call suspend
    val intList = runBlocking {
        suspendFunc()
    }
    println(intList)
}

fun <T> Flow<T>.asSuspend() = suspend { toList() }