package com.hunter.kotlin.coroutine.learn.flow.deepdive

import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    callbackSample()
    println()
    callbackFlowSample()
}

private fun callbackFlowSample() {
    // 1. view
    val view = View()

    // 2. callbackFlow
    val callbackFlow = callbackFlow {
        // 2.1 register callback
        view.onClick {
            launch { send(view) }
        }
        // 2.2 unregister callback
        // will be auto call when cancel flow
        awaitClose { view.removeClick() }
    }

    // 3. run
    runBlocking {
        // 3.1 collect
        val job = launch {
            callbackFlow.collect { println("View click from flow") }
        }
        delay(100)

        // 3.2 mock user click
        click(view)
        delay(100)

        // 3.3 cancel flow collect
        job.cancel()
    }
}

private fun callbackSample() {
    val view = View()
    view.onClick { println("View click from callback") }
    click(view)
}

private fun click(view: View) = repeat(3) { view.mockUserClick() }


class View {
    private var onClick: ((View) -> Unit)? = null

    fun mockUserClick() = onClick?.invoke(this)

    fun onClick(onClick: (View) -> Unit) {
        this.onClick = onClick
    }

    fun removeClick() {
        this.onClick = null
        println("removeClick")
    }
}