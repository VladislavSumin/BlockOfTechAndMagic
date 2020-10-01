package ru.vladislavsumin.blockoftechandmagic.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import ru.vladislavsumin.blockoftechandmagic.client.shader.ShaderManager
import ru.vladislavsumin.blockoftechandmagic.client.shader.ShaderManagerImpl
import ru.vladislavsumin.blockoftechandmagic.client.texture.TextureManager
import ru.vladislavsumin.blockoftechandmagic.client.texture.TextureManagerImpl
import ru.vladislavsumin.blockoftechandmagic.client.userinput.UserInputCursorCallback
import ru.vladislavsumin.blockoftechandmagic.client.userinput.UserInputKeyCallBack
import ru.vladislavsumin.blockoftechandmagic.client.userinput.UserInputManager
import ru.vladislavsumin.blockoftechandmagic.client.userinput.UserInputManagerImpl
import ru.vladislavsumin.opengl.OpenGlSateManager

@Module
abstract class ClientModule {
    @Binds
    abstract fun bindShaderManager(manager: ShaderManagerImpl): ShaderManager

    @Binds
    abstract fun bindTextureManager(manager: TextureManagerImpl): TextureManager

    @Binds
    abstract fun bindUserInputManager(userInputManager: UserInputManagerImpl): UserInputManager

    companion object {
        @Provides
        @JvmStatic
        fun provideUserInputKeyCallBack(userInputManager: UserInputManagerImpl): UserInputKeyCallBack {
            return userInputManager.keyListener
        }

        @Provides
        @JvmStatic
        fun provideUserInputCursorCallBack(userInputManager: UserInputManagerImpl): UserInputCursorCallback {
            return userInputManager.cursorListener
        }

        @Provides
        @JvmStatic
        fun provideGlStateManager(): OpenGlSateManager {
            return OpenGlSateManager()
        }
    }
}