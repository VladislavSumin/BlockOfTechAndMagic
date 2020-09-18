package ru.vladislav.sumin.blockoftechandmagic.shader

import ru.vladislav.sumin.blockoftechandmagic.markers.IoThread

object ShaderLoader {

    @IoThread
    fun loadShaderCode(shaderName: String): String {
        return javaClass.classLoader
                .getResourceAsStream("shaders/$shaderName.glsl")!!
                .reader()
                .use {
                    it.readText()
                }
    }

}