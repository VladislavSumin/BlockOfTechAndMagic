package ru.vladislavsumin.blockoftechandmagic.client

import kotlinx.coroutines.runBlocking
import org.lwjgl.Version
import org.lwjgl.glfw.GLFW.*
import org.lwjgl.glfw.GLFWWindowCloseCallbackI
import org.lwjgl.opengl.GL
import org.lwjgl.opengl.GL33.*
import ru.vladislavsumin.blockoftechandmagic.client.event.EventManager
import ru.vladislavsumin.blockoftechandmagic.client.performance.PerformanceManager
import ru.vladislavsumin.blockoftechandmagic.client.render.GuiRender
import ru.vladislavsumin.blockoftechandmagic.client.render.WorldRender
import ru.vladislavsumin.blockoftechandmagic.client.state.GameStateManager
import ru.vladislavsumin.blockoftechandmagic.client.window.GameWindow
import ru.vladislavsumin.blockoftechandmagic.log.LogTags
import ru.vladislavsumin.blockoftechandmagic.log.logger
import ru.vladislavsumin.blockoftechandmagic.resource.ResourceManager
import ru.vladislavsumin.opengl.markers.MainThread
import ru.vladislavsumin.opengl.utils.GlfwUtils
import sun.misc.GC
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.concurrent.thread


@Singleton
class Game @Inject constructor(
    private val gameWindow: GameWindow,
    private val eventManager: EventManager,
    private val worldRender: WorldRender,
    private val guiRender: GuiRender,
    private val resourceManager: ResourceManager,
    private val gameStateManager: GameStateManager,
    private val performanceManager: PerformanceManager,
) {
    companion object {
        private const val WIDTH = 800
        private const val HEIGHT = 600
        private val log = logger(LogTags.GAME)
    }

    private lateinit var mainThread: Thread

    @MainThread
    fun run() {
        mainThread = Thread.currentThread()
        mainThread.priority = Thread.MAX_PRIORITY

        log.info("Start loading game")
        addShowdownHook()

        resourceManager.init()

        val props = runBlocking {
            resourceManager.getConfiguration("settings")
        }

        log.info("LWJGL version: ${Version.getVersion()}")
        GlfwUtils.initGlfw()
        setupGlfwWindow()
        setupOpenGl()
        worldRender.init()
        guiRender.init()

        loop()

        guiRender.destroy()
        worldRender.destroy()
        gameWindow.destroy()
        GlfwUtils.terminateGlfw()
        resourceManager.destroy()
    }

    private fun addShowdownHook() {
        Runtime.getRuntime().addShutdownHook(thread(start = false, name = "shutdown") {
            log.info("Shutdown hook called")
            gameStateManager.setCloseSignalReceived()
            mainThread.join()
        })
    }

    private fun setupOpenGl() {
        GL.createCapabilities()
        log.info("OpenGL version: ${glGetString(GL_VERSION)}")

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

        var lastFrameTime = System.nanoTime()
        while (!gameStateManager.isCloseSignalReceived) {
            val currentFrameTime = System.nanoTime()
            val deltaTime = currentFrameTime - lastFrameTime
            val deltaTimeF = deltaTime / 1_000_000_000.0
            lastFrameTime = currentFrameTime

            // Handle event section
            glfwPollEvents()
            eventManager.calculateEvents(deltaTimeF)

            // Draw section
            glClearColor(0.2f, 0.3f, 0.3f, 1.0f)
            glClear(GL_COLOR_BUFFER_BIT or GL_DEPTH_BUFFER_BIT)
            worldRender.draw()
            guiRender.draw()

            performanceManager.commitFrameTime(System.nanoTime() - currentFrameTime)

            glfwSwapBuffers(gameWindow.window)
        }
    }
}