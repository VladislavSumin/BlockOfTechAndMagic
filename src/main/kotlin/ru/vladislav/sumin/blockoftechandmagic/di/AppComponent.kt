package ru.vladislav.sumin.blockoftechandmagic.di

import dagger.Component
import ru.vladislav.sumin.blockoftechandmagic.Starter
import javax.inject.Singleton

@Component
@Singleton
interface AppComponent {
    fun inject(starter: Starter)
}