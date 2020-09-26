package ru.vladislav.sumin.blockoftechandmagic.client.window

import org.lwjgl.glfw.GLFW.*
import org.lwjgl.system.MemoryStack
import ru.vladislav.sumin.blockoftechandmagic.client.userinput.UserInputCursorCallback
import ru.vladislav.sumin.blockoftechandmagic.client.userinput.UserInputKeyCallBack
import ru.vladislavsumin.core.utils.use
import ru.vladislavsumin.opengl.Window
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GameWindow @Inject constructor(
    private val userInputKeyCallBack: UserInputKeyCallBack,
    private val userInputCursorCallback: UserInputCursorCallback
) : Window() {

    override fun create(w: Int, h: Int, title: String) {
        super.create(w, h, title)

        //Setup keyboard input
        glfwSetKeyCallback(window, userInputKeyCallBack)

        //Setup cursor input
        glfwSetInputMode(window, GLFW_CURSOR, GLFW_CURSOR_DISABLED)
        MemoryStack.stackPush().use {
            val x = it.mallocDouble(1)
            val y = it.mallocDouble(1)
            glfwGetCursorPos(window, x, y)
            userInputCursorCallback.setInitialCursorPosition(x[0], y[0])
        }
        glfwSetCursorPosCallback(window, userInputCursorCallback)
    }
}