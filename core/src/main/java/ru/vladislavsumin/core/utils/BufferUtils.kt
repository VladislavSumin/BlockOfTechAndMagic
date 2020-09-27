package ru.vladislavsumin.core.utils

import java.io.IOException
import java.nio.ByteBuffer
import java.nio.channels.FileChannel
import java.nio.file.Path
import java.nio.file.StandardOpenOption

object BufferUtils {
    fun loadFile(path: Path, isDirect: Boolean = false): ByteBuffer {
        val channel = FileChannel.open(path, StandardOpenOption.READ)
        val fileSize = channel.size().toInt()
        val buffer = if (isDirect) ByteBuffer.allocateDirect(fileSize) else ByteBuffer.allocate(fileSize)
        val readSize = channel.read(buffer)
        if (fileSize != readSize) throw IOException("File size $fileSize, but read only $readSize. Path $path")
        buffer.position(0)
        return buffer
    }
}