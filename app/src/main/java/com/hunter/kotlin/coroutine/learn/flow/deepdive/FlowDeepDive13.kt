@file:OptIn(ExperimentalCoroutinesApi::class)

package com.hunter.kotlin.coroutine.learn.flow.deepdive

import com.hunter.kotlin.coroutine.learn.other.printlnWithTime
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    getTopics().flatMapMerge { getArticlesFromTopic(it) }
        .collect { printlnWithTime(it) }
}

fun getTopics(): Flow<String> = flowOf("A", "B", "C")

fun getArticlesFromTopic(topic: String): Flow<String> = flow {
    repeat(3) {
        delay(100) // mock network delay
        emit("$topic: $it")
    }
}
