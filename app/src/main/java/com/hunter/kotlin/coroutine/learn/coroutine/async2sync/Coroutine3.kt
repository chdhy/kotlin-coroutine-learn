package com.hunter.kotlin.coroutine.learn.coroutine.async2sync

import com.hunter.kotlin.coroutine.learn.thread.printlnWithThread
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.coroutines.yield

// CoroutineAsync
fun main() {
    coroutineScope.launch {
        // 1
        printlnWithThread("set place holder")
        // 2
        val userProfile = withContext(Dispatchers.IO) { getUserProfile() }
        // 3
        printlnWithThread("set user name: ${userProfile.first}")
        // 4
        val avatar = withContext(Dispatchers.IO) { getImageBy(userProfile.second) }
        // 5
        printlnWithThread("set avatar: $avatar")
        // 6
        val finalAvatar = withContext(Dispatchers.Default) { renderImage(avatar) }
        // 7
        printlnWithThread("set avatar: $finalAvatar")
    }
}