package ru.vladislavsumin.blockoftechandmagic.client.userinput

interface UserInputManager {
    val pressedKeys: BooleanArray
    val deltaX: Double
    val deltaY: Double

    fun calculateUserInput()
}