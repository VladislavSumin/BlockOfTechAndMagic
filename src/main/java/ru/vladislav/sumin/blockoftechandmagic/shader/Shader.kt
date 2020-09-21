package ru.vladislav.sumin.blockoftechandmagic.shader

import ru.vladislavsumin.opengl.markers.MainThread
import ru.vladislavsumin.opengl.OpenGL.glDeleteShader
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