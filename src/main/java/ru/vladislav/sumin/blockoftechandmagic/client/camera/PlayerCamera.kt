package ru.vladislav.sumin.blockoftechandmagic.client.camera

import glm_.glm
import org.lwjgl.glfw.GLFW.*
import ru.vladislav.sumin.blockoftechandmagic.client.userinput.UserInputManager
import ru.vladislavsumin.opengl.camera.Camera
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.math.cos
import kotlin.math.sin

@Singleton
class PlayerCamera @Inject constructor(
    private val userInputManager: UserInputManager
) : Camera() {

    private var yaw = -90.0
    private var pitch = 0.0

    fun updatePosition(timeDelta: Double) {
        calculateMovement(timeDelta)
        calculateRotation()
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

    private fun calculateRotation() {
        val sensitivity = 0.4
        yaw += userInputManager.deltaX * sensitivity
        pitch += userInputManager.deltaY * sensitivity

        if (pitch > 89) pitch = 89.0
        else if (pitch < -89) pitch = -89.0

        front.x = (cos(Math.toRadians(pitch)) * cos(Math.toRadians(yaw))).toFloat()
        front.y = sin(Math.toRadians(pitch)).toFloat()
        front.z = (cos(Math.toRadians(pitch)) * sin(Math.toRadians(yaw))).toFloat()

        front.normalize(tmp1)
    }
}