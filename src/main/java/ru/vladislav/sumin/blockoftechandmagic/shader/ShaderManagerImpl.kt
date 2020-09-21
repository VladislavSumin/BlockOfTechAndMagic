package ru.vladislav.sumin.blockoftechandmagic.shader

import ru.vladislavsumin.opengl.markers.IoThread
import ru.vladislavsumin.opengl.markers.MainThread
import ru.vladislavsumin.opengl.OpenGL.*
import ru.vladislav.sumin.blockoftechandmagic.resource.ResourceManager
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ShaderManagerImpl @Inject constructor(
        private val resourceManager: ResourceManager
) : ShaderManager {
    override fun loadShader(name: String, type: ShaderType): Shader {
        val shaderCode = loadShaderString(name, type)
        val shaderId = compileShader(name, type, shaderCode)
        return Shader(shaderId, type)
    }

    @MainThread
    private fun compileShader(name: String, type: ShaderType, shaderCode: String): Int {
        val shader = glCreateShader(type.shaderGlType)
        glShaderSource(shader, shaderCode)
        glCompileShader(shader)

        val status = glGetShaderi(shader, GL_COMPILE_STATUS)
        if (status == 0) {
            glDeleteShader(shader)
            val message = glGetShaderInfoLog(shader)
            throw ShaderCompileException("Shader compile failed. Shader name: $name, type: $type, error: $message")
        }
        return shader
    }

    override fun createShaderProgram(vararg shader: Shader): ShaderProgram {
        val program = glCreateProgram()
        shader.forEach {
            glAttachShader(program, it.shaderId)
        }
        glLinkProgram(program)

        val status = glGetProgrami(program, GL_LINK_STATUS)
        if (status == 0) {
            glDeleteProgram(program)
            val message = glGetProgramInfoLog(program)
            throw ShaderProgramCreateException("Program compile status failed. Shaders: $shader, error: $message")
        }
        return ShaderProgram(program)
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