package ru.vladislavsumin.opengl.buffer

import org.lwjgl.opengl.GL33.*
import java.nio.FloatBuffer

class VertexBufferObject : BufferObject() {
    override val type: Int
        get() = GL_ARRAY_BUFFER

    fun setData(data: FloatArray, usage: Usage = Usage.STATIC) {
        size = data.size * 4 //sizeof(float)
        bind()
        glBufferData(GL_ARRAY_BUFFER, data, usage.glId)
    }

    fun setData(data: FloatBuffer, usage: Usage = Usage.STATIC) {
        size = data.limit() * 4 //sizeof(float)
        bind()
        data.position(0)
        glBufferData(GL_ARRAY_BUFFER, data, usage.glId)
    }


    enum class Usage(val glId: Int) {
        STATIC(GL_STATIC_DRAW),
        DYNAMIC(GL_DYNAMIC_DRAW),
        STREAM(GL_STREAM_DRAW),
    }
}