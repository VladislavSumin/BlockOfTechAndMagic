package ru.vladislav.sumin.blockoftechandmagic.client.render

import glm_.glm
import org.lwjgl.system.MemoryUtil
import ru.vladislav.sumin.blockoftechandmagic.TestBlocks
import ru.vladislav.sumin.blockoftechandmagic.client.camera.PlayerCamera
import ru.vladislav.sumin.blockoftechandmagic.client.shader.ShaderManager
import ru.vladislavsumin.opengl.shader.ShaderProgram
import ru.vladislavsumin.opengl.shader.ShaderType
import java.nio.FloatBuffer
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WorldRender @Inject constructor(
    private val shaderManager: ShaderManager,
    private val playerCamera: PlayerCamera,
    private val testBlocks: TestBlocks,

    ) {
    private lateinit var shader: ShaderProgram
    private val projectionMatrix = glm.perspective(45f, 8 / 6f, 0.1f, 100f)


    private lateinit var tempF16: FloatBuffer

    fun init() {
        createShaderProgram()
        allocateTempBuffers()
        testBlocks.init()
    }

    fun draw() {
        shader.useProgram()
        shader.setUniform("view", playerCamera.matrix to tempF16)
        shader.setUniform("projection", projectionMatrix to tempF16)

        testBlocks.draw(shader)
    }

    fun destroy() {
        testBlocks.destroy()
        shader.close()
        freeTempBuffers()
    }

    private fun allocateTempBuffers() {
        tempF16 = MemoryUtil.memAllocFloat(16)
    }

    private fun freeTempBuffers() {
        MemoryUtil.memFree(tempF16)
    }


    private fun createShaderProgram() {
        val vertexShader = shaderManager.loadShader("vertexShader2", ShaderType.VERTEX)
        val fragmentShader = shaderManager.loadShader("variableColorShader", ShaderType.FRAGMENT)

        shader = shaderManager.createShaderProgram(vertexShader, fragmentShader)

        vertexShader.close()
        fragmentShader.close()
    }
}