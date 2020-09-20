package ru.vladislav.sumin.blockoftechandmagic

import org.lwjgl.opengl.GL33.*
import ru.vladislav.sumin.blockoftechandmagic.markers.MainThread
import ru.vladislav.sumin.blockoftechandmagic.render.buffer.EBO
import ru.vladislav.sumin.blockoftechandmagic.render.buffer.VAO
import ru.vladislav.sumin.blockoftechandmagic.render.buffer.VBO
import ru.vladislav.sumin.blockoftechandmagic.shader.ShaderManager
import ru.vladislav.sumin.blockoftechandmagic.shader.ShaderProgram
import ru.vladislav.sumin.blockoftechandmagic.shader.ShaderType
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.math.sin

@Singleton
class TestTriangles @Inject constructor(
    private val shaderManager: ShaderManager
) {
    private lateinit var vao: VAO
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

        vbo = VBO()
        ebo = EBO()
        vbo.setData(triangle)
        ebo.setData(indices)

        vao = VAO(vbo, ebo)

    }

    fun draw() {
        program.useProgram()
        glUniform4f(0, 0.0f, (sin(System.currentTimeMillis().toDouble()/400) / 2 + 0.5).toFloat(), 0.0f, 1.0f);
        vao.draw()
    }

    private fun createProgram(): ShaderProgram {
        val vertexShader = shaderManager.loadShader("vertexShader", ShaderType.VERTEX)
        val fragmentShader = shaderManager.loadShader("variableColorShader", ShaderType.FRAGMENT)

        val shaderProgram = shaderManager.createShaderProgram(vertexShader, fragmentShader)

        vertexShader.close()
        fragmentShader.close()

        return shaderProgram
    }
}