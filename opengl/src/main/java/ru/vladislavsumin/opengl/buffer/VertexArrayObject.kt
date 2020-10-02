package ru.vladislavsumin.opengl.buffer

import org.lwjgl.opengl.GL33.*
import ru.vladislavsumin.opengl.EBO
import ru.vladislavsumin.opengl.VBO
import ru.vladislavsumin.opengl.markers.MainThread
import java.io.Closeable

class VertexArrayObject(
    val vbo: VBO,
    val ebo: EBO? = null,
    val attributes: VertexAttributeArray,
    val mode: Mode = Mode.TRIANGLES,
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
        val count = ebo?.size ?: (vbo.size / attributes.stride)
        if (ebo != null) {
            assert(false)//todo check logic
            glDrawElements(mode.glId, count, GL_UNSIGNED_INT, 0)
        } else {
            glDrawArrays(mode.glId, 0, count)
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

    enum class Mode(val glId: Int) {
        POINTS(GL_POINT),
        LINES(GL_LINES),
        LINE_LOOP(GL_LINE_LOOP),
        LINE_STRIP(GL_LINE_STRIP),
        TRIANGLES(GL_TRIANGLES),
        TRIANGLE_STRIP(GL_TRIANGLE_STRIP),
        TRIANGLE_FAN(GL_TRIANGLE_FAN),
        QUADS(GL_QUADS),
        QUAD_STRIP(GL_QUAD_STRIP),
        POLYGON(GL_POLYGON),
    }
}