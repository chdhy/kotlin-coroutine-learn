package com.hunter.kotlin.coroutine.learn.coroutine.async2sync

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.system.measureTimeMillis

fun main() = runBlocking {
    val nonTimeCost = measureTimeMillis { nonTimeCost() }
    println("nonTimeCost: $nonTimeCost")

    val timeCost = measureTimeMillis { println(sqrt(3, 1000)) }
    println("timeCost: $timeCost")
}

// 开根号计算
fun sqrt(number: Long, precision: Int): BigDecimal {
    var x0 = BigDecimal.valueOf(1.0)
    var x1: BigDecimal
    val x2 = BigDecimal.valueOf(number)

    do {
        x1 = x0
        x0 = x1 - (x1.pow(2) - x2).divide(x1 * x2, precision, RoundingMode.HALF_UP)
    } while ((x0 - x1).abs() > BigDecimal.ONE.divide(BigDecimal.TEN.pow(precision)))

    return x0.setScale(precision, RoundingMode.HALF_UP)
}

suspend fun nonTimeCost() = delay(0)