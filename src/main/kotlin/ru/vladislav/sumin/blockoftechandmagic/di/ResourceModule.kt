package ru.vladislav.sumin.blockoftechandmagic.di

import dagger.Binds
import dagger.Module
import ru.vladislav.sumin.blockoftechandmagic.resource.ResourceManager
import ru.vladislav.sumin.blockoftechandmagic.resource.ResourceManagerImpl

@Module
abstract class ResourceModule {
    @Binds
    abstract fun bind(resourceManagerImpl: ResourceManagerImpl): ResourceManager
}