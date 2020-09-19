package ru.vladislav.sumin.blockoftechandmagic.shader

import ru.vladislav.sumin.blockoftechandmagic.markers.MainThread
import ru.vladislav.sumin.blockoftechandmagic.markers.MainThreadBlocking
import ru.vladislav.sumin.blockoftechandmagic.resource.exceptions.ResourceNotFoundException
import ru.vladislav.sumin.blockoftechandmagic.shader.exceptions.CreateShaderProgramException
import ru.vladislav.sumin.blockoftechandmagic.shader.exceptions.ShaderCompileException

interface ShaderManager {
    @MainThreadBlocking
    @Throws(ShaderCompileException::class, ResourceNotFoundException::class)
    fun loadShader(name: String, type: ShaderType): Shader

    @MainThread
    @Throws(CreateShaderProgramException::class)
    fun createShaderProgram(vararg shader: Shader): ShaderProgram
}