package ru.vladislav.sumin.blockoftechandmagic.shader

import ru.vladislav.sumin.blockoftechandmagic.markers.IoThread
import ru.vladislav.sumin.blockoftechandmagic.markers.MainThread
import ru.vladislav.sumin.blockoftechandmagic.render.OpenGL
import ru.vladislav.sumin.blockoftechandmagic.resource.ResourceManager
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ShaderManagerImpl @Inject constructor(
        private val gl: OpenGL,
        private val resourceManager: ResourceManager
) : ShaderManager {
    override fun loadShader(name: String, type: ShaderType): Shader {
        val shaderCode = loadShaderString(name, type)
        val shaderId = compileShader(name, type, shaderCode)
        return Shader(gl, shaderId, type)
    }

    @MainThread
    private fun compileShader(name: String, type: ShaderType, shaderCode: String): Int {
        val shader = gl.glCreateShader(type.shaderGlType)
        gl.glShaderSource(shader, shaderCode)
        gl.glCompileShader(shader)

        val status = gl.glGetShaderi(shader, OpenGL.GL_COMPILE_STATUS)
        if (status == 0) {
            gl.glDeleteShader(shader)
            val message = gl.glGetShaderInfoLog(shader)
            throw ShaderCompileException("Shader compile failed. Shader name: $name, type: $type, error: $message")
        }
        return shader
    }

    override fun createShaderProgram(vararg shader: Shader): ShaderProgram {
        val program = gl.glCreateProgram()
        shader.forEach {
            gl.glAttachShader(program, it.shaderId)
        }
        gl.glLinkProgram(program)

        val status = gl.glGetProgrami(program, OpenGL.GL_LINK_STATUS)
        if (status == 0) {
            gl.glDeleteProgram(program)
            val message = gl.glGetProgramInfoLog(program)
            throw ShaderProgramCreateException("Program compile status failed. Shaders: $shader, error: $message")
        }
        return ShaderProgram(gl, program)
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