package ru.vladislav.sumin.blockoftechandmagic.client.texture

import kotlinx.coroutines.runBlocking
import ru.vladislav.sumin.blockoftechandmagic.resource.ResourceManager
import ru.vladislavsumin.opengl.texture.TextureLoader
import ru.vladislavsumin.opengl.texture.Texture
import ru.vladislavsumin.opengl.utils.BufferUtils
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