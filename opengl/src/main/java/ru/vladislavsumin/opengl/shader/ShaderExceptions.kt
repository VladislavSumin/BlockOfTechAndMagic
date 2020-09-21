package ru.vladislavsumin.opengl.shader

import java.lang.Exception

open class ShaderException(message: String) : Exception(message)

class ShaderCompileException(message: String) : ShaderException(message)
class ShaderAlreadyClosedException(message: String) : ShaderException(message)
class ShaderProgramCreateException(message: String) : ShaderException(message)
class ProgramAlreadyClosedException(message: String) : ShaderException(message)
