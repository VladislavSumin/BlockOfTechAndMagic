package ru.vladislav.sumin.blockoftechandmagic.render.buffer

import ru.vladislav.sumin.blockoftechandmagic.render.OpenGL

class VertexAttribute(
    val size: Int,
    val type: Type,
    val normalized: Boolean,
) {
    enum class Type(
        val glId: Int,
        val size: Int
    ) {
        FLOAT(OpenGL.GL_FLOAT, 4)
    }
}