package ru.vladislavsumin.opengl.buffer

import org.lwjgl.opengl.GL33.*
import ru.vladislavsumin.opengl.markers.MainThread

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