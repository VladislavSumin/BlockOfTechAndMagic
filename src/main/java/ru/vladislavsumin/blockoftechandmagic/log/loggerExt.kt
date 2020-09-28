package ru.vladislavsumin.blockoftechandmagic.log

import org.apache.logging.log4j.Level
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import kotlin.time.Duration
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime

fun logger(tag: LogTags): Logger {
    return LogManager.getLogger(tag.name)
}


@OptIn(ExperimentalTime::class)
inline fun Logger.initializeAt(name: String, logLevel: Level = Level.INFO, block: () -> Unit) {
    if (level >= logLevel)
        log(logLevel, "[{}] initialize", name)
    val initTime = measureTime(block)
    if (level >= logLevel)
        log(logLevel, "[{}] initialize at {} sec", name, initTime.inFormattedSeconds())
}

@OptIn(ExperimentalTime::class)
inline fun Logger.loadAt(name: String, logLevel: Level = Level.DEBUG, block: () -> Unit) {
    val initTime = measureTime(block)
    if (level >= logLevel)
        log(logLevel, "{} load at {} sec", name, initTime.inFormattedSeconds())
}

@ExperimentalTime
fun Duration.inFormattedSeconds(): String {
    return "%.3f".format(this.inSeconds)
}