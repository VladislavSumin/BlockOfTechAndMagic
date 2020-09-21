package ru.vladislavsumin.opengl.buffer

import ru.vladislavsumin.opengl.OpenGL.*
import ru.vladislavsumin.opengl.markers.MainThread
import java.io.Closeable
import java.lang.Exception

abstract class BufferObject : Closeable {
    val id = glGenBuffers()
    abstract val type: Int
    var size = 0
        protected set

    private var isClosed = false

    fun bind() {
        glBindBuffer(type, id)
    }

    @MainThread
    final override fun close() {
        if (isClosed) throw Exception("buffer already closed") //TODO make custom exception
        isClosed = true
        glDeleteBuffers(id)
    }
}