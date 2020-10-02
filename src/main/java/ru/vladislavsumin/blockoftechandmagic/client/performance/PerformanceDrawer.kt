package ru.vladislavsumin.blockoftechandmagic.client.performance

import org.lwjgl.system.MemoryUtil
import ru.vladislavsumin.blockoftechandmagic.client.render.Drawer
import ru.vladislavsumin.opengl.VAO
import ru.vladislavsumin.opengl.VBO
import ru.vladislavsumin.opengl.buffer.VertexAttribute
import ru.vladislavsumin.opengl.buffer.VertexAttributeArray
import ru.vladislavsumin.opengl.buffer.VertexBufferObject
import java.nio.ByteBuffer
import java.nio.FloatBuffer
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PerformanceDrawer @Inject constructor(
    private val performanceManager: PerformanceManager
) : Drawer() {
    private lateinit var frameBuffer: FloatBuffer//TODO use only gpu memory
    private lateinit var vao: VAO
    private lateinit var vbo: VBO

    override fun init() {
        // 2 coord * 3 vertex * 2 vertex count * history size
        frameBuffer = MemoryUtil.memAllocFloat(2 * 3 * 2 * PerformanceManager.FRAME_HISTORY_SIZE)
        vbo = VBO()
        val attr = VertexAttributeArray(
            VertexAttribute(2, VertexAttribute.Type.FLOAT)
        )
        vao = VAO(vbo, null, attr)
    }

    override fun draw() {
        drawRect(
            performanceManager.currentFramePosition,
            performanceManager.frameHistory[performanceManager.currentFramePosition]/100_000f
        )
        vbo.setData(frameBuffer, VertexBufferObject.Usage.STREAM)
        vao.draw()

    }

    private fun drawRect(pos: Int, height: Float) {
        frameBuffer.position(pos * 2 * 3 * 2)
        frameBuffer.put(
            floatArrayOf(
                pos * 1f,      0f,
                pos * 1f + 1f, 0f,
                pos * 1f + 1f, height,
                pos * 1f + 1f, 0f,
                pos * 1f + 1f, height,
                pos * 1f,      height
            )
        )
    }

    override fun destroy() {
        MemoryUtil.memFree(frameBuffer)
        vao.close()
    }
}