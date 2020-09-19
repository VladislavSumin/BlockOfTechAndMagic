package ru.vladislav.sumin.blockoftechandmagic.shader.exceptions

class ShaderCompileException : ShaderException {
    constructor(message: String) : super(message)
    constructor(message: String, cause: Throwable) : super(message, cause)
}