package ru.vladislavsumin.opengl.texture

import org.lwjgl.opengl.GL33.*
import ru.vladislavsumin.opengl.OpenGlResource
import ru.vladislavsumin.opengl.markers.MainThread
import java.awt.image.BufferedImage


class Texture(
    image: BufferedImage
) : OpenGlResource(glGenTextures()) {

    init {
        glBindTexture(GL_TEXTURE_2D, id)
        val data = IntArray(image.width*image.height)
        image.getRGB(0, 0, image.width, image.height, data, 0, image.width)
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR_MIPMAP_NEAREST)
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST)
        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGB, image.width, image.height, 0, GL_BGRA, GL_UNSIGNED_INT_8_8_8_8_REV, data)
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