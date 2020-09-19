package ru.vladislav.sumin.blockoftechandmagic.shader

import ru.vladislav.sumin.blockoftechandmagic.markers.MainThread
import ru.vladislav.sumin.blockoftechandmagic.render.OpenGL.glDeleteProgram
import java.io.Closeable

class ShaderProgram(
        val programId: Int
) : Closeable {
    var isClosed = false
        private set

    @MainThread
    override fun close() {
        if (isClosed) throw ProgramAlreadyClosedException("Shader $this already closed")
        isClosed = true
        glDeleteProgram(programId)
    }
}