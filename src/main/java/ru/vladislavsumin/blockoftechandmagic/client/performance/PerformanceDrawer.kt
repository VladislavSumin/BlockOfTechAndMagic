package ru.vladislavsumin.blockoftechandmagic.client.performance

import org.lwjgl.system.MemoryUtil
import ru.vladislavsumin.blockoftechandmagic.client.render.Drawer
import ru.vladislavsumin.opengl.VAO
import ru.vladislavsumin.opengl.VBO
import ru.vladislavsumin.opengl.buffer.VertexAttribute
import ru.vladislavsumin.opengl.buffer.VertexAttributeArray
import ru.vladislavsumin.opengl.buffer.VertexBufferObject
import java.nio.FloatBuffer
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PerformanceDrawer @Inject constructor(
    private val performanceManager: PerformanceManager
) : Drawer() {
    private lateinit var frameBuffer: FloatBuffer
    private lateinit var vao: VAO
    private lateinit var vbo: VBO

    companion object {
        private const val ONE_FRAME_BUFFER_SIZE_FLOAT = 2 * 3 * 2
        private const val ONE_FRAME_BUFFER_SIZE_BYTE = ONE_FRAME_BUFFER_SIZE_FLOAT * 4L
    }

    override fun init() {
        frameBuffer = MemoryUtil.memAllocFloat(ONE_FRAME_BUFFER_SIZE_FLOAT)
        vbo = VBO()
        vbo.allocate(
            ONE_FRAME_BUFFER_SIZE_BYTE * PerformanceManager.FRAME_HISTORY_SIZE,
            VertexBufferObject.Usage.STREAM
        )
        val attr = VertexAttributeArray(
            VertexAttribute(2, VertexAttribute.Type.FLOAT)
        )
        vao = VAO(vbo, null, attr)
    }

    override fun draw() {
        drawRect(
            performanceManager.currentFramePosition,
            performanceManager.frameHistory[performanceManager.currentFramePosition] / 100_000f
        )
        vao.draw()
    }

    private fun drawRect(pos: Int, height: Float) {
        frameBuffer.apply {
            position(0)
            put(pos.toFloat()); put(0f)
            put(pos.toFloat() + 1f); put(0f)
            put(pos.toFloat() + 1f); put(height)
            put(pos.toFloat() + 1f); put(0f)
            put(pos.toFloat() + 1f); put(height)
            put(pos.toFloat()); put(height)
        }
        vbo.subData(
            pos * ONE_FRAME_BUFFER_SIZE_BYTE,
            frameBuffer
        )
    }

    override fun destroy() {
        MemoryUtil.memFree(frameBuffer)
        vao.close()
    }
}