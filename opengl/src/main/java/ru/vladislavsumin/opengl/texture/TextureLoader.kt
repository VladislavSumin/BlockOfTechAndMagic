package ru.vladislavsumin.opengl.texture

import org.lwjgl.stb.STBImage.*
import org.lwjgl.system.MemoryStack.stackPush
import ru.vladislavsumin.core.utils.use
import java.io.IOException
import java.nio.ByteBuffer


object TextureLoader {
    fun loadTextureFromDirectBuffer(buffer: ByteBuffer): Texture {
        return decode(buffer).use {
            generateTexture(it)
        }
    }

    private fun generateTexture(decodedImage: DecodedImage): Texture {
        return Texture(decodedImage)
    }

    private fun decode(imageBuffer: ByteBuffer): DecodedImage {
        if (!imageBuffer.isDirect) throw IOException("Buffer must be direct")
        stackPush().use { stack ->
            val w = stack.mallocInt(1)
            val h = stack.mallocInt(1)
            val comp = stack.mallocInt(1)

            if (!stbi_info_from_memory(imageBuffer, w, h, comp)) {
                throw IOException("Failed to read image information: " + stbi_failure_reason())
            }
            val isHdr = stbi_is_hdr_from_memory(imageBuffer)

            val decodedBuffer = stbi_load_from_memory(imageBuffer, w, h, comp, 0)
                ?: throw RuntimeException("Failed to load image: " + stbi_failure_reason())


            val colorFormat = when (comp[0]) {
                3 -> ColorFormat.RGB
                4 -> ColorFormat.RGBA
                else -> throw Exception("panic")
            }

            return DecodedImage(
                w[0],
                h[0],
                colorFormat,
                isHdr,
                decodedBuffer
            )
        }
    }
}