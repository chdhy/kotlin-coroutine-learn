package com.hunter.kotlin.coroutine.learn.coroutine.async2sync

import com.hunter.kotlin.coroutine.learn.thread.printlnWithThread
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.asCoroutineDispatcher
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

// threadPool related
val handler: ExecutorService = Executors.newSingleThreadExecutor { Thread(it, "main") }
val threadPool: ExecutorService = Executors.newCachedThreadPool()
// coroutine related
val mainDispatcher: CoroutineDispatcher = handler.asCoroutineDispatcher()
val coroutineScope = CoroutineScope(mainDispatcher)

fun ExecutorService.post(runnable: Runnable) = handler.execute(runnable) // 模拟 Handler.post

fun getUserProfile(): Pair<String, String> {
    printlnWithThread("get User Profile")
    return "userName" to "avatarUrl"
}

fun getImageBy(url: String): String {
    printlnWithThread("get Image")
    return "avatar"
}

fun renderImage(image: String): String {
    printlnWithThread("render Image")
    return "$image with border and shadow"
}