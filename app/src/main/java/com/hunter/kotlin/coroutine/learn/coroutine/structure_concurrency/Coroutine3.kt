package com.hunter.kotlin.coroutine.learn.coroutine.structure_concurrency

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

fun main() {
    val coroutineScope = CoroutineScope(Dispatchers.Default)
    println(coroutineScope.coroutineContext)
}
