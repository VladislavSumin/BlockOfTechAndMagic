package ru.vladislavsumin.core.io

import kotlinx.coroutines.*
import java.io.IOException
import java.nio.ByteBuffer
import java.nio.channels.AsynchronousCloseException
import java.nio.channels.AsynchronousFileChannel
import java.nio.channels.CompletionHandler
import java.nio.file.Path
import java.nio.file.StandardOpenOption
import kotlin.coroutines.coroutineContext
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

internal class IOHelperImpl : IOHelper {
    private val handler = Handler()

    override suspend fun loadResource(path: Path): ByteBuffer {
        return AsynchronousFileChannel.open(path, StandardOpenOption.READ).use { channel ->
            val size = channel.size().toInt()
            val dst = ByteBuffer.allocateDirect(size)
            val read = suspendCancellableCoroutine<Int> {
                channel.closeOnCancel(it)
                channel.read(dst, 0, it, handler)
            }
            if (read != size) throw IOException("File size $size, but read only $read")
            dst.flip()
            dst
        }
    }

    private fun AsynchronousFileChannel.closeOnCancel(cont: CancellableContinuation<*>) {
        cont.invokeOnCancellation {
            try {
                close()
            } catch (e: IOException) {
                //ignore
            }
        }
    }

    private class Handler : CompletionHandler<Int, CancellableContinuation<Int>> {
        override fun completed(result: Int, attachment: CancellableContinuation<Int>) {
            attachment.resume(result)
        }

        override fun failed(exc: Throwable, attachment: CancellableContinuation<Int>) {
            if (exc is AsynchronousCloseException && attachment.isCancelled) return
            attachment.resumeWithException(exc)
        }
    }
}