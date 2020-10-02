package ru.vladislavsumin.blockoftechandmagic.client.render

import glm_.mat4x4.Mat4
import org.lwjgl.system.MemoryUtil
import ru.vladislavsumin.blockoftechandmagic.client.TestGui
import ru.vladislavsumin.blockoftechandmagic.client.performance.PerformanceDrawer
import ru.vladislavsumin.blockoftechandmagic.client.shader.ShaderManager
import ru.vladislavsumin.blockoftechandmagic.client.state.GameStateManager
import ru.vladislavsumin.opengl.OpenGlSateManager
import ru.vladislavsumin.opengl.shader.ShaderProgram
import ru.vladislavsumin.opengl.shader.ShaderType
import java.nio.FloatBuffer
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GuiRender @Inject constructor(
    private val shaderManager: ShaderManager,
    private val gameStateManager: GameStateManager,
    private val testGui: TestGui,
    private val openGlSateManager: OpenGlSateManager,
    private val performanceDrawer: PerformanceDrawer,
) : Render() {
    private lateinit var shader: ShaderProgram
    private val scale = Mat4()
    private val universalMat1 = Mat4()
    private lateinit var tmpF16: FloatBuffer

    override fun init() {
        createShaderProgram()
        tmpF16 = MemoryUtil.memAllocFloat(16)
        testGui.init()
        performanceDrawer.init()
    }

    override fun draw() {
        openGlSateManager.setDepthTest(false)
        openGlSateManager.setCullFace(false)
        calculateScale()
        shader.useProgram()
        shader.setUniform("scale", scale to tmpF16)
//        shader.setUniform("universalMat1", universalMat1 to tmpF16)
//        testGui.draw()
        performanceDrawer.draw(shader)
    }

    private fun calculateScale() {
        //TODO no recalculate if not changed
        scale
            .put(1f)
            .translate_(-1f, -1f, 0f)
            .scale_(2f / gameStateManager.width, 2f / gameStateManager.heght, 1f)
    }

    override fun destroy() {
        shader.close()
        MemoryUtil.memFree(tmpF16)
        testGui.destroy()
        performanceDrawer.destroy()
    }

    private fun createShaderProgram() {
        val vertexShader = shaderManager.loadShader("uiVertexShader", ShaderType.VERTEX)
        val fragmentShader = shaderManager.loadShader("uiColorShader", ShaderType.FRAGMENT)

        shader = shaderManager.createShaderProgram(vertexShader, fragmentShader)

        vertexShader.close()
        fragmentShader.close()
    }
}