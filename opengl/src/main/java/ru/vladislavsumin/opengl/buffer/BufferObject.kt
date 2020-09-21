package ru.vladislavsumin.opengl.buffer

import org.lwjgl.opengl.GL33.*
import ru.vladislavsumin.opengl.OpenGlResource
import ru.vladislavsumin.opengl.markers.MainThread

abstract class BufferObject : OpenGlResource(glGenBuffers()) {
    abstract val type: Int
    var size = 0
        protected set

    fun bind() {
        glBindBuffer(type, id)
    }

    @MainThread
    final override fun close() {
        super.close()
        glDeleteBuffers(id)
    }
}