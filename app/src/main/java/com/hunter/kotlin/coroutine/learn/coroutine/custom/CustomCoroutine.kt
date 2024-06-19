@file:Suppress("UNCHECKED_CAST")

package com.hunter.kotlin.coroutine.learn.coroutine.custom

import com.hunter.kotlin.coroutine.learn.coroutine.async2sync.getImageBy
import com.hunter.kotlin.coroutine.learn.coroutine.async2sync.getUserProfile
import com.hunter.kotlin.coroutine.learn.coroutine.async2sync.renderImage
import com.hunter.kotlin.coroutine.learn.thread.printlnWithThread
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.ScheduledThreadPoolExecutor
import java.util.concurrent.ThreadFactory
import java.util.concurrent.TimeUnit

fun main() {
    val coroutineScope = MyCoroutineScope(MyDispatchers.mainDispatcher)

    coroutineScope.launch {
        when (state) {
            1 -> {
                printlnWithThread("set place holder: ${System.currentTimeMillis()}")
                delay(myCoroutineContext, 1000L, this)
            }

            2 -> withContext(MyDispatchers.ioDispatcher, this) { getUserProfile() }
            3 -> {
                val userProfile = it as Pair<String, String>
                printlnWithThread("set user name: ${userProfile.first} ${System.currentTimeMillis()}")
                withContext(MyDispatchers.ioDispatcher, this) { getImageBy(userProfile.second) }
            }

            4 -> {
                val avatar = it as String
                printlnWithThread("set avatar: $avatar")
                val deferredCoroutine = async(MyDispatchers.defaultDispatcher, this) { renderImage(avatar) }
                printlnWithThread("log record")
                deferredCoroutine.await()
            }

            5 -> {
                val finalAvatar = it as String
                printlnWithThread("set avatar: $finalAvatar ${System.currentTimeMillis()}")
            }
        }
    }
}

interface MyCoroutineScope {
    val myCoroutineContext: ScheduledExecutorService
}

object MyDispatchers {
    val mainDispatcher = ScheduledThreadPoolExecutor(1, ThreadFactory { Thread(it, "main") })
    val defaultDispatcher = ScheduledThreadPoolExecutor(4, ThreadFactory { Thread(it, "default") })
    val ioDispatcher = ScheduledThreadPoolExecutor(8, ThreadFactory { Thread(it, "io") })
}

interface Continuation {
    fun resume(any: Any)
}

abstract class MyCoroutine : Continuation, Executable {
    var state = 1
    var myCoroutineContext: ScheduledExecutorService = MyDispatchers.defaultDispatcher
    override fun resume(any: Any) {
        state++
        myCoroutineContext.submit { this(any) }
    }
}

class MyDeferred {
    lateinit var delegate: MyCoroutine
    lateinit var myCoroutineContext: ScheduledExecutorService
    var isCompleted = false
    var result: Any? = null

    fun await() {
        if (!isCompleted) {
            myCoroutineContext.submit { await() }
        } else {
            return delegate.resume(result!!)
        }
    }
}

fun MyCoroutineScope(myCoroutineContext: ScheduledExecutorService = MyDispatchers.defaultDispatcher): MyCoroutineScope {
    return object : MyCoroutineScope {
        override val myCoroutineContext: ScheduledExecutorService = myCoroutineContext
    }
}

typealias Executable = (Any) -> Any

private fun withContext(
    dispatcher: ScheduledExecutorService,
    continuation: Continuation,
    executable: Executable
) {
    dispatcher.execute {
        val result = executable(Unit)
        continuation.resume(result)
    }
}

private fun async(
    myCoroutineContext: ScheduledExecutorService,
    delegate: MyCoroutine,
    executable: Executable
): MyDeferred {
    // 1. defer
    val myDeferred = MyDeferred()
    myDeferred.delegate = delegate
    myDeferred.myCoroutineContext = myCoroutineContext

    // 2. execute async
    myCoroutineContext.execute {
        val result = executable(Unit)
        myDeferred.result = result
        myDeferred.isCompleted = true
    }

    // 3. return myDeferred
    return myDeferred
}

fun MyCoroutineScope.launch(
    myCoroutineContext: ScheduledExecutorService? = null,
    block: MyCoroutine.(Any) -> Any
) {
    val myCoroutine = object : MyCoroutine() {
        override fun invoke(any: Any): Any = block(any)
    }.apply { this.myCoroutineContext = myCoroutineContext ?: this@launch.myCoroutineContext }

    (myCoroutineContext ?: this.myCoroutineContext).dispatch(myCoroutine)
}

private fun delay(
    myCoroutineContext: ScheduledExecutorService,
    delay: Long,
    continuation: Continuation
) {
    myCoroutineContext.schedule({
        continuation.resume(Unit)
    }, delay, TimeUnit.MILLISECONDS)
}

fun ScheduledExecutorService.dispatch(myCoroutine: MyCoroutine) {
    execute { myCoroutine(Unit) }
}
