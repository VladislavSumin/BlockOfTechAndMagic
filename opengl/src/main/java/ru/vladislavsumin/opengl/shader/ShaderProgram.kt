package ru.vladislavsumin.opengl.shader

import org.lwjgl.opengl.GL33
import org.lwjgl.system.MemoryStack
import ru.vladislavsumin.opengl.markers.MainThread
import org.lwjgl.opengl.GL33.*
import ru.vladislavsumin.opengl.OpenGlResource
import ru.vladislavsumin.core.utils.use
import java.nio.FloatBuffer

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
    val uniforms: HashMap<String, Uniform> by lazy(LazyThreadSafetyMode.NONE) {
        MemoryStack.stackPush().use { memory ->
            val size = memory.mallocInt(1)
            val type = memory.mallocInt(1)

            val uniformCount = glGetProgrami(id, GL_ACTIVE_UNIFORMS)
            val result = HashMap<String, Uniform>()

            for (index in 0 until uniformCount) {
                val name = glGetActiveUniform(id, index, size, type)
                val uniformLocation = glGetUniformLocation(id, name)

                val uniformType = (UniformType.values().find { it.openGlType == type[0] }
                    ?: throw UnknownUniformType("Unknown uniform type ${type[0]}"))

                val uniform = Uniform(uniformLocation, name, size[0], uniformType)
                result[uniform.name] = uniform
            }
            result
        }
    }

    fun setUniform(name: String, data: Any) {
        setUniform(uniforms[name]!!, data)
    }

    fun setUniform(uniform: Uniform, data: Any) {
        @Suppress("UNNECESSARY_NOT_NULL_ASSERTION")
        when (uniform.type) {
            UniformType.FLOAT_MAT4 -> {
                glUniformMatrix4fv(
                    uniform.id, false, data as FloatBuffer
                )
            }
            UniformType.SAMPLER_2D -> {
                assert(false)
            }
        }!!
    }

    fun useProgram() {
        glUseProgram(id)
    }

    @MainThread
    override fun close() {
        super.close()
        glDeleteProgram(id)
    }

}