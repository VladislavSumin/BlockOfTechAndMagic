package ru.vladislavsumin.blockoftechandmagic.client.performance

import glm_.mat4x4.Mat4
import org.lwjgl.system.MemoryUtil
import ru.vladislavsumin.opengl.VAO
import ru.vladislavsumin.opengl.VBO
import ru.vladislavsumin.opengl.buffer.VertexAttribute
import ru.vladislavsumin.opengl.buffer.VertexAttributeArray
import ru.vladislavsumin.opengl.buffer.VertexBufferObject
import ru.vladislavsumin.opengl.shader.ShaderProgram
import java.nio.FloatBuffer
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PerformanceDrawer @Inject constructor(
    private val performanceManager: PerformanceManager
) {
    private lateinit var frameBuffer: FloatBuffer
    private lateinit var vao: VAO
    private lateinit var vbo: VBO
    private val scale = Mat4().apply {
        scale_(3f, 600f, 0f)
    }
    private lateinit var tmpF16: FloatBuffer

    companion object {
        private const val ONE_FRAME_BUFFER_SIZE_FLOAT = 2 * 3 * 2
        private const val ONE_FRAME_BUFFER_SIZE_BYTE = ONE_FRAME_BUFFER_SIZE_FLOAT * 4L
    }

    fun init() {
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
        tmpF16 = MemoryUtil.memAllocFloat(16)
    }

    fun draw(shader: ShaderProgram) {
        drawRect(
            performanceManager.currentFramePosition,
            performanceManager.frameHistory[performanceManager.currentFramePosition] / (1_000_000f * 1000 / 60)
        )
        shader.setUniform("universalMat1", scale to tmpF16)
        vao.draw()
    }

    private fun drawRect(pos: Int, height: Float) {
        frameBuffer.apply {
            position(0)
            put(pos.toFloat()); put(0f)
            put(pos.toFloat() + 1f); put(0f)
            put(pos.toFloat() + 1f); put(height)
            put(pos.toFloat() + 1f); put(height)
            put(pos.toFloat()); put(height)
            put(pos.toFloat()); put(0f)
        }
        vbo.subData(
            pos * ONE_FRAME_BUFFER_SIZE_BYTE,
            frameBuffer
        )
    }

    fun destroy() {
        MemoryUtil.memFree(frameBuffer)
        vao.close()
        MemoryUtil.memFree(tmpF16)
    }
}