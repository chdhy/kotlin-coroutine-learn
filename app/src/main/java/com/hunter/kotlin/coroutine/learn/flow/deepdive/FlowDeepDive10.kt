package com.hunter.kotlin.coroutine.learn.flow.deepdive

import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.filterNot
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.takeWhile
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    flowOf(1, 2, 3, 4, null, 5, 6, 7)
        .filterNotNull() // 1,2,3,4,5,6,7
        .filter { it % 2 != 0 } // 1,3,5,7
        .filterNot { it % 3 == 0 } // 1,5,7
        .drop(1) // 5,7
        .take(1) // 5
        .collect { println(it) } // 5
}

