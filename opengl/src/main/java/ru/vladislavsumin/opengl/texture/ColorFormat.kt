package ru.vladislavsumin.opengl.texture

import org.lwjgl.opengl.GL33.*

enum class ColorFormat(val openGlFormat: Int) {
    RGB(GL_RGB),
    RGBA(GL_RGBA)
}