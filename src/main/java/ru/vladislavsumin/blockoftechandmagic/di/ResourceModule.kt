package ru.vladislavsumin.blockoftechandmagic.di

import dagger.Binds
import dagger.Module
import ru.vladislavsumin.blockoftechandmagic.resource.ResourceManager
import ru.vladislavsumin.blockoftechandmagic.resource.ResourceManagerImpl
import kotlin.time.ExperimentalTime

@Module
abstract class ResourceModule {
    @ExperimentalTime
    @Binds
    abstract fun bind(resourceManagerImpl: ResourceManagerImpl): ResourceManager
}