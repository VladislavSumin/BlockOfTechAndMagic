package ru.vladislav.sumin.blockoftechandmagic

import ru.vladislavsumin.opengl.markers.MainThread
import ru.vladislavsumin.opengl.buffer.*
import ru.vladislav.sumin.blockoftechandmagic.shader.ShaderManager
import ru.vladislavsumin.opengl.shader.ShaderProgram
import ru.vladislavsumin.opengl.shader.ShaderType
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TestTriangles @Inject constructor(
    private val shaderManager: ShaderManager
) {
    private lateinit var vao: VAO
    private lateinit var program: ShaderProgram

    @MainThread
    fun init() {
        program = createProgram()

        val quad = floatArrayOf(
            0.5f, 0.5f, 0.0f,  // Верхний правый угол
            0.5f, -0.5f, 0.0f,  // Нижний правый угол
            -0.5f, -0.5f, 0.0f,  // Нижний левый угол
            -0.5f, 0.5f, 0.0f   // Верхний левый угол
        )

        val indices = intArrayOf(
            0, 1, 3,   // Первый треугольник
            1, 2, 3    // Второй треугольник
        )

        val triangle = floatArrayOf(
            // Позиции         // Цвета
            0.5f, -0.5f, 0.0f, 1.0f, 0.0f, 0.0f,   // Нижний правый угол
            -0.5f, -0.5f, 0.0f, 0.0f, 1.0f, 0.0f,   // Нижний левый угол
            0.0f, 0.5f, 0.0f, 0.0f, 0.0f, 1.0f    // Верхний угол
        )

        val vbo = VBO()
//        val ebo = EBO()
        vbo.setData(triangle)
//        ebo.setData(indices)

        val attr1 = VertexAttribute(3, VertexAttribute.Type.FLOAT, false)
        val attr2 = VertexAttribute(3, VertexAttribute.Type.FLOAT, false)
        val attrs = VertexAttributeArray(attr1, attr2)
        vao = VAO(vbo, null, attrs)

    }

    fun draw() {
        program.useProgram()
//        glUniform4f(0, 0.0f, (sin(System.currentTimeMillis().toDouble() / 400) / 2 + 0.5).toFloat(), 0.0f, 1.0f);
        vao.draw()
    }

    private fun createProgram(): ShaderProgram {
        val vertexShader = shaderManager.loadShader("vertexShader2", ShaderType.VERTEX)
        val fragmentShader = shaderManager.loadShader("variableColorShader", ShaderType.FRAGMENT)

        val shaderProgram = shaderManager.createShaderProgram(vertexShader, fragmentShader)

        vertexShader.close()
        fragmentShader.close()

        return shaderProgram
    }
}