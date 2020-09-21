package ru.vladislavsumin.opengl

open class OpenGlException(message: String) : Exception(message)
class ResourceAlreadyClosedException(message: String) : OpenGlException(message)