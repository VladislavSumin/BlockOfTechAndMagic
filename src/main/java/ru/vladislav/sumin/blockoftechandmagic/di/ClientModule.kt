package ru.vladislav.sumin.blockoftechandmagic.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import ru.vladislav.sumin.blockoftechandmagic.client.shader.ShaderManager
import ru.vladislav.sumin.blockoftechandmagic.client.shader.ShaderManagerImpl
import ru.vladislav.sumin.blockoftechandmagic.client.texture.TextureManager
import ru.vladislav.sumin.blockoftechandmagic.client.texture.TextureManagerImpl
import ru.vladislav.sumin.blockoftechandmagic.client.userinput.UserInputCursorCallback
import ru.vladislav.sumin.blockoftechandmagic.client.userinput.UserInputKeyCallBack
import ru.vladislav.sumin.blockoftechandmagic.client.userinput.UserInputManager
import ru.vladislav.sumin.blockoftechandmagic.client.userinput.UserInputManagerImpl
import javax.inject.Singleton

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
    }
}