package ru.vladislav.sumin.blockoftechandmagic.shader

import ru.vladislav.sumin.blockoftechandmagic.markers.MainThread
import ru.vladislav.sumin.blockoftechandmagic.render.OpenGL.glDeleteProgram
import ru.vladislav.sumin.blockoftechandmagic.render.OpenGL.glUseProgram
import java.io.Closeable

class ShaderProgram(
        val id: Int
) : Closeable {
    var isClosed = false
        private set

    fun useProgram() {
        glUseProgram(id)
    }

    @MainThread
    override fun close() {
        if (isClosed) throw ProgramAlreadyClosedException("Shader $this already closed")
        isClosed = true
        glDeleteProgram(id)
    }
}