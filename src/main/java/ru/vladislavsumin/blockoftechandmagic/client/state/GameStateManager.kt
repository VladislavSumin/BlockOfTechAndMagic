package ru.vladislavsumin.blockoftechandmagic.client.state

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GameStateManager @Inject constructor() {
    var isCloseSignalReceived: Boolean = false
        private set

    fun setCloseSignalReceived() {
        isCloseSignalReceived = true
    }
}