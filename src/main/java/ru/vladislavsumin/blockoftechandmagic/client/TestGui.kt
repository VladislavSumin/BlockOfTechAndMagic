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
        vbo = VBO()
        val vaa = VertexAttributeArray(
            VertexAttribute(2, VertexAttribute.Type.FLOAT)
        )
        vao = VAO(vbo, null, vaa)
    }

    fun draw() {
        vbo.setData(
            floatArrayOf(
                0f, 0f,
                800f, 0f,
                800f, 600f,
            ),

            VertexBufferObject.Usage.STREAM
        )
        vao.draw()
    }

    fun destroy() {
        vao.close()
    }
}