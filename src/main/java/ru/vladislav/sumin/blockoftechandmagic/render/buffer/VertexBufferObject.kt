package ru.vladislav.sumin.blockoftechandmagic.render.buffer

import ru.vladislav.sumin.blockoftechandmagic.render.OpenGL.*

class VertexBufferObject : BufferObject() {
    override val type: Int
        get() = GL_ARRAY_BUFFER

    fun setData(data: FloatArray, usage: Int = GL_STATIC_DRAW) {
        size = data.size
        bind()
        glBufferData(GL_ARRAY_BUFFER, data, usage)
    }
}