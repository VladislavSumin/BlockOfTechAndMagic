package ru.vladislav.sumin.blockoftechandmagic.di

import dagger.Binds
import dagger.Module
import ru.vladislav.sumin.blockoftechandmagic.userinput.UserInputKeyCallBack
import ru.vladislav.sumin.blockoftechandmagic.userinput.UserInputManagerImpl

@Module
abstract class UserInputModule {
    @Binds
    abstract fun bind(userInputManager: UserInputManagerImpl): UserInputKeyCallBack
}