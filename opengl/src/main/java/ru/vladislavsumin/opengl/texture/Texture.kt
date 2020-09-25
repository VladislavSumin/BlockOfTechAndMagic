package ru.vladislavsumin.opengl.texture

import org.lwjgl.opengl.GL33.*
import ru.vladislavsumin.opengl.OpenGlResource
import ru.vladislavsumin.opengl.markers.MainThread


class Texture internal constructor(
    image: Image
) : OpenGlResource(glGenTextures()) {

    init {
        glBindTexture(GL_TEXTURE_2D, id)
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST_MIPMAP_NEAREST)
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST)
        glTexImage2D(
            GL_TEXTURE_2D,
            0,
            GL_RGB,
            image.w,
            image.h,
            0,
            image.colorFormat.openGlFormat,
            GL_UNSIGNED_BYTE,
            image.buffer
        )
        glGenerateMipmap(GL_TEXTURE_2D)
        glBindTexture(GL_TEXTURE_2D, 0)
    }

    fun bindTexture() {
        glBindTexture(GL_TEXTURE_2D, id)
    }

    @MainThread
    override fun close() {
        super.close()
        glDeleteTextures(id)
    }
}