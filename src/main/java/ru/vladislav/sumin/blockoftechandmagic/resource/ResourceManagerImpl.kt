package ru.vladislav.sumin.blockoftechandmagic.resource

import ru.vladislav.sumin.blockoftechandmagic.resource.exceptions.ResourceNotFoundException
import java.io.InputStream
import java.nio.file.Path
import java.nio.file.Paths
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ResourceManagerImpl @Inject constructor() : ResourceManager {
    override fun getResourceAsStream(path: String): InputStream {
        return javaClass.classLoader.getResourceAsStream(path)
            ?: throw ResourceNotFoundException("Resource $path not found")
    }

    override fun getResourceAsPath(path: String): Path {
        val resource = javaClass.classLoader.getResource(path)
            ?: throw ResourceNotFoundException("Resource $path not found")
        return Paths.get(resource.toURI())
    }
}