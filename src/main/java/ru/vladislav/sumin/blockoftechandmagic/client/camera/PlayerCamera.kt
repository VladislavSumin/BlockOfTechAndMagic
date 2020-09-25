package ru.vladislav.sumin.blockoftechandmagic.client.camera

import glm_.glm
import org.lwjgl.glfw.GLFW.*
import ru.vladislav.sumin.blockoftechandmagic.client.userinput.UserInputManager
import ru.vladislavsumin.opengl.camera.Camera
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PlayerCamera @Inject constructor(
    private val userInputManager: UserInputManager
) : Camera() {
    fun updatePosition() {
        calculateMovement()
        calculate()
    }


    private fun calculateMovement() {
        val keys = userInputManager.pressedKeys
        // Camera controls
        val cameraSpeed = 0.03f
        if (keys[GLFW_KEY_W])
            pos += front * cameraSpeed
        if (keys[GLFW_KEY_S])
            pos -= front * cameraSpeed
        if (keys[GLFW_KEY_A])
            pos -= glm.normalize(glm.cross(front, up)) * cameraSpeed
        if (keys[GLFW_KEY_D])
            pos += glm.normalize(glm.cross(front, up)) * cameraSpeed
        if (keys[GLFW_KEY_SPACE])
            pos += up * cameraSpeed
        if (keys[GLFW_KEY_LEFT_SHIFT])
            pos -= up * cameraSpeed

    }
}