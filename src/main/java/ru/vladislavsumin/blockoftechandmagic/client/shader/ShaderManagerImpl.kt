package ru.vladislavsumin.blockoftechandmagic.client.shader

import ru.vladislavsumin.opengl.markers.IoThread
import ru.vladislavsumin.blockoftechandmagic.resource.ResourceManager
import ru.vladislavsumin.opengl.shader.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ShaderManagerImpl @Inject constructor(
    private val resourceManager: ResourceManager
) : ShaderManager {
    override fun loadShader(name: String, type: ShaderType): Shader {
        val shaderCode = loadShaderString(name, type)
        return Shader(shaderCode, type)
    }

    override fun createShaderProgram(vararg shader: Shader): ShaderProgram {
        return ShaderProgram(*shader)
    }

    @IoThread
    private fun loadShaderString(name: String, type: ShaderType): String {
        return resourceManager.getResourceAsStream(getShaderPath(name, type)).reader()
            .use {
                it.readText()
            }
    }

    private fun getShaderPath(name: String, type: ShaderType): String {
        return "shaders/$name${type.extension}"
    }
}