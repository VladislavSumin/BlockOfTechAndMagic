package ru.vladislavsumin.blockoftechandmagic.client.texture

import ru.vladislavsumin.opengl.texture.Texture

interface TextureManager {
    fun loadTexture(name: String): Texture
}