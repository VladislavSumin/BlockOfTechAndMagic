package ru.vladislav.sumin.blockoftechandmagic.resource

import ru.vladislav.sumin.blockoftechandmagic.markers.IoThread
import ru.vladislav.sumin.blockoftechandmagic.resource.exceptions.ResourceNotFoundException
import java.io.InputStream

interface ResourceManager {
    @IoThread
    @Throws(ResourceNotFoundException::class)
    fun getResourceAsStream(path: String): InputStream
}