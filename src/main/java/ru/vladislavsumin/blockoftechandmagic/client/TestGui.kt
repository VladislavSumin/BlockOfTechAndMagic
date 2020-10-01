package ru.vladislavsumin.blockoftechandmagic.client

import ru.vladislavsumin.opengl.VAO
import ru.vladislavsumin.opengl.VBO
import ru.vladislavsumin.opengl.buffer.VertexAttribute
import ru.vladislavsumin.opengl.buffer.VertexAttributeArray
import ru.vladislavsumin.opengl.buffer.VertexBufferObject
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TestGui @Inject constructor() {
    lateinit var vao: VAO
    lateinit var vbo: VBO

    fun init() {
        vbo = VBO().apply {

        }
        val vaa = VertexAttributeArray(
            VertexAttribute(3, VertexAttribute.Type.FLOAT)
        )
        vao = VAO(vbo, null, vaa)
    }

    fun draw() {
        vbo.setData(
            floatArrayOf(
                -0.5f, -0.5f, 0.0f,
                0.5f, -0.5f, 0.0f,
                0.0f, 0.5f, 0.0f
            ),
            VertexBufferObject.Usage.STREAM
        )
        vao.draw()
    }

    fun destroy() {
        vao.close()
    }
}