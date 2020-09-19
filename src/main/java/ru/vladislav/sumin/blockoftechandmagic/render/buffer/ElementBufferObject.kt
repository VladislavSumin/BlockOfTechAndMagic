package ru.vladislav.sumin.blockoftechandmagic.render.buffer

import ru.vladislav.sumin.blockoftechandmagic.render.OpenGL.*

class ElementBufferObject : BufferObject() {
    fun setData(data: IntArray, usage: Int) {
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, id)
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, data, usage)
    }
}