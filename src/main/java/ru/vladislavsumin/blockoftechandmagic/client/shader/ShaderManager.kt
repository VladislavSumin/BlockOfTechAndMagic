package ru.vladislavsumin.blockoftechandmagic.client.shader

import ru.vladislavsumin.opengl.markers.MainThread
import ru.vladislavsumin.opengl.markers.MainThreadBlocking
import ru.vladislavsumin.blockoftechandmagic.resource.exceptions.ResourceNotFoundException
import ru.vladislavsumin.opengl.shader.*

interface ShaderManager {
    @MainThreadBlocking
    @Throws(ShaderCompileException::class, ResourceNotFoundException::class)
    fun loadShader(name: String, type: ShaderType): Shader

    @MainThread
    @Throws(ShaderProgramCreateException::class)
    fun createShaderProgram(vararg shader: Shader): ShaderProgram
}