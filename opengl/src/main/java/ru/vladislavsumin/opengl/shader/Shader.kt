package ru.vladislavsumin.opengl.shader

import org.lwjgl.opengl.GL33.*
import ru.vladislavsumin.opengl.OpenGlResource
import ru.vladislavsumin.opengl.markers.MainThread
import java.io.Closeable

class Shader(
    shaderCode: String,
    val shaderType: ShaderType
) : OpenGlResource(glCreateShader(shaderType.shaderGlType)) {


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

    internal fun attach(programId: Int) {
        glAttachShader(programId, id)
    }

    @MainThread
    override fun close() {
        super.close()
        glDeleteShader(id)
    }
}