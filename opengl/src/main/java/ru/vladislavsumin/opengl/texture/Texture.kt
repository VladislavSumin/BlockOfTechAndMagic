package ru.vladislavsumin.opengl.texture

import ru.vladislavsumin.opengl.OpenGL.*
import ru.vladislavsumin.opengl.OpenGlResource
import ru.vladislavsumin.opengl.markers.MainThread

class Texture : OpenGlResource(glGenTextures()) {

    @MainThread
    override fun close() {
        super.close()
        glDeleteShader(id)
    }
}