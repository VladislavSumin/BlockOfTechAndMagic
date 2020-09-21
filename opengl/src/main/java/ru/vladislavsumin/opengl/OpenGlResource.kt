package ru.vladislavsumin.opengl

import ru.vladislavsumin.opengl.markers.CallSuper
import ru.vladislavsumin.opengl.markers.MainThread
import java.io.Closeable

abstract class OpenGlResource(protected val id: Int) : Closeable {
    private var isClosed = false

    @MainThread
    @CallSuper
    override fun close() {
        if (isClosed) throw ResourceAlreadyClosedException("OpenGl resource $this already closed")
        isClosed = true
    }
}
