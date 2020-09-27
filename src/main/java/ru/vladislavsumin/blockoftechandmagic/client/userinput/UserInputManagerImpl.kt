package ru.vladislavsumin.blockoftechandmagic.client.userinput

import org.lwjgl.glfw.GLFW.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserInputManagerImpl @Inject constructor() : UserInputManager {
    override val pressedKeys: BooleanArray = BooleanArray(1024)

    override var deltaX = 0.0
    override var deltaY = 0.0

    val keyListener = UserInputKeyCallbackListener()
    val cursorListener = UserInputInputCursorCallbackListener()

    override fun calculateUserInput() {
        cursorListener.calculate()
    }

    inner class UserInputKeyCallbackListener : UserInputKeyCallBack {
        override fun invoke(window: Long, key: Int, scancode: Int, action: Int, mods: Int) {
            when (action) {
                GLFW_PRESS -> pressedKeys[key] = true
                GLFW_RELEASE -> pressedKeys[key] = false
            }
        }
    }

    inner class UserInputInputCursorCallbackListener : UserInputCursorCallback {
        private var lastX = 0.0
        private var lastY = 0.0

        private var x = 0.0
        private var y = 0.0

        override fun setInitialCursorPosition(x: Double, y: Double) {
            this.x = x
            this.y = y
        }

        override fun invoke(window: Long, xpos: Double, ypos: Double) {
            x = xpos
            y = ypos
        }

        fun calculate() {
            deltaX = x - lastX
            deltaY = lastY - y //reverse
            lastX = x
            lastY = y
        }
    }
}