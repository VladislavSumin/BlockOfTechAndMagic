package ru.vladislav.sumin.blockoftechandmagic.shader

import ru.vladislav.sumin.blockoftechandmagic.markers.MainThread
import ru.vladislav.sumin.blockoftechandmagic.render.OpenGL.glDeleteShader
import java.io.Closeable

class Shader(
        val shaderId: Int,
        val shaderType: ShaderType
) : Closeable {
    var isClosed = false
        private set

    @MainThread
    override fun close() {
        if (isClosed) throw ShaderAlreadyClosedException("Shader $this already closed")
        isClosed = true
        glDeleteShader(shaderId)
    }
}