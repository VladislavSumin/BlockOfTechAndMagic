package ru.vladislavsumin.opengl.shader

import org.lwjgl.opengl.GL33.*


enum class UniformType(val openGlType: Int) {
    FLOAT_MAT4(GL_FLOAT_MAT4),
    SAMPLER_2D(GL_SAMPLER_2D)
}