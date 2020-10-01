package ru.vladislavsumin.opengl.buffer

import org.lwjgl.opengl.GL33.*


class VertexAttribute(
    val size: Int,
    val type: Type,
    val normalized: Boolean = false,
) {
    enum class Type(
        val glId: Int,
        val size: Int
    ) {
        FLOAT(GL_FLOAT, 4)
    }
}