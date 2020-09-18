package ru.vladislav.sumin.blockoftechandmagic.utils

inline fun <T : AutoCloseable, R> T.use(block: (T) -> R): R {
    try {
        return block(this);
    } finally {
        this.close()
    }
}