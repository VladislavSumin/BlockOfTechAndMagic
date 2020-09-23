package ru.vladislavsumin.core.io

import java.nio.ByteBuffer
import java.nio.file.Path

interface IOHelper {
    suspend fun loadResource(path: Path): ByteBuffer
}