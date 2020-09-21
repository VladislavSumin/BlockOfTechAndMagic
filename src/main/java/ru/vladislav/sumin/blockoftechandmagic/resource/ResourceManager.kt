package ru.vladislav.sumin.blockoftechandmagic.resource

import ru.vladislavsumin.opengl.markers.IoThread
import ru.vladislav.sumin.blockoftechandmagic.resource.exceptions.ResourceNotFoundException
import java.io.InputStream

interface ResourceManager {
    @IoThread
    @Throws(ResourceNotFoundException::class)
    fun getResourceAsStream(path: String): InputStream
}