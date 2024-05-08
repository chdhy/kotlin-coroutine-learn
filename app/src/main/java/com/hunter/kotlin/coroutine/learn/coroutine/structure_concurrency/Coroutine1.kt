package com.hunter.kotlin.coroutine.learn.coroutine.structure_concurrency

import com.hunter.kotlin.coroutine.learn.thread.printlnWithThread
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

fun main() {
    GlobalScope.launch(Dispatchers.IO) {
        printlnWithThread("end")
    }

    GlobalScope.cancel()

    Thread.sleep(100)
}
