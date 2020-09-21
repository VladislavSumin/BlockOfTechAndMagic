package ru.vladislavsumin.opengl.buffer

import org.lwjgl.opengl.GL33.*

class VertexBufferObject : BufferObject() {
    override val type: Int
        get() = GL_ARRAY_BUFFER

    fun setData(data: FloatArray, usage: Int = GL_STATIC_DRAW) {
        size = data.size
        bind()
        glBufferData(GL_ARRAY_BUFFER, data, usage)
    }
}