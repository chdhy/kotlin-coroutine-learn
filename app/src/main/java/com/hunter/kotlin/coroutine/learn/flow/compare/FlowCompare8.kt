@file:SuppressLint("CheckResult")

package com.hunter.kotlin.coroutine.learn.flow.compare

import android.annotation.SuppressLint
import com.hunter.kotlin.coroutine.learn.other.printlnWithTime
import com.hunter.kotlin.coroutine.learn.thread.printlnWithThread
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.concurrent.Executors

fun main() {
    flowSample()
    rxjavaSample()
    Thread.sleep(10000)
}

private fun rxjavaSample() {
    val observable = Observable.fromArray(1, 2, 3, 4, 5)
        .doOnSubscribe { printlnWithTime("rxjava start") }
        .doOnComplete { printlnWithTime("rxjava complete") }
        .map {
            // mock 耗时IO
            Thread.sleep(1000)
            it * it
        }
        .filter { it > 20 }
        .map { "*$it" }

    // 模拟 Scheduler.main()
    val mainScheduler = Schedulers.from(Executors.newSingleThreadExecutor { Thread(it, "main") })

    repeat(10000) {
        observable
            .subscribeOn(Schedulers.io())
            .observeOn(mainScheduler)
            .subscribe {}
    }
}

private fun flowSample() {
    val flow = flowOf(1, 2, 3, 4, 5)
        .onStart { printlnWithTime("flow start") }
        .onCompletion { printlnWithTime("flow complete") }
        .map {
            // mock 耗时IO
            delay(1000)
            it * it
        }
        .filter { it > 20 }
        .map { "*$it" }

    runBlocking {
        repeat(10000) {
            launch {
                flow.flowOn(Dispatchers.IO)
                    .collect { }
            }
        }
    }
}
