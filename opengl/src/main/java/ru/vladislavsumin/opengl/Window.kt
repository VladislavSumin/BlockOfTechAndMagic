package ru.vladislavsumin.opengl

import org.lwjgl.glfw.Callbacks
import org.lwjgl.glfw.GLFW.*
import org.lwjgl.system.MemoryStack
import org.lwjgl.system.MemoryUtil.*
import ru.vladislavsumin.core.utils.use
import ru.vladislavsumin.opengl.markers.MainThread

abstract class Window {
    var window: Long = 0
        private set

    open fun create(w: Int, h: Int, title: String) {
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE)
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE)

        window = glfwCreateWindow(w, h, title, NULL, NULL)
        if (window == NULL) throw RuntimeException("Failed to create the GLFW window")

        // Make the OpenGL context current
        glfwMakeContextCurrent(window)
    }

    fun show() {
        glfwShowWindow(window)
    }

    fun moveWindowToCenterOfScreen() {
        MemoryStack.stackPush().use { stack ->
            val pWidth = stack.mallocInt(1) // int*
            val pHeight = stack.mallocInt(1) // int*

            // Get the window size passed to glfwCreateWindow
            glfwGetWindowSize(window, pWidth, pHeight)

            // Get the resolution of the primary monitor
            val videoMode = glfwGetVideoMode(glfwGetPrimaryMonitor())

            // Center the window
            glfwSetWindowPos(
                window,
                (videoMode!!.width() - pWidth[0]) / 2,
                (videoMode.height() - pHeight[0]) / 2
            )
        }
    }

    fun setVSync(enabled: Boolean) {
        glfwSwapInterval(if (enabled) 1 else 0)
    }

    fun setNeedClose() {
        glfwSetWindowShouldClose(window, true)
    }

    fun destroy() {
        Callbacks.glfwFreeCallbacks(window)
        glfwDestroyWindow(window)
    }
}