package ru.vladislav.sumin.blockoftechandmagic.shader

import ru.vladislav.sumin.blockoftechandmagic.render.OpenGL

enum class ShaderType(
        val extension: String,
        val shaderGlType: Int
) {
    VERTEX(".vsh", OpenGL.GL_VERTEX_SHADER),
    FRAGMENT(".fsh", OpenGL.GL_FRAGMENT_SHADER);
}