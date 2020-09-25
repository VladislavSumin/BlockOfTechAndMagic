package ru.vladislav.sumin.blockoftechandmagic.client

import dagger.Lazy
import org.lwjgl.Version
import org.lwjgl.glfw.Callbacks
import org.lwjgl.glfw.GLFW.*
import org.lwjgl.glfw.GLFWErrorCallback
import org.lwjgl.opengl.GL
import org.lwjgl.opengl.GL33.*
import org.lwjgl.system.MemoryStack
import org.lwjgl.system.MemoryUtil.*
import ru.vladislav.sumin.blockoftechandmagic.TestTriangles
import ru.vladislav.sumin.blockoftechandmagic.client.camera.PlayerCamera
import ru.vladislavsumin.opengl.markers.MainThread
import ru.vladislav.sumin.blockoftechandmagic.client.userinput.UserInputKeyCallBack
import ru.vladislavsumin.opengl.utils.use
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class Game @Inject constructor(
    private val userInputKeyCallBack: UserInputKeyCallBack,
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
        //TODO Add logging library
        println("Hello LWJGL " + Version.getVersion() + "!")
        setupGlfwLogs()
        setupGlfw()
        setupGlfwWindow()
        setupOpenGl()
        loop()

        // Free the window callbacks and destroy the window
        Callbacks.glfwFreeCallbacks(window)
        glfwDestroyWindow(window)

        // Terminate GLFW and free the error callback
        glfwTerminate()
        glfwSetErrorCallback(null)!!.free()
    }

    @MainThread
    private fun setupGlfw() {
        // Initialize GLFW.
        // Most GLFW functions will not work before doing this.
        check(glfwInit()) { "Unable to initialize GLFW" }

        glfwDefaultWindowHints() // optional, the current window hints are already the default
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3)
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3)
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE)
        glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GL_TRUE)

    }

    private fun setupOpenGl() {
        // This line is critical for LWJGL's interoperation with GLFW's
        // OpenGL context, or any context that is managed externally.
        // LWJGL detects the context that is current in the current thread,
        // creates the GLCapabilities instance and makes the OpenGL
        // bindings available for use.
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

        // Setup a key callback. It will be called every time a key is pressed, repeated or released.
        glfwSetKeyCallback(window, userInputKeyCallBack)
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
        // Set the clear color

        // Run the rendering loop until the user has attempted to close
        // the window or has pressed the ESCAPE key.
        triangles.init()

        while (!glfwWindowShouldClose(window)) {
            // Poll for window events. The key callback above will only be
            // invoked during this call.
            glfwPollEvents()
            playerCamera.updatePosition()

            // Draw section
            glClearColor(0.2f, 0.3f, 0.3f, 1.0f)
            glClear(GL_COLOR_BUFFER_BIT or GL_DEPTH_BUFFER_BIT) // clear the framebuffer
            triangles.draw()
            glfwSwapBuffers(window) // swap the color buffers
        }
    }

    @MainThread
    fun setNeedClose() {
        glfwSetWindowShouldClose(window, true)
    }

    @MainThread
    private fun setupGlfwLogs() {
        // Setup an error callback. The default implementation
        // will print the error message in System.err.
        //TODO change logs output
        GLFWErrorCallback.createPrint(System.err).set()
    }
}