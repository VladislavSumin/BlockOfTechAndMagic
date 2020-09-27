package ru.vladislavsumin.blockoftechandmagic.client.texture

import kotlinx.coroutines.runBlocking
import ru.vladislavsumin.blockoftechandmagic.resource.ResourceManager
import ru.vladislavsumin.opengl.texture.TextureLoader
import ru.vladislavsumin.opengl.texture.Texture
import ru.vladislavsumin.core.utils.BufferUtils
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TextureManagerImpl @Inject constructor(
    private val resourceManager: ResourceManager
) : TextureManager {

    override fun loadTexture(name: String): Texture = runBlocking {
        val path = resourceManager.getResourceAsPath("$name.png")
        val buffer = BufferUtils.loadFile(path, true)
        TextureLoader.loadTextureFromDirectBuffer(buffer)
    }
}