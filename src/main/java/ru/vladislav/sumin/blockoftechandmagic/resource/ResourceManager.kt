package ru.vladislav.sumin.blockoftechandmagic.resource

import java.io.InputStream
import java.nio.file.Path
import java.util.*

interface ResourceManager {
    suspend fun getConfiguration(name: String): Properties

    fun getResourceAsStream(path: String): InputStream

    fun getResourceAsPath(path: String): Path
}