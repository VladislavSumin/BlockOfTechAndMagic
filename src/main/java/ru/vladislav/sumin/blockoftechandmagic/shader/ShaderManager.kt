package ru.vladislav.sumin.blockoftechandmagic.shader

import ru.vladislavsumin.opengl.markers.MainThread
import ru.vladislavsumin.opengl.markers.MainThreadBlocking
import ru.vladislav.sumin.blockoftechandmagic.resource.exceptions.ResourceNotFoundException

interface ShaderManager {
    @MainThreadBlocking
    @Throws(ShaderCompileException::class, ResourceNotFoundException::class)
    fun loadShader(name: String, type: ShaderType): Shader

    @MainThread
    @Throws(ShaderProgramCreateException::class)
    fun createShaderProgram(vararg shader: Shader): ShaderProgram
}