package ru.vladislav.sumin.blockoftechandmagic.client.userinput

interface UserInputManager {
    val pressedKeys: BooleanArray
    val deltaX: Double
    val deltaY: Double

    fun calculateUserInput()
}