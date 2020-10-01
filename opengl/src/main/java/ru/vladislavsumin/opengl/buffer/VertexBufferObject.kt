package ru.vladislavsumin.opengl.buffer

import org.lwjgl.opengl.GL33.*

class VertexBufferObject : BufferObject() {
    override val type: Int
        get() = GL_ARRAY_BUFFER

    fun setData(data: FloatArray, usage: Usage = Usage.STATIC) {
        size = data.size
        bind()
        glBufferData(GL_ARRAY_BUFFER, data, usage.glId)
    }

    enum class Usage(val glId: Int) {
        STATIC(GL_STATIC_DRAW),
        DYNAMIC(GL_DYNAMIC_DRAW),
        STREAM(GL_STREAM_DRAW),
    }
}