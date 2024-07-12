@file:SuppressLint("CheckResult")

package com.hunter.kotlin.coroutine.learn.flow.compare

import android.annotation.SuppressLint
import com.hunter.kotlin.coroutine.learn.thread.printlnWithThread
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import java.util.concurrent.Executors

fun main() {
    rxjavaSample()
    Thread.sleep(100)
    println()
    flowSample()
}

private fun rxjavaSample() {
    val observable = Observable.fromArray(1, 2, 3, 4, 5)
        .map {
            printlnWithThread("x map sequence $it")
            it * it
        }
        .filter {
            printlnWithThread("filter sequence $it")
            it > 20
        }
        .map {
            printlnWithThread("* map sequence $it")
            "*$it"
        }

    // 模拟 Scheduler.main()
    val mainScheduler = Schedulers.from(Executors.newSingleThreadExecutor { Thread(it, "main") })

    observable
        .subscribeOn(Schedulers.io())
        .observeOn(mainScheduler)
        .subscribe { printlnWithThread("consume $it") }
}

private fun flowSample() {
    val flow = flowOf(1, 2, 3, 4, 5)
        .map {
            printlnWithThread("x map flow $it")
            it * it
        }
        .filter {
            printlnWithThread("filter flow $it")
            it > 20
        }
        .map {
            printlnWithThread("* map flow $it")
            "*$it"
        }

    runBlocking {
        flow.flowOn(Dispatchers.IO)
            .collect { printlnWithThread("collect $it") }
    }
}
