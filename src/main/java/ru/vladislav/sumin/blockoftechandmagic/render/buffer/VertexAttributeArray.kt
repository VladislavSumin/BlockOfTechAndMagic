package ru.vladislav.sumin.blockoftechandmagic.render.buffer

import ru.vladislav.sumin.blockoftechandmagic.markers.MainThread
import ru.vladislav.sumin.blockoftechandmagic.render.OpenGL.*

class VertexAttributeArray(
    vararg val attributes: VertexAttribute
) {
    val stride = attributes.sumBy { it.size * it.type.size }

    @MainThread
    fun setAttributes() {
        var offset = 0L
        attributes.forEachIndexed { index, attr ->
            glVertexAttribPointer(index, attr.size, attr.type.glId, attr.normalized, stride, offset)
            offset += attr.size * attr.type.size
            glEnableVertexAttribArray(index)
        }
    }
}