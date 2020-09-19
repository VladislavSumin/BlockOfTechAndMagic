package ru.vladislav.sumin.blockoftechandmagic.render.buffer

import ru.vladislav.sumin.blockoftechandmagic.markers.MainThread
import ru.vladislav.sumin.blockoftechandmagic.render.OpenGL.*
import java.io.Closeable
import java.lang.Exception

abstract class BufferObject : Closeable {
    val id = glGenBuffers()

    private var isClosed = false

    @MainThread
    final override fun close() {
        if (isClosed) throw Exception("buffer already closed") //TODO make custom exception
        isClosed = true
        glDeleteBuffers(id)
    }
}