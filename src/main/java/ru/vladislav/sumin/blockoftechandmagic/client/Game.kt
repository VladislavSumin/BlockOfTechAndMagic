package ru.vladislav.sumin.blockoftechandmagic.client

import kotlinx.coroutines.runBlocking
import org.lwjgl.Version
import org.lwjgl.glfw.GLFW.*
import org.lwjgl.opengl.GL
import org.lwjgl.opengl.GL33.*
import ru.vladislav.sumin.blockoftechandmagic.client.event.EventManager
import ru.vladislav.sumin.blockoftechandmagic.client.render.WorldRender
import ru.vladislav.sumin.blockoftechandmagic.client.window.GameWindow
import ru.vladislav.sumin.blockoftechandmagic.resource.ResourceManager
import ru.vladislavsumin.opengl.markers.MainThread
import ru.vladislavsumin.opengl.utils.GlfwUtils
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class Game @Inject constructor(
    private val gameWindow: GameWindow,
    private val eventManager: EventManager,
    private val worldRender: WorldRender,
    private val resourceManager: ResourceManager
) {
    companion object {
        private const val WIDTH = 800
        private const val HEIGHT = 600
    }

    @MainThread
    fun run() {
        val props = runBlocking {
            resourceManager.getConfiguration("settings")
        }

        println("Hello LWJGL " + Version.getVersion() + "!")
        GlfwUtils.initGlfw()
        setupGlfwWindow()
        setupOpenGl()
        worldRender.init()

        loop()

        worldRender.destroy()
        gameWindow.destroy()
        GlfwUtils.terminateGlfw()
    }

    private fun setupOpenGl() {
        GL.createCapabilities()
        println("OpenGL version: ${glGetString(GL_VERSION)}")

        glEnable(GL_DEPTH_TEST)

        glEnable(GL_CULL_FACE)
        glFrontFace(GL_CCW)
//        glViewport(0, 0, WIDTH, HEIGHT)
    }

    @MainThread
    private fun setupGlfwWindow() {
        gameWindow.create(WIDTH, HEIGHT, "Block ot tech and magic")
        gameWindow.moveWindowToCenterOfScreen()
        gameWindow.setVSync(true)

//        glfwSetWindowSizeCallback(window) { window: Long, width: Int, height: Int ->
//            println("Window changed w=$width, h=$height")
//            glViewport(0, 0, width*2, height*2)
//        }

        gameWindow.show()
    }

    @MainThread
    private fun loop() {
        //TODO add window resize callback

        var lastFrameTime = glfwGetTime()
        while (!glfwWindowShouldClose(gameWindow.window)) {
            val currentFrameTime = glfwGetTime()
            val deltaTime = currentFrameTime - lastFrameTime
            lastFrameTime = currentFrameTime

            // Handle event section
            glfwPollEvents()
            eventManager.calculateEvents(deltaTime)

            // Draw section
            glClearColor(0.2f, 0.3f, 0.3f, 1.0f)
            glClear(GL_COLOR_BUFFER_BIT or GL_DEPTH_BUFFER_BIT)
            worldRender.draw()

            glfwSwapBuffers(gameWindow.window)
        }
    }
}