package ru.vladislav.sumin.blockoftechandmagic.di

import dagger.Binds
import dagger.Module
import ru.vladislav.sumin.blockoftechandmagic.client.shader.ShaderManager
import ru.vladislav.sumin.blockoftechandmagic.client.shader.ShaderManagerImpl
import ru.vladislav.sumin.blockoftechandmagic.client.texture.TextureManager
import ru.vladislav.sumin.blockoftechandmagic.client.texture.TextureManagerImpl

@Module
abstract class ClientModule {
    @Binds
    abstract fun bindShaderManager(manager: ShaderManagerImpl): ShaderManager

    @Binds
    abstract fun bindTextureManager(manager: TextureManagerImpl): TextureManager
}