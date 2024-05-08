package com.hunter.kotlin.coroutine.learn.coroutine.structure_concurrency

import kotlinx.coroutines.CompletableJob
import kotlinx.coroutines.Job

fun main() {
    val job1 = createJob()
    println("----------")
    job1.complete()
    printJobState(job1)

    println()

    val job2 = createJob()
    println("----------")
    job2.cancel()
    printJobState(job2)
}

private fun createJob(): CompletableJob {
    val job = Job()
    println(job)
    printJobState(job)
    job.invokeOnCompletion { println("$job completed") }
    return job
}

private fun printJobState(job: CompletableJob) {
    println("isActive: ${job.isActive}")
    println("isCompleted: ${job.isCompleted}")
    println("isCancelled: ${job.isCancelled}")
}