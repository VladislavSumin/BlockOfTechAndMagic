package ru.vladislavsumin.opengl.utils

inline fun <T : AutoCloseable, R> T.use(block: (T) -> R): R {
    try {
        return block(this);
    } finally {
        this.close()
    }
}