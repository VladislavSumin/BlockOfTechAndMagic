package ru.vladislavsumin.blockoftechandmagic.log

import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

fun logger(tag: LogTags): Logger {
    return LogManager.getLogger(tag.name)
}