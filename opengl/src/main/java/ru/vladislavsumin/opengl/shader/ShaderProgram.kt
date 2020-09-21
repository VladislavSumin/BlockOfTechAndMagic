package ru.vladislavsumin.opengl.shader

import org.lwjgl.system.MemoryStack
import ru.vladislavsumin.opengl.markers.MainThread
import org.lwjgl.opengl.GL33.*
import ru.vladislavsumin.opengl.OpenGlResource
import ru.vladislavsumin.opengl.utils.use

class ShaderProgram(
    vararg shaders: Shader,
    val isCloseChildAfterCompile: Boolean = false
) : OpenGlResource(glCreateProgram()) {
    init {
        try {
            shaders.forEach {
                it.attach(id)
            }
            glLinkProgram(id)

            val status = glGetProgrami(id, GL_LINK_STATUS)
            if (status == 0) {
                glDeleteProgram(id)
                val message = glGetProgramInfoLog(id)
                throw ShaderProgramCreateException("Program compile status failed. Shaders: $shaders, error: $message")
            }
        } finally {
            if (isCloseChildAfterCompile) {
                shaders.forEach { it.close() }
            }
        }
    }

    @MainThread
    private val uniforms: Array<Uniform> by lazy(LazyThreadSafetyMode.NONE) {
        val uniformCount = glGetProgrami(id, GL_ACTIVE_UNIFORMS)
        Array(uniformCount) { index ->
            MemoryStack.stackPush().use {
                val size = it.mallocInt(1)
                val type = it.mallocInt(1)
                val name = glGetActiveUniform(id, index, size, type)
                Uniform(index, name, size[0], type[0])
            }
        }
    }

    //TODO add function glUniform4f

    fun useProgram() {
        glUseProgram(id)
    }

    @MainThread
    override fun close() {
        super.close()
        glDeleteProgram(id)
    }

    data class Uniform(val id: Int, val name: String, val size: Int, val type: Int)
}