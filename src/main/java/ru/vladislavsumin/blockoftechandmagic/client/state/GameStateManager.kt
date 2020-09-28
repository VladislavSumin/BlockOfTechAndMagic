package ru.vladislavsumin.blockoftechandmagic.client.state

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GameStateManager @Inject constructor() {
    var width: Int = 0
        private set
    var heght: Int = 0
        private set
    var aspect: Float = 0f
        private set

    var isCloseSignalReceived: Boolean = false
        private set

    fun setCloseSignalReceived() {
        isCloseSignalReceived = true
    }

    fun setWindowResolution(w: Int, h: Int) {
        width = w
        heght = h
        aspect = w / h.toFloat()
    }
}