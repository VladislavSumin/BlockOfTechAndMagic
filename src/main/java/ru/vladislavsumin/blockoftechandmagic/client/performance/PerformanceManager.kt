package ru.vladislavsumin.blockoftechandmagic.client.performance

import sun.misc.GC
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PerformanceManager @Inject constructor() {
    companion object {
        private const val FRAME_HISTORY_SIZE = 256
    }

    private val frameHistory = LongArray(FRAME_HISTORY_SIZE)
    private val gcHistory = BooleanArray(FRAME_HISTORY_SIZE)
    private var nextFramePosition = 0
    private var lastGcTime = 0L

    /**
     * frame time in nanoseconds
     */
    fun commitFrameTime(frameTime: Long) {
        frameHistory[nextFramePosition] = frameTime
        val currentGcTime = GC.maxObjectInspectionAge()
        gcHistory[nextFramePosition] = (currentGcTime < lastGcTime)
        lastGcTime = currentGcTime

        nextFramePosition++
        nextFramePosition %= FRAME_HISTORY_SIZE
        if (nextFramePosition == 0) printStatsTmpFn()
    }

    private fun printStatsTmpFn() {
        val avg = frameHistory.average() / 1_000_000.0
        val max = frameHistory.maxOrNull()!! / 1_000_000.0
        val min = frameHistory.minOrNull()!! / 1_000_000.0
        val count = frameHistory.count { it / 1_000_000.0 > 2.0 }
        val isGcRun = gcHistory.any { it }
        println("Avg: $avg ms, max: $max ms, min: $min ms, isGC:$isGcRun, badFrameCount: $count")
    }
}