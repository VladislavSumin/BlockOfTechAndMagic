package ru.vladislav.sumin.blockoftechandmagic.resource

import ru.vladislavsumin.opengl.markers.IoThread
import ru.vladislav.sumin.blockoftechandmagic.resource.exceptions.ResourceNotFoundException
import java.io.InputStream
import java.nio.file.Path

interface ResourceManager {
    @IoThread
    @Throws(ResourceNotFoundException::class)
    fun getResourceAsStream(path: String): InputStream

    fun getResourceAsPath(path: String):Path
}