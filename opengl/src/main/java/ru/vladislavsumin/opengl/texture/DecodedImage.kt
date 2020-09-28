package ru.vladislavsumin.opengl.texture

import org.lwjgl.stb.STBImage.*
import java.io.Closeable
import java.nio.ByteBuffer

internal class DecodedImage(
    val w: Int,
    val h: Int,
    val colorFormat: ColorFormat,
    val isHdr: Boolean,
    val buffer: ByteBuffer
) : Closeable {
    override fun close() {
        buffer.position(0)
        stbi_image_free(buffer)
    }
}