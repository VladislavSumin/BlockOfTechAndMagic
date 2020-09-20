package ru.vladislav.sumin.blockoftechandmagic.render.buffer

import ru.vladislav.sumin.blockoftechandmagic.render.OpenGL.*

class ElementBufferObject : BufferObject() {
    override val type: Int
        get() = GL_ELEMENT_ARRAY_BUFFER

    fun setData(data: IntArray, usage: Int = GL_STATIC_DRAW) {
        size = data.size
        bind()
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, data, usage)
    }
}