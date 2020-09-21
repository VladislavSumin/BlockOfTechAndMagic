package ru.vladislavsumin.opengl

import ru.vladislavsumin.opengl.markers.CallSuper
import ru.vladislavsumin.opengl.markers.MainThread
import java.io.Closeable

abstract class OpenGlResource(val id: Int) : Closeable {
    var isClosed = false
        private set

    @MainThread
    @CallSuper
    override fun close() {
        if (isClosed) throw ResourceAlreadyClosedException("OpenGl resource $this already closed")
        isClosed = true
    }
}
