package ru.vladislav.sumin.blockoftechandmagic.di

import dagger.Binds
import dagger.Module
import ru.vladislav.sumin.blockoftechandmagic.shader.ShaderManager
import ru.vladislav.sumin.blockoftechandmagic.shader.ShaderManagerImpl

@Module
abstract class ShaderModule {
    @Binds
    abstract fun bind(userInputManager: ShaderManagerImpl): ShaderManager
}