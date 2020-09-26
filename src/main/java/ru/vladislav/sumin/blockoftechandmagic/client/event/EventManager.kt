package ru.vladislav.sumin.blockoftechandmagic.client.event

import ru.vladislav.sumin.blockoftechandmagic.client.camera.PlayerCamera
import ru.vladislav.sumin.blockoftechandmagic.client.userinput.UserInputManager
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