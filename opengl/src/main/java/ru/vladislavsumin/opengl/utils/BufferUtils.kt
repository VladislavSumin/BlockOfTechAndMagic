package ru.vladislavsumin.opengl.utils

import kotlinx.coroutines.CancellableContinuation
import kotlinx.coroutines.suspendCancellableCoroutine
import java.io.IOException
import java.nio.ByteBuffer
import java.nio.channels.AsynchronousCloseException
import java.nio.channels.AsynchronousFileChannel
import java.nio.channels.CompletionHandler
import java.nio.file.Path
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

object BufferUtils {
    private val handler = Handler()

    suspend fun loadFile(path: Path, isDirect: Boolean = false): ByteBuffer {
        return AsynchronousFileChannel.open(path).use { channel ->
            val fileSize = channel.size().toInt()
            val buffer = if (isDirect) ByteBuffer.allocateDirect(fileSize) else ByteBuffer.allocate(fileSize)
            val readSize = suspendCancellableCoroutine<Int> {
                it.invokeOnCancellation { channel.close() }
                channel.read(buffer, 0, it, handler)
            }
            if (fileSize != readSize) throw IOException("File size $fileSize, but read only $readSize. Path $path")
            buffer.position(0)
            buffer
        }
    }

    private class Handler : CompletionHandler<Int, CancellableContinuation<Int>> {
        override fun completed(result: Int, cont: CancellableContinuation<Int>) {
            cont.resume(result)
        }

        override fun failed(exc: Throwable, cont: CancellableContinuation<Int>) {
            if (exc is AsynchronousCloseException || cont.isCancelled) return
            cont.resumeWithException(exc)
        }
    }
}