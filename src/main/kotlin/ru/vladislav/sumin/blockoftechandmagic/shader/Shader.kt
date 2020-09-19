package ru.vladislav.sumin.blockoftechandmagic.shader

import ru.vladislav.sumin.blockoftechandmagic.markers.MainThread
import ru.vladislav.sumin.blockoftechandmagic.render.OpenGL
import ru.vladislav.sumin.blockoftechandmagic.shader.exceptions.ShaderAlreadyClosedException
import java.io.Closeable

class Shader(
        private val gl: OpenGL,
        val shaderId: Int,
        val shaderType: ShaderType
) : Closeable {
    var isClosed = false
        private set

    @MainThread
    override fun close() {
        if (isClosed) throw ShaderAlreadyClosedException("Shader $this already closed")
        isClosed = true
        gl.glDeleteShader(shaderId)
    }
}