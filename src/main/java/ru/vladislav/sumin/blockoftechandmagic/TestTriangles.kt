package ru.vladislav.sumin.blockoftechandmagic

import glm.mat._4.Mat4
import org.lwjgl.opengl.GL33
import ru.vladislavsumin.opengl.markers.MainThread
import ru.vladislavsumin.opengl.buffer.*
import ru.vladislav.sumin.blockoftechandmagic.client.shader.ShaderManager
import ru.vladislav.sumin.blockoftechandmagic.client.texture.TextureManager
import ru.vladislavsumin.opengl.EBO
import ru.vladislavsumin.opengl.VAO
import ru.vladislavsumin.opengl.VBO
import ru.vladislavsumin.opengl.shader.ShaderProgram
import ru.vladislavsumin.opengl.shader.ShaderType
import ru.vladislavsumin.opengl.texture.Texture
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.math.sin

@Singleton
class TestTriangles @Inject constructor(
    private val shaderManager: ShaderManager,
    private val textureManager: TextureManager
) {
    private lateinit var vao: VAO
    private lateinit var program: ShaderProgram
    private lateinit var texture: Texture
    private lateinit var modelMatrix: Mat4
    private lateinit var viewMatrix: Mat4
    private lateinit var projectionMatrix: Mat4

    @MainThread
    fun init() {
        program = createProgram()

        val cube = floatArrayOf(
            -0.5f, -0.5f, -0.5f,  0.0f, 0.0f,
            0.5f, -0.5f, -0.5f,  1.0f, 0.0f,
            0.5f,  0.5f, -0.5f,  1.0f, 1.0f,
            0.5f,  0.5f, -0.5f,  1.0f, 1.0f,
            -0.5f,  0.5f, -0.5f,  0.0f, 1.0f,
            -0.5f, -0.5f, -0.5f,  0.0f, 0.0f,

            -0.5f, -0.5f,  0.5f,  0.0f, 0.0f,
            0.5f, -0.5f,  0.5f,  1.0f, 0.0f,
            0.5f,  0.5f,  0.5f,  1.0f, 1.0f,
            0.5f,  0.5f,  0.5f,  1.0f, 1.0f,
            -0.5f,  0.5f,  0.5f,  0.0f, 1.0f,
            -0.5f, -0.5f,  0.5f,  0.0f, 0.0f,

            -0.5f,  0.5f,  0.5f,  1.0f, 0.0f,
            -0.5f,  0.5f, -0.5f,  1.0f, 1.0f,
            -0.5f, -0.5f, -0.5f,  0.0f, 1.0f,
            -0.5f, -0.5f, -0.5f,  0.0f, 1.0f,
            -0.5f, -0.5f,  0.5f,  0.0f, 0.0f,
            -0.5f,  0.5f,  0.5f,  1.0f, 0.0f,

            0.5f,  0.5f,  0.5f,  1.0f, 0.0f,
            0.5f,  0.5f, -0.5f,  1.0f, 1.0f,
            0.5f, -0.5f, -0.5f,  0.0f, 1.0f,
            0.5f, -0.5f, -0.5f,  0.0f, 1.0f,
            0.5f, -0.5f,  0.5f,  0.0f, 0.0f,
            0.5f,  0.5f,  0.5f,  1.0f, 0.0f,

            -0.5f, -0.5f, -0.5f,  0.0f, 1.0f,
            0.5f, -0.5f, -0.5f,  1.0f, 1.0f,
            0.5f, -0.5f,  0.5f,  1.0f, 0.0f,
            0.5f, -0.5f,  0.5f,  1.0f, 0.0f,
            -0.5f, -0.5f,  0.5f,  0.0f, 0.0f,
            -0.5f, -0.5f, -0.5f,  0.0f, 1.0f,

            -0.5f,  0.5f, -0.5f,  0.0f, 1.0f,
            0.5f,  0.5f, -0.5f,  1.0f, 1.0f,
            0.5f,  0.5f,  0.5f,  1.0f, 0.0f,
            0.5f,  0.5f,  0.5f,  1.0f, 0.0f,
            -0.5f,  0.5f,  0.5f,  0.0f, 0.0f,
            -0.5f,  0.5f, -0.5f,  0.0f, 1.0f
        )

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
            // Позиции          // Цвета             // Текстурные координаты
            0.5f, 0.5f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f,   // Верхний правый
            0.5f, -0.5f, 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 0.0f,   // Нижний правый
            -0.5f, -0.5f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f,   // Нижний левый
            -0.5f, 0.5f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f    // Верхний левый
        )

        val vbo = VBO()
        val ebo = EBO()
        vbo.setData(cube)
        ebo.setData(indices)

        val attr1 = VertexAttribute(3, VertexAttribute.Type.FLOAT, false)
//        val attr2 = VertexAttribute(3, VertexAttribute.Type.FLOAT, false)
        val attr3 = VertexAttribute(2, VertexAttribute.Type.FLOAT, false)
        val attrs = VertexAttributeArray(attr1, attr3)
        vao = VAO(vbo, null, attrs)

        texture = textureManager.loadTexture("testTexture")

        modelMatrix = Mat4()
        viewMatrix = Mat4().translate(0f, 0f, -3f)
        projectionMatrix = Mat4().perspective(Math.toRadians(45.0).toFloat(), 1f, 0.1f, 100f)

    }

    fun draw() {
        program.useProgram()
        modelMatrix
            .identity()
            .rotateX((sin(System.currentTimeMillis().toDouble() / 400) / 2 + 0.5).toFloat())
            .rotateZ((sin(System.currentTimeMillis().toDouble() / 400) / 2 + 0.5).toFloat())

        GL33.glUniformMatrix4fv(GL33.glGetUniformLocation(program.id,"model"), false, modelMatrix.toFa_())
        GL33.glUniformMatrix4fv(GL33.glGetUniformLocation(program.id,"view"), false, viewMatrix.toFa_())
        GL33.glUniformMatrix4fv(GL33.glGetUniformLocation(program.id,"projection"), false, projectionMatrix.toFa_())
        texture.bindTexture()
//        GL33.glUniform4f(0, 0.0f, (sin(System.currentTimeMillis().toDouble() / 400) / 2 + 0.5).toFloat(), 0.0f, 1.0f);
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