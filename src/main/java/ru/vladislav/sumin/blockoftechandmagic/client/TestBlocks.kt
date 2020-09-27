package ru.vladislav.sumin.blockoftechandmagic.client

import glm_.mat4x4.Mat4
import glm_.vec3.Vec3
import org.lwjgl.system.MemoryUtil
import ru.vladislav.sumin.blockoftechandmagic.client.texture.TextureManager
import ru.vladislavsumin.opengl.VAO
import ru.vladislavsumin.opengl.VBO
import ru.vladislavsumin.opengl.buffer.VertexAttribute
import ru.vladislavsumin.opengl.buffer.VertexAttributeArray
import ru.vladislavsumin.opengl.markers.MainThread
import ru.vladislavsumin.opengl.shader.ShaderProgram
import ru.vladislavsumin.opengl.texture.Texture
import java.nio.FloatBuffer
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TestBlocks @Inject constructor(
    private val textureManager: TextureManager
) {
    private lateinit var vao: VAO
    private lateinit var texture: Texture
    private lateinit var modelMatrix: Mat4
    private lateinit var cubes: Array<Vec3>
    private lateinit var tempF16: FloatBuffer

    @MainThread
    fun init() {
        tempF16 = MemoryUtil.memAllocFloat(16)
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

        cubes = createGround(16, -3f)

        val cube = floatArrayOf(
            // Back face
            -0.5f, -0.5f, -0.5f,  0.0f, 0.0f, // Bottom-left
            0.5f,  0.5f, -0.5f,  1.0f, 1.0f, // top-right
            0.5f, -0.5f, -0.5f,  1.0f, 0.0f, // bottom-right
            0.5f,  0.5f, -0.5f,  1.0f, 1.0f, // top-right
            -0.5f, -0.5f, -0.5f,  0.0f, 0.0f, // bottom-left
            -0.5f,  0.5f, -0.5f,  0.0f, 1.0f, // top-left
            // Front face
            -0.5f, -0.5f,  0.5f,  0.0f, 0.0f, // bottom-left
            0.5f, -0.5f,  0.5f,  1.0f, 0.0f, // bottom-right
            0.5f,  0.5f,  0.5f,  1.0f, 1.0f, // top-right
            0.5f,  0.5f,  0.5f,  1.0f, 1.0f, // top-right
            -0.5f,  0.5f,  0.5f,  0.0f, 1.0f, // top-left
            -0.5f, -0.5f,  0.5f,  0.0f, 0.0f, // bottom-left
            // Left face
            -0.5f,  0.5f,  0.5f,  1.0f, 0.0f, // top-right
            -0.5f,  0.5f, -0.5f,  1.0f, 1.0f, // top-left
            -0.5f, -0.5f, -0.5f,  0.0f, 1.0f, // bottom-left
            -0.5f, -0.5f, -0.5f,  0.0f, 1.0f, // bottom-left
            -0.5f, -0.5f,  0.5f,  0.0f, 0.0f, // bottom-right
            -0.5f,  0.5f,  0.5f,  1.0f, 0.0f, // top-right
            // Right face
            0.5f,  0.5f,  0.5f,  1.0f, 0.0f, // top-left
            0.5f, -0.5f, -0.5f,  0.0f, 1.0f, // bottom-right
            0.5f,  0.5f, -0.5f,  1.0f, 1.0f, // top-right
            0.5f, -0.5f, -0.5f,  0.0f, 1.0f, // bottom-right
            0.5f,  0.5f,  0.5f,  1.0f, 0.0f, // top-left
            0.5f, -0.5f,  0.5f,  0.0f, 0.0f, // bottom-left
            // Bottom face
            -0.5f, -0.5f, -0.5f,  0.0f, 1.0f, // top-right
            0.5f, -0.5f, -0.5f,  1.0f, 1.0f, // top-left
            0.5f, -0.5f,  0.5f,  1.0f, 0.0f, // bottom-left
            0.5f, -0.5f,  0.5f,  1.0f, 0.0f, // bottom-left
            -0.5f, -0.5f,  0.5f,  0.0f, 0.0f, // bottom-right
            -0.5f, -0.5f, -0.5f,  0.0f, 1.0f, // top-right
            // Top face
            -0.5f,  0.5f, -0.5f,  0.0f, 1.0f, // top-left
            0.5f,  0.5f,  0.5f,  1.0f, 0.0f, // bottom-right
            0.5f,  0.5f, -0.5f,  1.0f, 1.0f, // top-right
            0.5f,  0.5f,  0.5f,  1.0f, 0.0f, // bottom-right
            -0.5f,  0.5f, -0.5f,  0.0f, 1.0f, // top-left
            -0.5f,  0.5f,  0.5f,  0.0f, 0.0f  // bottom-left
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

    }

    fun draw(program: ShaderProgram) {
        program.useProgram()
        texture.bindTexture()

        cubes.forEach { pos ->
            modelMatrix =
                Mat4()
                    .translate(pos)
//                    .rotate((sin(System.currentTimeMillis().toDouble() / 400) / 2 + 0.5).toFloat(), Vec3(1f, 0f, 0f))
//                    .rotate((sin(System.currentTimeMillis().toDouble() / 400) / 2 + 0.5).toFloat(), Vec3(0f, 0f, 1f))

            program.setUniform(program.uniforms["model"]!!, modelMatrix to tempF16)
            vao.draw()
        }
    }

    fun destroy() {
        MemoryUtil.memFree(tempF16)
        texture.close()
        vao.close()
    }

    private fun createGround(size: Int, y: Float): Array<Vec3> {
        return Array(size * size) {
            val x = it % size
            val z = it / size
            Vec3(x, y, z)
        }
    }

}