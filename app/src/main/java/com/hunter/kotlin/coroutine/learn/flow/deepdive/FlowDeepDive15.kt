@file:OptIn(ExperimentalCoroutinesApi::class)

package com.hunter.kotlin.coroutine.learn.flow.deepdive

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    flowOf(::getFlow1, ::getFlow2, ::getFlow3)
        .flatMapConcat { it() }
        .collect { print(it) }
}

private fun getFlow1(): Flow<Int> = flowOf(1, 2, 3)

private fun getFlow2() = flowOf('A', 'B', 'C')
    .map {
        delay(100)
        it
    }

private fun getFlow3() = flowOf("一", "二", "三")
