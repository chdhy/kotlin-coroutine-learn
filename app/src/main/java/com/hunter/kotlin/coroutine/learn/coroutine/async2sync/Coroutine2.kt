package com.hunter.kotlin.coroutine.learn.coroutine.async2sync

import com.hunter.kotlin.coroutine.learn.thread.printlnWithThread

// threadPoolAsync
fun main() {
    handler.post {
        // 1
        printlnWithThread("set place holder")
        threadPool.execute {
            // 2
            val userProfile = getUserProfile()
            handler.post {
                // 3
                printlnWithThread("set user name: ${userProfile.first}")
                threadPool.execute {
                    // 4
                    val avatar = getImageBy(userProfile.second)
                    handler.post {
                        // 5
                        printlnWithThread("set avatar: $avatar")
                    }
                    // 6
                    val finalAvatar = renderImage(avatar)
                    // 7
                    handler.post {
                        printlnWithThread("set avatar: $finalAvatar")
                    }
                }
            }
        }
    }
    Thread.sleep(1000_000)
}