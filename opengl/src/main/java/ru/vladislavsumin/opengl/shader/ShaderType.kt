package ru.vladislavsumin.opengl.shader

import org.lwjgl.opengl.GL33.*

enum class ShaderType(
        val extension: String,
        val shaderGlType: Int
) {
    VERTEX(".vsh", GL_VERTEX_SHADER),
    FRAGMENT(".fsh", GL_FRAGMENT_SHADER);
}