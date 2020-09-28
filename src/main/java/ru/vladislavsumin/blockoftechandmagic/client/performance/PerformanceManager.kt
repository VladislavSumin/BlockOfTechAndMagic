package ru.vladislavsumin.blockoftechandmagic.client.performance

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PerformanceManager @Inject constructor() {
    companion object {
        private const val FRAME_HISTORY_SIZE = 256
    }

    private val frameHistory = LongArray(FRAME_HISTORY_SIZE)
    private var nextFramePosition = 0

    /**
     * frame time in nanoseconds
     */
    fun commitFrameTime(frameTime: Long) {
        frameHistory[nextFramePosition++] = frameTime
        nextFramePosition %= FRAME_HISTORY_SIZE
        if (nextFramePosition == 0) printStatsTmpFn()
    }

    private fun printStatsTmpFn() {
        val avg = frameHistory.average() / 1_000_000.0
        val max = frameHistory.maxOrNull()!! / 1_000_000.0
        val min = frameHistory.minOrNull()!! / 1_000_000.0
        println("Avg: $avg ms, max: $max ms, min: $min ms")
    }
}