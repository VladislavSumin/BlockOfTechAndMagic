package ru.vladislavsumin.opengl;

import org.lwjgl.opengl.*;
import org.lwjgl.system.NativeType;

import java.nio.IntBuffer;

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
            GL_ARRAY_BUFFER = GL15.GL_ARRAY_BUFFER,
            GL_ELEMENT_ARRAY_BUFFER = GL15.GL_ELEMENT_ARRAY_BUFFER;

    public static final int
            GL_SHADER_TYPE = GL20.GL_SHADER_TYPE,
            GL_DELETE_STATUS = GL20.GL_DELETE_STATUS,
            GL_COMPILE_STATUS = GL20.GL_COMPILE_STATUS,
            GL_LINK_STATUS = GL20.GL_LINK_STATUS,
            GL_VALIDATE_STATUS = GL20.GL_VALIDATE_STATUS,
            GL_INFO_LOG_LENGTH = GL20.GL_INFO_LOG_LENGTH,
            GL_ATTACHED_SHADERS = GL20.GL_ATTACHED_SHADERS,
            GL_ACTIVE_UNIFORMS = GL20.GL_ACTIVE_UNIFORMS,
            GL_ACTIVE_UNIFORM_MAX_LENGTH = GL20.GL_ACTIVE_UNIFORM_MAX_LENGTH,
            GL_ACTIVE_ATTRIBUTES = GL20.GL_ACTIVE_ATTRIBUTES,
            GL_ACTIVE_ATTRIBUTE_MAX_LENGTH = GL20.GL_ACTIVE_ATTRIBUTE_MAX_LENGTH,
            GL_SHADER_SOURCE_LENGTH = GL20.GL_SHADER_SOURCE_LENGTH;

    public static final int
            GL_COLOR_INDEX     = GL11.GL_COLOR_INDEX,
            GL_STENCIL_INDEX   = GL11.GL_STENCIL_INDEX,
            GL_DEPTH_COMPONENT = GL11.GL_DEPTH_COMPONENT,
            GL_RED             = GL11.GL_RED,
            GL_GREEN           = GL11.GL_GREEN,
            GL_BLUE            = GL11.GL_BLUE,
            GL_ALPHA           = GL11.GL_ALPHA,
            GL_RGB             = GL11.GL_RGB,
            GL_RGBA            = GL11.GL_RGBA,
            GL_LUMINANCE       = GL11.GL_LUMINANCE,
            GL_LUMINANCE_ALPHA = GL11.GL_LUMINANCE_ALPHA;

    public static final int GL_TEXTURE_2D = GL11.GL_TEXTURE_2D;


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

    public static String glGetActiveUniform(int program, int index, IntBuffer size, IntBuffer type) {
        return GL20.glGetActiveUniform(program, index, size, type);
    }

    public static int glGenTextures() {
        return GL11.glGenTextures();
    }

    public static void glBindTexture(int target, int texture) {
        GL11.glBindTexture(target, texture);
    }

    public static void glDeleteTextures(int texture) {
        GL11.glDeleteTextures(texture);
    }
}
