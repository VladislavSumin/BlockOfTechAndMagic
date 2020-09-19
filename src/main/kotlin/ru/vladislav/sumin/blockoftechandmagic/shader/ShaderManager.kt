package ru.vladislav.sumin.blockoftechandmagic.shader

import ru.vladislav.sumin.blockoftechandmagic.markers.MainThreadBlocking
import ru.vladislav.sumin.blockoftechandmagic.shader.exceptions.ShaderException

interface ShaderManager {
    @MainThreadBlocking
    @Throws(ShaderException::class)
    fun loadShader(name: String, type: ShaderType): Shader
}