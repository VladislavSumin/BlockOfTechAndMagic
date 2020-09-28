package ru.vladislavsumin.blockoftechandmagic.client.window

import org.lwjgl.glfw.GLFW.*
import org.lwjgl.opengl.GL33.*
import org.lwjgl.system.MemoryStack
import ru.vladislavsumin.blockoftechandmagic.client.state.GameStateManager
import ru.vladislavsumin.blockoftechandmagic.client.userinput.UserInputCursorCallback
import ru.vladislavsumin.blockoftechandmagic.client.userinput.UserInputKeyCallBack
import ru.vladislavsumin.blockoftechandmagic.log.LogTags
import ru.vladislavsumin.blockoftechandmagic.log.logger
import ru.vladislavsumin.core.utils.use
import ru.vladislavsumin.opengl.Window
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GameWindow @Inject constructor(
    private val userInputKeyCallBack: UserInputKeyCallBack,
    private val userInputCursorCallback: UserInputCursorCallback,
    private val gameStateManager: GameStateManager
) : Window() {
    companion object {
        private val log = logger(LogTags.GAME)
    }

    override fun create(w: Int, h: Int, title: String) {
        super.create(w, h, title)

        // Setup keyboard input
        glfwSetKeyCallback(window, userInputKeyCallBack)

        // Setup cursor input
        glfwSetInputMode(window, GLFW_CURSOR, GLFW_CURSOR_DISABLED)
        MemoryStack.stackPush().use {
            val x = it.mallocDouble(1)
            val y = it.mallocDouble(1)
            glfwGetCursorPos(window, x, y)
            userInputCursorCallback.setInitialCursorPosition(x[0], y[0])
        }
        glfwSetCursorPosCallback(window, userInputCursorCallback)

        // Setup close window callback
        glfwSetWindowCloseCallback(window) {
            log.info("Window close event received")
            gameStateManager.setCloseSignalReceived()
        }

        // Setup window size callback
        gameStateManager.setWindowResolution(w, h)
        glfwSetWindowSizeCallback(window) { _, w1, h1 ->
            glViewport(0, 0, w1, h1)
            gameStateManager.setWindowResolution(w1, h1)
        }
    }
}