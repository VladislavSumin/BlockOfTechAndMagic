package ru.vladislav.sumin.blockoftechandmagic.client.camera

import glm_.glm
import glm_.vec3.Vec3
import org.lwjgl.glfw.GLFW.*
import ru.vladislav.sumin.blockoftechandmagic.client.userinput.UserInputManager
import ru.vladislavsumin.opengl.camera.Camera
import ru.vladislavsumin.opengl.utils.isZero
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

    //TODO make acceleration
    private val cameraSpeed = 4f
    private val sensitivity = 0.4


    private val tmp2 = Vec3()
    private val tmp3 = Vec3()
    private val tmp4 = Vec3()


    fun updatePosition(timeDelta: Double) {
        calculateMovement(timeDelta)
        calculateRotation()
        calculate()
    }


    private fun calculateMovement(timeDelta: Double) {
        val keys = userInputManager.pressedKeys

        val frontXZ = tmp1.put(front)
        frontXZ.y = 0f
        frontXZ.normalizeAssign()

        val posDelta = tmp2.put(0, 0, 0)

        if (keys[GLFW_KEY_W])
            posDelta += frontXZ
        if (keys[GLFW_KEY_S])
            posDelta -= frontXZ
        if (keys[GLFW_KEY_A])
            posDelta -= glm.normalize(glm.cross(front, up, tmp3), tmp4)
        if (keys[GLFW_KEY_D])
            posDelta += glm.normalize(glm.cross(front, up, tmp3), tmp4)
        if (keys[GLFW_KEY_SPACE])
            posDelta += up
        if (keys[GLFW_KEY_LEFT_SHIFT])
            posDelta -= up

        if (!posDelta.isZero()) {
            posDelta.normalizeAssign() *= (cameraSpeed * timeDelta)
            pos += posDelta
        }

    }

    private fun calculateRotation() {
        yaw += userInputManager.deltaX * sensitivity
        pitch += userInputManager.deltaY * sensitivity

        if (pitch > 89) pitch = 89.0
        else if (pitch < -89) pitch = -89.0

        val cosPitch = cos(Math.toRadians(pitch))
        val sinPitch = sin(Math.toRadians(pitch))
        val cosYaw = cos(Math.toRadians(yaw))
        val sinYaw = sin(Math.toRadians(yaw))

        front.x = (cosPitch * cosYaw).toFloat()
        front.y = sinPitch.toFloat()
        front.z = (cosPitch * sinYaw).toFloat()

        front.normalizeAssign()
    }
}