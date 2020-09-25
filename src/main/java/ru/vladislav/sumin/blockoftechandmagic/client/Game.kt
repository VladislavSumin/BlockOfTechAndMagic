package ru.vladislav.sumin.blockoftechandmagic.client

import org.lwjgl.Version
import org.lwjgl.glfw.Callbacks
import org.lwjgl.glfw.GLFW.*
import org.lwjgl.opengl.GL
import org.lwjgl.opengl.GL33.*
import org.lwjgl.system.MemoryStack
import org.lwjgl.system.MemoryUtil.*
import ru.vladislav.sumin.blockoftechandmagic.TestTriangles
import ru.vladislav.sumin.blockoftechandmagic.client.camera.PlayerCamera
import ru.vladislav.sumin.blockoftechandmagic.client.userinput.UserInputCursorCallback
import ru.vladislavsumin.opengl.markers.MainThread
import ru.vladislav.sumin.blockoftechandmagic.client.userinput.UserInputKeyCallBack
import ru.vladislav.sumin.blockoftechandmagic.client.userinput.UserInputManager
import ru.vladislavsumin.core.utils.use
import ru.vladislavsumin.opengl.utils.GlfwUtils
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class Game @Inject constructor(
    private val userInputKeyCallBack: UserInputKeyCallBack,
    private val userInputCursorCallback: UserInputCursorCallback,
    private val userInputManager: UserInputManager,
    private val triangles: TestTriangles,
    private val playerCamera: PlayerCamera
) {
    companion object {
        private const val WIDTH = 800
        private const val HEIGHT = 600
    }

    private var window: Long = 0

    @MainThread
    fun run() {
        println("Hello LWJGL " + Version.getVersion() + "!")
        GlfwUtils.initGlfw()
        setupGlfwWindow()
        setupOpenGl()
        loop()

        // Free the window callbacks and destroy the window
        Callbacks.glfwFreeCallbacks(window)
        glfwDestroyWindow(window)

        GlfwUtils.terminateGlfw()
    }

    private fun setupOpenGl() {
        GL.createCapabilities()
        println("OpenGL version: ${glGetString(GL_VERSION)}")
        glEnable(GL_DEPTH_TEST);
//        glViewport(0, 0, WIDTH, HEIGHT)
    }

    @MainThread
    private fun setupGlfwWindow() {
        // Configure GLFW
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE)
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE)

        // Create the window
        window = glfwCreateWindow(WIDTH, HEIGHT, "Block ot tech and magic", NULL, NULL)
        if (window == NULL) throw RuntimeException("Failed to create the GLFW window")

        glfwSetInputMode(window, GLFW_CURSOR, GLFW_CURSOR_DISABLED)

        glfwSetKeyCallback(window, userInputKeyCallBack)

        MemoryStack.stackPush().use {
            val x = it.mallocDouble(1)
            val y = it.mallocDouble(1)
            glfwGetCursorPos(window, x, y)
            userInputCursorCallback.setInitialCursorPosition(x[0], y[0])
        }

        glfwSetCursorPosCallback(window, userInputCursorCallback)


        MemoryStack.stackPush().use { stack ->
            val pWidth = stack.mallocInt(1) // int*
            val pHeight = stack.mallocInt(1) // int*

            // Get the window size passed to glfwCreateWindow
            glfwGetWindowSize(window, pWidth, pHeight)

            // Get the resolution of the primary monitor
            val vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor())

            // Center the window
            glfwSetWindowPos(
                window,
                (vidmode!!.width() - pWidth[0]) / 2,
                (vidmode.height() - pHeight[0]) / 2
            )
        }

        glfwSetWindowSizeCallback(window) { window: Long, width: Int, height: Int ->
            println("Window shanged w=$width, h=$height")
//            glViewport(0, 0, width*2, height*2)
        }

        // Make the OpenGL context current
        glfwMakeContextCurrent(window)
        // Enable v-sync
        glfwSwapInterval(1)

        // Make the window visible
        glfwShowWindow(window)
    }

    @MainThread
    private fun loop() {
        //TODO add window resize callback

        triangles.init()

        var lastFrameTime = glfwGetTime()
        while (!glfwWindowShouldClose(window)) {
            val currentFrameTime = glfwGetTime()
            val deltaTime = currentFrameTime - lastFrameTime
            lastFrameTime = currentFrameTime

            glfwPollEvents()
            userInputManager.calculateUserInput()
            playerCamera.updatePosition(deltaTime)

            // Draw section
            glClearColor(0.2f, 0.3f, 0.3f, 1.0f)
            glClear(GL_COLOR_BUFFER_BIT or GL_DEPTH_BUFFER_BIT)
            triangles.draw()

            glfwSwapBuffers(window)
        }
    }

    @MainThread
    fun setNeedClose() {
        glfwSetWindowShouldClose(window, true)
    }
}