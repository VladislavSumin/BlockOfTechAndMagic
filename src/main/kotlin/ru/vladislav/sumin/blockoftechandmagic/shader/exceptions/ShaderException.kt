package ru.vladislav.sumin.blockoftechandmagic.shader.exceptions

import java.lang.Exception

open class ShaderException : Exception {
    constructor(message: String) : super(message)
    constructor(message: String, cause: Throwable) : super(message, cause)
}