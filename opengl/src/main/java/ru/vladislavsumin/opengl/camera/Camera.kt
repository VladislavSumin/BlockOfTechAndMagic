package ru.vladislavsumin.opengl.camera

import glm_.glm
import glm_.mat4x4.Mat4
import glm_.vec3.Vec3

abstract class Camera {
    protected val pos = Vec3(0f, 0f, 0f)
    protected val front = Vec3(0f, 0f, -1f)
    protected val up = Vec3(0f, 1f, 0f)
    protected val tmp1 = Vec3()

    val matrix = Mat4()

    protected fun calculate() {
        tmp1.put(pos).plusAssign(front)
        glm.lookAt(matrix, pos, tmp1, up)
    }
}