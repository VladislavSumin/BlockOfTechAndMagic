package ru.vladislav.sumin.blockoftechandmagic.render.buffer

import ru.vladislav.sumin.blockoftechandmagic.render.OpenGL

class VertexAttribute(
    val size: Int,
    val type: Int,
    val normalized: Boolean,
    val stride: Int,
    val offset: Long
) {
    fun setAttribute(index: Int) {
        OpenGL.glVertexAttribPointer(index, size, type, normalized, stride, offset)
    }
}