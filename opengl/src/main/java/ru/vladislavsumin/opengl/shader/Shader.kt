package ru.vladislavsumin.opengl.shader

import ru.vladislavsumin.opengl.OpenGL.*
import ru.vladislavsumin.opengl.markers.MainThread
import java.io.Closeable

class Shader(
    shaderCode: String,
    val shaderType: ShaderType
) : Closeable {
    val id: Int = glCreateShader(shaderType.shaderGlType)

    var isClosed = false
        private set


    init {
        glShaderSource(id, shaderCode)
        glCompileShader(id)

        val status = glGetShaderi(id, GL_COMPILE_STATUS)
        if (status == 0) {
            glDeleteShader(id)
            val message = glGetShaderInfoLog(id)
            throw ShaderCompileException("Shader compile failed. Shader code: $shaderCode, type: $shaderType, error: $message")
        }
    }

    @MainThread
    override fun close() {
        if (isClosed) throw ShaderAlreadyClosedException("Shader $this already closed")
        isClosed = true
        glDeleteShader(id)
    }
}