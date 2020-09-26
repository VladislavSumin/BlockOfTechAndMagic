package ru.vladislav.sumin.blockoftechandmagic

import glm_.glm
import glm_.mat4x4.Mat4
import glm_.vec3.Vec3
import org.lwjgl.opengl.GL33
import ru.vladislav.sumin.blockoftechandmagic.client.camera.PlayerCamera
import ru.vladislav.sumin.blockoftechandmagic.client.shader.ShaderManager
import ru.vladislav.sumin.blockoftechandmagic.client.texture.TextureManager
import ru.vladislavsumin.opengl.EBO
import ru.vladislavsumin.opengl.VAO
import ru.vladislavsumin.opengl.VBO
import ru.vladislavsumin.opengl.buffer.VertexAttribute
import ru.vladislavsumin.opengl.buffer.VertexAttributeArray
import ru.vladislavsumin.opengl.markers.MainThread
import ru.vladislavsumin.opengl.shader.ShaderProgram
import ru.vladislavsumin.opengl.shader.ShaderType
import ru.vladislavsumin.opengl.texture.Texture
import java.nio.FloatBuffer
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.math.cos
import kotlin.math.sin

@Singleton
class TextBlocks @Inject constructor(
    private val shaderManager: ShaderManager,
    private val textureManager: TextureManager,
    private val playerCamera: PlayerCamera
) {
    private lateinit var vao: VAO
    private lateinit var program: ShaderProgram
    private lateinit var texture: Texture
    private lateinit var modelMatrix: Mat4
    private lateinit var projectionMatrix: Mat4
    private lateinit var cubes: Array<Vec3>

    @MainThread
    fun init() {
        program = createProgram()
        val uniforms = program.uniforms

//        cubes = arrayOf(
//            Vec3(0.0f, 0.0f, 0.0f),
//            Vec3(2.0f, 5.0f, -15.0f),
//            Vec3(-1.5f, -2.2f, -2.5f),
//            Vec3(-3.8f, -2.0f, -12.3f),
//            Vec3(2.4f, -0.4f, -3.5f),
//            Vec3(-1.7f, 3.0f, -7.5f),
//            Vec3(1.3f, -2.0f, -2.5f),
//            Vec3(1.5f, 2.0f, -2.5f),
//            Vec3(1.5f, 0.2f, -1.5f),
//            Vec3(-1.3f, 1.0f, -1.5f)
//        )

        cubes = createGround(15, -3f)

        val cube = floatArrayOf(
            -0.5f, -0.5f, -0.5f, 0.0f, 0.0f,
            0.5f, -0.5f, -0.5f, 1.0f, 0.0f,
            0.5f, 0.5f, -0.5f, 1.0f, 1.0f,
            0.5f, 0.5f, -0.5f, 1.0f, 1.0f,
            -0.5f, 0.5f, -0.5f, 0.0f, 1.0f,
            -0.5f, -0.5f, -0.5f, 0.0f, 0.0f,

            -0.5f, -0.5f, 0.5f, 0.0f, 0.0f,
            0.5f, -0.5f, 0.5f, 1.0f, 0.0f,
            0.5f, 0.5f, 0.5f, 1.0f, 1.0f,
            0.5f, 0.5f, 0.5f, 1.0f, 1.0f,
            -0.5f, 0.5f, 0.5f, 0.0f, 1.0f,
            -0.5f, -0.5f, 0.5f, 0.0f, 0.0f,

            -0.5f, 0.5f, 0.5f, 1.0f, 0.0f,
            -0.5f, 0.5f, -0.5f, 1.0f, 1.0f,
            -0.5f, -0.5f, -0.5f, 0.0f, 1.0f,
            -0.5f, -0.5f, -0.5f, 0.0f, 1.0f,
            -0.5f, -0.5f, 0.5f, 0.0f, 0.0f,
            -0.5f, 0.5f, 0.5f, 1.0f, 0.0f,

            0.5f, 0.5f, 0.5f, 1.0f, 0.0f,
            0.5f, 0.5f, -0.5f, 1.0f, 1.0f,
            0.5f, -0.5f, -0.5f, 0.0f, 1.0f,
            0.5f, -0.5f, -0.5f, 0.0f, 1.0f,
            0.5f, -0.5f, 0.5f, 0.0f, 0.0f,
            0.5f, 0.5f, 0.5f, 1.0f, 0.0f,

            -0.5f, -0.5f, -0.5f, 0.0f, 1.0f,
            0.5f, -0.5f, -0.5f, 1.0f, 1.0f,
            0.5f, -0.5f, 0.5f, 1.0f, 0.0f,
            0.5f, -0.5f, 0.5f, 1.0f, 0.0f,
            -0.5f, -0.5f, 0.5f, 0.0f, 0.0f,
            -0.5f, -0.5f, -0.5f, 0.0f, 1.0f,

            -0.5f, 0.5f, -0.5f, 0.0f, 1.0f,
            0.5f, 0.5f, -0.5f, 1.0f, 1.0f,
            0.5f, 0.5f, 0.5f, 1.0f, 0.0f,
            0.5f, 0.5f, 0.5f, 1.0f, 0.0f,
            -0.5f, 0.5f, 0.5f, 0.0f, 0.0f,
            -0.5f, 0.5f, -0.5f, 0.0f, 1.0f
        )

        val cubePack = FloatArray(cube.size * 1)
        for (i in 0 until cubePack.size / cube.size) {
            System.arraycopy(cube, 0, cubePack, i * cube.size, cube.size)
        }


        val vbo = VBO()
        vbo.setData(cubePack)

        val attr1 = VertexAttribute(3, VertexAttribute.Type.FLOAT, false)
        val attr3 = VertexAttribute(2, VertexAttribute.Type.FLOAT, false)
        val attrs = VertexAttributeArray(attr1, attr3)
        vao = VAO(vbo, null, attrs)

        texture = textureManager.loadTexture("testTexture")

        modelMatrix = Mat4(1f)
        projectionMatrix = glm.perspective(45f, 8 / 6f, 0.1f, 100f)

    }

    fun draw() {
        program.useProgram()
        texture.bindTexture()

        val tmp = FloatBuffer.allocate(16)

        cubes.forEach { pos ->
            modelMatrix =
                Mat4()
                    .translate(pos)
//                    .rotate((sin(System.currentTimeMillis().toDouble() / 400) / 2 + 0.5).toFloat(), Vec3(1f, 0f, 0f))
//                    .rotate((sin(System.currentTimeMillis().toDouble() / 400) / 2 + 0.5).toFloat(), Vec3(0f, 0f, 1f))

            GL33.glUniformMatrix4fv(GL33.glGetUniformLocation(program.id, "model"), false, modelMatrix.to(tmp).array())
            GL33.glUniformMatrix4fv(
                GL33.glGetUniformLocation(program.id, "view"),
                false,
                playerCamera.matrix.to(tmp).array()
            )
            GL33.glUniformMatrix4fv(
                GL33.glGetUniformLocation(program.id, "projection"),
                false,
                projectionMatrix.to(tmp).array()
            )
//        GL33.glUniform4f(0, 0.0f, (sin(System.currentTimeMillis().toDouble() / 400) / 2 + 0.5).toFloat(), 0.0f, 1.0f);
            vao.draw()
        }
    }

    private fun createGround(size: Int, y: Float): Array<Vec3> {
        return Array(size * size) {
            val x = it % size
            val z = it / size
            Vec3(x, y, z)
        }
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