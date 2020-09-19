package ru.vladislav.sumin.blockoftechandmagic.render

import org.lwjgl.opengl.GL20
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OpenGL @Inject constructor() {
    companion object {
        const val GL_VERTEX_SHADER = GL20.GL_VERTEX_SHADER
        const val GL_FRAGMENT_SHADER = GL20.GL_FRAGMENT_SHADER

        const val GL_COMPILE_STATUS = GL20.GL_COMPILE_STATUS
    }

    fun glCreateShader(type: Int): Int {
        return GL20.glCreateShader(type)
    }

    fun glShaderSource(shader: Int, string: CharSequence) {
        GL20.glShaderSource(shader, string)
    }

    fun glCompileShader(shader: Int) {
        GL20.glCompileShader(shader)
    }

    fun glGetShaderi(shader: Int, pname: Int): Int {
        return GL20.glGetShaderi(shader, pname)
    }

    fun glGetShaderInfoLog(shader: Int): String {
        return GL20.glGetShaderInfoLog(shader, GL20.glGetShaderi(shader, GL20.GL_INFO_LOG_LENGTH))
    }

    fun glDeleteShader(shader: Int) {
        GL20.glDeleteShader(shader)
    }
}