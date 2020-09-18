package ru.vladislav.sumin.blockoftechandmagic.di

import dagger.Component
import ru.vladislav.sumin.blockoftechandmagic.Starter
import javax.inject.Singleton

@Singleton
@Component(modules = [UserInputModule::class])
interface AppComponent {
    fun inject(starter: Starter)
}