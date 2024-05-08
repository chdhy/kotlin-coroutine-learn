package com.hunter.kotlin.coroutine.learn.coroutine.structure_concurrency

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

val coroutineExceptionHandler =
    CoroutineExceptionHandler { _, throwable -> println("record $throwable to log") }

fun main() {
    coroutineCancel()
    coroutineNonCancelable()

    Thread.sleep(1100)
}

private fun coroutineCancel() {
    val coroutineScope = CoroutineScope(coroutineExceptionHandler)
    coroutineScope.launch {
        try {
            println("lock resource 1")
            runThrow("exception 1")
        } finally {
            launch {
                delay(1000)
                println("release resource 1")
            }
        }
    }
}

private fun coroutineNonCancelable() {
    val coroutineScope = CoroutineScope(coroutineExceptionHandler)
    coroutineScope.launch {
        try {
            println("lock resource 2")
            runThrow("exception 2")
        } finally {
            withContext(NonCancellable) {
                delay(1000)
                println("release resource 2")
            }
        }
    }
}