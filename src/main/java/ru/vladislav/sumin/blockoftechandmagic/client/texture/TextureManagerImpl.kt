package ru.vladislav.sumin.blockoftechandmagic.client.texture

import ru.vladislav.sumin.blockoftechandmagic.resource.ResourceManager
import ru.vladislavsumin.opengl.texture.Texture
import java.awt.image.BufferedImage
import java.io.InputStream
import javax.imageio.ImageIO
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TextureManagerImpl @Inject constructor(
    private val resourceManager: ResourceManager
) : TextureManager {

    init {
        //Tmp workaround //TODO fix that
        ImageIO.getCacheDirectory()
    }

    override fun loadTexture(name: String): Texture {
        val stream = resourceManager.getResourceAsStream("$name.png")
        val image = loadImage(stream)
        return Texture(image)
    }

    private fun loadImage(input: InputStream): BufferedImage {
        val imageStream = ImageIO.createImageInputStream(input)
        return ImageIO.read(imageStream)
    }
}