package ru.vladislav.sumin.blockoftechandmagic.render.buffer

import ru.vladislav.sumin.blockoftechandmagic.render.OpenGL.*

class VertexBufferObject : BufferObject() {
    fun setData(data: FloatArray, usage: Int) {
        glBindBuffer(GL_ARRAY_BUFFER, id)
        glBufferData(GL_ARRAY_BUFFER, data, usage)
    }
}