package ru.vladislavsumin.opengl.texture

import java.nio.ByteBuffer

internal class Image(
    val w: Int,
    val h: Int,
    val colorFormat: ColorFormat,
    val idHdr: Boolean,
    val buffer: ByteBuffer
)