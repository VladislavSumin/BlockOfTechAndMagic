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
    fun updatePosition(timeDelta: Double) {
        calculateMovement(timeDelta)
        calculate()
    }


    private fun calculateMovement(timeDelta: Double) {
        val keys = userInputManager.pressedKeys
        // Camera controls
        val cameraSpeed = 2f * timeDelta
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