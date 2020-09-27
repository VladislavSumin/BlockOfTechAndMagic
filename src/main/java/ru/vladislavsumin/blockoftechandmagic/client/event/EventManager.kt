package ru.vladislavsumin.blockoftechandmagic.client.event

import ru.vladislavsumin.blockoftechandmagic.client.camera.PlayerCamera
import ru.vladislavsumin.blockoftechandmagic.client.userinput.UserInputManager
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EventManager @Inject constructor(
    private val userInputManager: UserInputManager,
    private val playerCamera: PlayerCamera
) {
    fun calculateEvents(deltaTime: Double) {
        userInputManager.calculateUserInput()
        playerCamera.updatePosition(deltaTime)
    }
}