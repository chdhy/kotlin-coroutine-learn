@file:OptIn(ExperimentalCoroutinesApi::class)

package com.hunter.kotlin.coroutine.learn.flow.deepdive

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.fold
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.reduce
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val flow = flowOf(1, 2, 3)

    // only collect first
    flow.first().println()
    // only collect last
    flow.last().println()
    // collect the count
    flow.count().println()
    // collect the reduced value
    flow.reduce { accumulator, value -> accumulator * value }.println()
    // collect the reduced value with a initial value
    flow.fold(1) { accumulator, value -> accumulator + value }.println()
}

fun <T> T.println() = println(this)
