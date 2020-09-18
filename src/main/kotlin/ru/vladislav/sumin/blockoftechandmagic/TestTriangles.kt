package ru.vladislav.sumin.blockoftechandmagic

import org.lwjgl.opengl.GL33.*
import ru.vladislav.sumin.blockoftechandmagic.markers.MainThread
import java.lang.RuntimeException

class TestTriangles {
    companion object {
        private val vertexShaderText = """
            #version 330 core
    
            layout (location = 0) in vec3 position;
    
            void main()
            {
                gl_Position = vec4(position.x, position.y, position.z, 1.0);
            }
        """.trimIndent()

        private val fragmentShaderText = """
            #version 330 core

            out vec4 color;

            void main()
            {
            	color = vec4(0.0f, 1.0f, 0.0f, 1.0f);
            }
        """.trimIndent()
    }

    private var vao = 0
    private var vbo = 0
    private var program = 0

    @MainThread
    fun init() {
        program = createProgram()

        val triangle = floatArrayOf(
                -0.5f, -0.5f, 0.0f,
                0.5f, -0.5f, 0.0f,
                0.0f, 0.5f, 0.0f)
//        triangle.flip()

        vao = glGenVertexArrays()
        vbo = glGenBuffers()

        glBindVertexArray(vao)

        glBindBuffer(GL_ARRAY_BUFFER, vbo)
        glBufferData(GL_ARRAY_BUFFER, triangle, GL_STATIC_DRAW)


        glVertexAttribPointer(0, 3, GL_FLOAT, false, 3*4, 0)
//        glEnableVertexAttribArray(0)

        glBindVertexArray(0)

    }

    fun draw() {
        glUseProgram(program)
        glBindVertexArray(vao)
        glEnableVertexAttribArray(0)
        glBindBuffer(GL_ARRAY_BUFFER, vbo)
        glDrawArrays(GL_TRIANGLES, 0, 3)
        glBindVertexArray(0)
    }

    private fun createProgram(): Int {
        val vertexShader = loadShader(GL_VERTEX_SHADER, vertexShaderText)
        val fragmentShader = loadShader(GL_FRAGMENT_SHADER, fragmentShaderText)

        val shaderProgram = glCreateProgram()
        glAttachShader(shaderProgram, vertexShader)
        glAttachShader(shaderProgram, fragmentShader)
        glLinkProgram(shaderProgram)

        val status = glGetProgrami(shaderProgram, GL_LINK_STATUS)
        if (status != 1) {
            println(glGetProgramInfoLog(shaderProgram))
            throw RuntimeException("Shader compile failed status: $status")
        }

        glDeleteShader(vertexShader)
        glDeleteShader(fragmentShader)
        return shaderProgram
    }

    @MainThread
    private fun loadShader(type: Int, text: String): Int {
        val shader = glCreateShader(type)
        glShaderSource(shader, text)
        glCompileShader(shader)

        val status = glGetShaderi(shader, GL_COMPILE_STATUS)
        if (status != 1) {
            println(glGetShaderInfoLog(shader))
            throw RuntimeException("Shader compile failed status: $status")
        }
        return shader
    }
}