package com.hunter.kotlin.coroutine.learn.coroutine.structure_concurrency

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

fun main() {
    val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        println("========================")
        println("record $throwable to log")
    }
    val coroutineScope = CoroutineScope(SupervisorJob() + coroutineExceptionHandler)

    // 1.启动协程
    coroutineScope.launch {
        // coroutineContext 来自上面外层 launch 的 receiver
        val outerCoroutineContext = coroutineContext

        // 2.启动子协程
        launch {
            // coroutineContext 来自上面内层 launch 的 receiver
            val middleCoroutineContext = coroutineContext

            // 3. 子协程再启动孙协程
            launch {
                val innerCoroutineContext = coroutineContext
                printJobAndChild("outerContextJob", outerCoroutineContext)
                printJobAndChild("middleContextJob", middleCoroutineContext)
                printJobAndChild("innerContextJob", innerCoroutineContext)

                delay(100)
                println("grandson1 will not be cancel due to supervisorJob")
                delay(100)
                println("grandson1 will be cancel by coroutineScope.cancel")
            }
        }
    }

    // 4.启动协程 throw exception
    coroutineScope.launch {
        delay(50)
        throw RuntimeException("outerJob2 exception")
    }

    // 5. 150ms 后取消 coroutineScope
    Thread.sleep(150)
    coroutineScope.cancel()
}

fun printJobAndChild(jobName: String, coroutineContext: CoroutineContext) {
    println("$jobName: ${coroutineContext[Job]}")
    println("${jobName}-Child: ${coroutineContext[Job]!!.children.toList()}")
    println()
}