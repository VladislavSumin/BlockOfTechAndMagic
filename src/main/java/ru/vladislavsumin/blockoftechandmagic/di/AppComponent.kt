package ru.vladislavsumin.blockoftechandmagic.di

import dagger.Component
import ru.vladislavsumin.blockoftechandmagic.Starter
import javax.inject.Singleton

@Singleton
@Component(modules = [
    ResourceModule::class,
    ClientModule::class
])
interface AppComponent {
    fun inject(starter: Starter)
}