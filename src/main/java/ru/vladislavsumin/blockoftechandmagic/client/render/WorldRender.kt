package ru.vladislavsumin.blockoftechandmagic.client.render

import glm_.glm
import glm_.mat4x4.Mat4
import org.lwjgl.system.MemoryUtil
import ru.vladislavsumin.blockoftechandmagic.client.TestBlocks
import ru.vladislavsumin.blockoftechandmagic.client.camera.PlayerCamera
import ru.vladislavsumin.blockoftechandmagic.client.shader.ShaderManager
import ru.vladislavsumin.blockoftechandmagic.client.state.GameStateManager
import ru.vladislavsumin.opengl.OpenGlSateManager
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
    private val gameStateManager: GameStateManager,
    private val openGlSateManager: OpenGlSateManager

) : Render() {
    private lateinit var shader: ShaderProgram
    private val projectionMatrix = Mat4()


    private lateinit var tempF16: FloatBuffer

    override fun init() {
        createShaderProgram()
        allocateTempBuffers()
        testBlocks.init()
    }

    override fun draw() {
        openGlSateManager.setDepthTest(true)
        openGlSateManager.setCullFace(true)

        shader.useProgram()

        //TODO no recalculate if not changed
        glm.perspective(projectionMatrix, 45f, gameStateManager.aspect, 0.1f, 100f)

        shader.setUniform("view", playerCamera.matrix to tempF16)
        shader.setUniform("projection", projectionMatrix to tempF16)

        testBlocks.draw(shader)
    }

    override fun destroy() {
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
        val vertexShader = shaderManager.loadShader("vertexShader", ShaderType.VERTEX)
        val fragmentShader = shaderManager.loadShader("colorShader", ShaderType.FRAGMENT)

        shader = shaderManager.createShaderProgram(vertexShader, fragmentShader)

        vertexShader.close()
        fragmentShader.close()
    }
}