package ru.vladislav.sumin.blockoftechandmagic

import org.lwjgl.BufferUtils
import org.lwjgl.opengl.GL15
import org.lwjgl.opengl.GL33.*
import ru.vladislav.sumin.blockoftechandmagic.markers.MainThread
import ru.vladislav.sumin.blockoftechandmagic.render.buffer.EBO
import ru.vladislav.sumin.blockoftechandmagic.render.buffer.VBO
import ru.vladislav.sumin.blockoftechandmagic.shader.ShaderManager
import ru.vladislav.sumin.blockoftechandmagic.shader.ShaderProgram
import ru.vladislav.sumin.blockoftechandmagic.shader.ShaderType
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TestTriangles @Inject constructor(
        private val shaderManager: ShaderManager) {
    private var vao = 0
    private lateinit var ebo: EBO
    private lateinit var vbo: VBO
    private lateinit var program: ShaderProgram

    @MainThread
    fun init() {
        program = createProgram()

        val triangle = floatArrayOf(
                0.5f, 0.5f, 0.0f,  // Верхний правый угол
                0.5f, -0.5f, 0.0f,  // Нижний правый угол
                -0.5f, -0.5f, 0.0f,  // Нижний левый угол
                -0.5f, 0.5f, 0.0f   // Верхний левый угол
        )

        val indices = intArrayOf(
                0, 1, 3,   // Первый треугольник
                1, 2, 3    // Второй треугольник
        )

        vao = glGenVertexArrays()
        vbo = VBO()
        ebo = EBO()

        glBindVertexArray(vao)

        vbo.setData(triangle, GL_STATIC_DRAW)
        ebo.setData(indices, GL_STATIC_DRAW)

        glVertexAttribPointer(0, 3, GL_FLOAT, false, 3 * 4, 0)
        glEnableVertexAttribArray(0)

        glBindVertexArray(0)

    }

    fun draw() {
        glUseProgram(program.programId)
        glBindVertexArray(vao)
        glDrawElements(GL_TRIANGLES, 6, GL_UNSIGNED_INT, 0)
        glBindVertexArray(0)
    }

    private fun createProgram(): ShaderProgram {
        val vertexShader = shaderManager.loadShader("vertexShader", ShaderType.VERTEX)
        val fragmentShader = shaderManager.loadShader("fragmentShader", ShaderType.FRAGMENT)

        val shaderProgram = shaderManager.createShaderProgram(vertexShader, fragmentShader)

        vertexShader.close()
        fragmentShader.close()

        return shaderProgram
    }
}