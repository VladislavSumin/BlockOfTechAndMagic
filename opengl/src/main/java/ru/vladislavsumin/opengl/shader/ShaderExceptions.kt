package ru.vladislavsumin.opengl.shader

import ru.vladislavsumin.opengl.OpenGlException

open class ShaderException(message: String) : OpenGlException(message)

class ShaderCompileException(message: String) : ShaderException(message)
class ShaderProgramCreateException(message: String) : ShaderException(message)
class UnknownUniformType(message: String) : ShaderException(message)
