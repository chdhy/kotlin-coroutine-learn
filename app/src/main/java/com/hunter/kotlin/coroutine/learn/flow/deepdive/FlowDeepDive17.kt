@file:OptIn(ExperimentalCoroutinesApi::class)

package com.hunter.kotlin.coroutine.learn.flow.deepdive

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.fold
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.reduce
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    flowOf(1f, 2f, 3f)
        .average()
        .println()
}

suspend fun Flow<Float>.average(): Float {
    var count  = 0
    var accumultor = 0f

    collect {
        count++
        accumultor += it
    }

    return accumultor / count
}