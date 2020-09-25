package ru.vladislavsumin.opengl.utils

import org.lwjgl.glfw.GLFW.*
import org.lwjgl.glfw.GLFWErrorCallback
import org.lwjgl.opengl.GL33

object GlfwUtils {
    fun initGlfw() {
        setupGlfwLogs()
        check(glfwInit()) { "Unable to initialize GLFW" }
        setupGlfw()
    }

    fun terminateGlfw() {
        glfwTerminate()
        glfwSetErrorCallback(null)!!.free()
    }

    private fun setupGlfw() {
        glfwDefaultWindowHints() // optional, the current window hints are already the default
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3)
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3)
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE)
        glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GL33.GL_TRUE)
    }

    private fun setupGlfwLogs() {
        // Setup an error callback. The default implementation
        // will print the error message in System.err.
        //TODO change logs output
        GLFWErrorCallback.createPrint(System.err).set()
    }
}