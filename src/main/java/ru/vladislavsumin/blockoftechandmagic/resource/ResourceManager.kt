package ru.vladislavsumin.blockoftechandmagic.resource

import java.io.InputStream
import java.nio.file.Path
import java.util.*

interface ResourceManager {
    fun init()

    fun getConfiguration(name: String): Properties

    fun getResourceAsPath(path: String): Path

    fun destroy()
}