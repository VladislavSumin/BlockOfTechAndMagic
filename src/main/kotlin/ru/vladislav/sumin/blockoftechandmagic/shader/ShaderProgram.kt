package ru.vladislav.sumin.blockoftechandmagic.shader

import ru.vladislav.sumin.blockoftechandmagic.markers.MainThread
import ru.vladislav.sumin.blockoftechandmagic.render.OpenGL
import java.io.Closeable

class ShaderProgram(
        private val gl: OpenGL,
        val programId: Int
) : Closeable {
    var isClosed = false
        private set

    @MainThread
    override fun close() {
        if (isClosed) throw ProgramAlreadyClosedException("Shader $this already closed")
        isClosed = true
        gl.glDeleteProgram(programId)
    }
}