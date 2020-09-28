package ru.vladislavsumin.blockoftechandmagic.client.render

import ru.vladislavsumin.blockoftechandmagic.client.shader.ShaderManager
import ru.vladislavsumin.opengl.shader.ShaderProgram
import ru.vladislavsumin.opengl.shader.ShaderType
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GuiRender @Inject constructor(
    private val shaderManager: ShaderManager
) : Render() {
    private lateinit var shader: ShaderProgram

    override fun init() {
        createShaderProgram()
    }

    override fun draw() {
        shader.useProgram()
    }

    override fun destroy() {
        shader.close()
    }

    private fun createShaderProgram() {
        val vertexShader = shaderManager.loadShader("uiVertexShader", ShaderType.VERTEX)
        val fragmentShader = shaderManager.loadShader("uiColorShader", ShaderType.FRAGMENT)

        shader = shaderManager.createShaderProgram(vertexShader, fragmentShader)

        vertexShader.close()
        fragmentShader.close()
    }
}