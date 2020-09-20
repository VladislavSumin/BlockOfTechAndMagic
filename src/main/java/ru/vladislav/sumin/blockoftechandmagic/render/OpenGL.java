package ru.vladislav.sumin.blockoftechandmagic.render;

import org.lwjgl.opengl.*;

public class OpenGL {

    public static final int
            GL_BYTE = GL11.GL_BYTE,
            GL_UNSIGNED_BYTE = GL11.GL_UNSIGNED_BYTE,
            GL_SHORT = GL11.GL_SHORT,
            GL_UNSIGNED_SHORT = GL11.GL_UNSIGNED_SHORT,
            GL_INT = GL11.GL_INT,
            GL_UNSIGNED_INT = GL11.GL_UNSIGNED_INT,
            GL_FLOAT = GL11.GL_FLOAT,
            GL_2_BYTES = GL11.GL_2_BYTES,
            GL_3_BYTES = GL11.GL_3_BYTES,
            GL_4_BYTES = GL11.GL_4_BYTES,
            GL_DOUBLE = GL11.GL_DOUBLE;

    public static final int
            GL_STREAM_DRAW = GL15.GL_STREAM_DRAW,
            GL_STREAM_READ = GL15.GL_STREAM_READ,
            GL_STREAM_COPY = GL15.GL_STREAM_COPY,
            GL_STATIC_DRAW = GL15.GL_STATIC_DRAW,
            GL_STATIC_READ = GL15.GL_STATIC_READ,
            GL_STATIC_COPY = GL15.GL_STATIC_COPY,
            GL_DYNAMIC_DRAW = GL15.GL_DYNAMIC_DRAW,
            GL_DYNAMIC_READ = GL15.GL_DYNAMIC_READ,
            GL_DYNAMIC_COPY = GL15.GL_DYNAMIC_COPY;

    public static final int
            GL_POINTS = GL11.GL_POINTS,
            GL_LINES = GL11.GL_LINES,
            GL_LINE_LOOP = GL11.GL_LINE_LOOP,
            GL_LINE_STRIP = GL11.GL_LINE_STRIP,
            GL_TRIANGLES = GL11.GL_TRIANGLES,
            GL_TRIANGLE_STRIP = GL11.GL_TRIANGLE_STRIP,
            GL_TRIANGLE_FAN = GL11.GL_TRIANGLE_FAN,
            GL_QUADS = GL11.GL_QUADS,
            GL_QUAD_STRIP = GL11.GL_QUAD_STRIP,
            GL_POLYGON = GL11.GL_POLYGON;

    public static final int
            GL_VERTEX_SHADER = GL20.GL_VERTEX_SHADER,
            GL_FRAGMENT_SHADER = GL20.GL_FRAGMENT_SHADER;

    public static final int
            GL_COMPILE_STATUS = GL20.GL_COMPILE_STATUS,
            GL_LINK_STATUS = GL20.GL_LINK_STATUS;

    public static final int
            GL_ARRAY_BUFFER = GL15.GL_ARRAY_BUFFER,
            GL_ELEMENT_ARRAY_BUFFER = GL15.GL_ELEMENT_ARRAY_BUFFER;

    public static int glCreateShader(int type) {
        return GL20.glCreateShader(type);
    }

    public static void glShaderSource(int shader, String string) {
        GL20.glShaderSource(shader, string);
    }

    public static void glCompileShader(int shader) {
        GL20.glCompileShader(shader);
    }

    public static int glGetShaderi(int shader, int pname) {
        return GL20.glGetShaderi(shader, pname);
    }

    public static String glGetShaderInfoLog(int shader) {
        return GL20.glGetShaderInfoLog(shader, glGetShaderi(shader, GL20.GL_INFO_LOG_LENGTH));
    }

    public static void glDeleteShader(int shader) {
        GL20.glDeleteShader(shader);
    }

    public static int glCreateProgram() {
        return GL20.glCreateProgram();
    }

    public static void glAttachShader(int program, int shader) {
        GL20.glAttachShader(program, shader);
    }

    public static void glLinkProgram(int program) {
        GL20.glLinkProgram(program);
    }

    public static int glGetProgrami(int program, int pname) {
        return GL20.glGetProgrami(program, pname);
    }

    public static String glGetProgramInfoLog(int program) {
        return GL20.glGetProgramInfoLog(program, glGetProgrami(program, GL20.GL_INFO_LOG_LENGTH));
    }

    public static void glDeleteProgram(int program) {
        GL20.glDeleteProgram(program);
    }

    public static int glGenBuffers() {
        return GL15.glGenBuffers();
    }

    public static void glDeleteBuffers(int buffer) {
        GL15.glDeleteBuffers(buffer);
    }

    public static void glBindBuffer(int target, int buffer) {
        GL15.glBindBuffer(target, buffer);
    }

    public static void glBufferData(int target, int[] data, int usage) {
        GL15.glBufferData(target, data, usage);
    }

    public static void glBufferData(int target, float[] data, int usage) {
        GL15.glBufferData(target, data, usage);
    }

    public static int glGenVertexArrays() {
        return GL30.glGenVertexArrays();
    }

    public static void glDeleteVertexArrays(int array) {
        GL30.glDeleteVertexArrays(array);
    }

    public static void glBindVertexArray(int array) {
        GL30.glBindVertexArray(array);
    }

    public static void glVertexAttribPointer(int index, int size, int type, boolean normalized, int stride, long pointer) {
        GL20.glVertexAttribPointer(index, size, type, normalized, stride, pointer);
    }

    public static void glEnableVertexAttribArray(int index) {
        GL20.glEnableVertexAttribArray(index);
    }

    public static void glDrawElements(int mode, int count, int type, long indices) {
        GL11.glDrawElements(mode, count, type, indices);
    }

    public static void glDrawArrays(int mode, int first, int count) {
        GL11.glDrawArrays(mode, first, count);
    }

    public static void glUseProgram(int program) {
        GL20.glUseProgram(program);
    }
}
