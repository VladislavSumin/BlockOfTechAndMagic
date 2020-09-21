package ru.vladislav.sumin.blockoftechandmagic.shader

import org.lwjgl.system.MemoryStack
import ru.vladislavsumin.opengl.markers.MainThread
import ru.vladislavsumin.opengl.OpenGL.*
import ru.vladislav.sumin.blockoftechandmagic.utils.use
import java.io.Closeable

class ShaderProgram(
    val id: Int
) : Closeable {
    var isClosed = false
        private set

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
        if (isClosed) throw ProgramAlreadyClosedException("Shader $this already closed")
        isClosed = true
        glDeleteProgram(id)
    }

    data class Uniform(val id: Int, val name: String, val size: Int, val type: Int)
}