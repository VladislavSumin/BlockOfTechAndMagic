package ru.vladislav.sumin.blockoftechandmagic.client.userinput

import org.lwjgl.glfw.GLFWCursorPosCallbackI

interface UserInputCursorCallback : GLFWCursorPosCallbackI {
    fun setInitialCursorPosition(x: Double, y: Double)
}