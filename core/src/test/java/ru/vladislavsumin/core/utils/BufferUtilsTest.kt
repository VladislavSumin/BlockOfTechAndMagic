package ru.vladislavsumin.core.utils

import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
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

    private fun `load file`(isDirect: Boolean) {
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