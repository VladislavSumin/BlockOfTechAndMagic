package ru.vladislavsumin.core.utils

import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.yield
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import ru.vladislavsumin.core.utils.BufferUtils
import java.nio.ByteBuffer
import java.nio.file.Path
import java.nio.file.Paths

@RunWith(JUnit4::class)
class BufferUtilsTest {
    companion object {
        private val IMAGE16 = Paths.get("../testFiles/image16.png")
    }

    @Test
    fun `load file not direct buffer`() {
        `load file`(false)
    }

    @Test
    fun `load file direct buffer`() {
        `load file`(true)
    }

    @Test
    fun `cancel load file not direct buffer`() {
        `cancel load file`(false)
    }

    @Test
    fun `cancel load file direct buffer`() {
        `cancel load file`(true)
    }

    private fun `cancel load file`(isDirect: Boolean) = runBlocking {
        val async = async() { BufferUtils.loadFile(IMAGE16, isDirect) }
        yield()
        async.cancel()
    }

    private fun `load file`(isDirect: Boolean) = runBlocking {
        val expected = loadClassicWay(IMAGE16)
        val actual = BufferUtils.loadFile(IMAGE16, isDirect)
        assertEquals(0, actual.position())
        assertEquals(isDirect, actual.isDirect)
        assertEquals(expected.limit(), actual.limit())
        while (actual.hasRemaining()) {
            assertEquals(expected.get(), actual.get())
        }
    }

    private fun loadClassicWay(path: Path): ByteBuffer {
        return ByteBuffer.wrap(path.toFile().readBytes())
    }
}