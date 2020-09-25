package ru.vladislavsumin.opengl.camera

import glm_.glm
import glm_.mat4x4.Mat4
import glm_.vec3.Vec3

abstract class Camera {
    protected val pos = Vec3(0f, 0f, 0f)
    protected val front = Vec3(0f, 0f, -1f)
    protected val up = Vec3(0f, 1f, 0f)

    val matrix = Mat4()

    fun calculate() {
        //TODO add tmp vector to prevent + operator vector allocation
        glm.lookAt(matrix, pos, pos + front, up)
    }
}