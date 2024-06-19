package com.hunter.kotlin.coroutine.learn.coroutine.custom

import com.hunter.kotlin.coroutine.learn.coroutine.async2sync.getImageBy
import com.hunter.kotlin.coroutine.learn.coroutine.async2sync.getUserProfile
import com.hunter.kotlin.coroutine.learn.coroutine.async2sync.mainDispatcher
import com.hunter.kotlin.coroutine.learn.coroutine.async2sync.renderImage
import com.hunter.kotlin.coroutine.learn.thread.printlnWithThread
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

fun main() {
    val coroutineScope = CoroutineScope(mainDispatcher)

    coroutineScope.launch {
        // 1
        printlnWithThread("set place holder: ${System.currentTimeMillis()}")
        // 2 为了丰富演示内容，加入delay
        delay(1000L)
        // 3
        val userProfile = withContext(Dispatchers.IO) { getUserProfile() }
        // 4
        printlnWithThread("set user name: ${userProfile.first} ${System.currentTimeMillis()}")
        // 5
        val avatar = withContext(Dispatchers.IO) { getImageBy(userProfile.second) }
        // 6
        printlnWithThread("set avatar: $avatar")
        // 7 为了丰富演示内容，加入async
        val finalAvatar = async(Dispatchers.Default) { renderImage(avatar) }
        // 8 加入 log 演示挂起点的作用
        printlnWithThread("log record")
        // 9
        printlnWithThread("set avatar: ${finalAvatar.await()} ${System.currentTimeMillis()}")
    }
}
