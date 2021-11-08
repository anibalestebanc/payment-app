package com.example.utils.android.testing

import java.util.*
import java.util.concurrent.ThreadLocalRandom

object RandomFactory {
    fun generateString(): String = UUID.randomUUID().toString()
    fun generateInt(): Int = ThreadLocalRandom.current().nextInt(0, 1000 + 1)
    fun generateBoolean(): Boolean = Math.random() < 0.5
}