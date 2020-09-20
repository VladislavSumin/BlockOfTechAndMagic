package ru.vladislav.sumin.blockoftechandmagic.render.buffer

import org.lwjgl.opengl.GL33
import ru.vladislav.sumin.blockoftechandmagic.markers.MainThread
import ru.vladislav.sumin.blockoftechandmagic.render.OpenGL.*
import java.io.Closeable
import java.lang.Exception

class VertexArrayObject(
    val vbo: VBO,
    val ebo: EBO? = null,
    val attributes: VertexAttributeArray,
    private val isCloseChildResources: Boolean = true
) : Closeable {
    val id = glGenVertexArrays()

    init {
        glBindVertexArray(id)

        vbo.bind()
        ebo?.bind()

        attributes.setAttributes()

        glBindVertexArray(0)
    }

    fun draw() {
        glBindVertexArray(id)
        val count = ebo?.size ?: vbo.size / 3
        if (ebo != null) {
            glDrawElements(GL_TRIANGLES, count, GL_UNSIGNED_INT, 0)
        } else {
            GL33.glDrawArrays(GL33.GL_TRIANGLES, 0, count)
        }
        glBindVertexArray(0)
    }

    private var isClosed = false

    @MainThread
    override fun close() {
        if (isClosed) throw Exception("buffer already closed") //TODO make custom exception
        isClosed = true
        if (isCloseChildResources) {
            vbo.close()
            ebo?.close()
        }
        glDeleteVertexArrays(id)
    }
}