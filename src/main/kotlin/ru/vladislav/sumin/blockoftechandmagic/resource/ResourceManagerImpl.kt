package ru.vladislav.sumin.blockoftechandmagic.resource

import ru.vladislav.sumin.blockoftechandmagic.resource.exceptions.ResourceNotFoundException
import java.io.InputStream
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ResourceManagerImpl @Inject constructor() : ResourceManager {
    override fun getResourceAsStream(path: String): InputStream {
        return javaClass.classLoader.getResourceAsStream(path)
                ?: throw ResourceNotFoundException("Resource $path not found")
    }
}