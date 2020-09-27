package ru.vladislavsumin.blockoftechandmagic.di

import dagger.Binds
import dagger.Module
import ru.vladislavsumin.blockoftechandmagic.resource.ResourceManager
import ru.vladislavsumin.blockoftechandmagic.resource.ResourceManagerImpl

@Module
abstract class ResourceModule {
    @Binds
    abstract fun bind(resourceManagerImpl: ResourceManagerImpl): ResourceManager
}