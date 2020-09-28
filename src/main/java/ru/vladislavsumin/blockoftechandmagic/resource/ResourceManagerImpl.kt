package ru.vladislavsumin.blockoftechandmagic.resource

import ru.vladislavsumin.blockoftechandmagic.log.LogTags
import ru.vladislavsumin.blockoftechandmagic.log.initializeAt
import ru.vladislavsumin.blockoftechandmagic.log.loadAt
import ru.vladislavsumin.blockoftechandmagic.log.logger
import ru.vladislavsumin.blockoftechandmagic.resource.exceptions.ResourceNotFoundException
import ru.vladislavsumin.core.utils.BufferUtils
import java.io.ByteArrayInputStream
import java.io.File
import java.net.URI
import java.nio.file.*
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.collections.ArrayList

@Singleton
class ResourceManagerImpl @Inject constructor() : ResourceManager {
    companion object {
        private val log = logger(LogTags.RESOURCES)
    }

    private val resourceSources: MutableList<Path> = ArrayList()

    override fun init() {
        log.initializeAt("resource manager") {
            createFileSystemsFromResources()

            log.loadAt("self filesystem") {
                createThisJarFileSystem()
            }
        }
    }

    private fun createFileSystemsFromResources() {
        File("resources").listFiles()!!
            .asSequence()
            .filter { it.name.endsWith("zip") }
            .forEach {
                log.loadAt("${it.name} filesystem") {
                    resourceSources.add(
                        createFileSystem(URI.create("jar:${it.toURI()}"))
                            .rootDirectories.iterator().next()
                    )
                }
            }
    }

    private fun createThisJarFileSystem() {
        val uri = javaClass.classLoader.getResource("resourceMark")!!.toURI()
        if (uri.scheme == "jar") {
            val createFileSystem = createFileSystem(URI.create(uri.toString().split("!").first()))
            println(createFileSystem)
        }
        resourceSources.add(Paths.get(uri).parent)
    }

    private fun createFileSystem(uri: URI): FileSystem {
        assert(uri.scheme == "jar")
        val env = mutableMapOf("create" to "true", "useTempFile" to "false")
        return FileSystems.newFileSystem(uri, env)
    }

    override fun getConfiguration(name: String): Properties {
        //TODO make async
        val userConfiguration = Paths.get("$name.properties")
        val defaultConfiguration = getResourceAsPath("configuration/$name.properties")

        val defaultConfigurationStream = ByteArrayInputStream(BufferUtils.loadFile(defaultConfiguration).array())

        val properties = Properties().apply {
            load(defaultConfigurationStream)
        }
        if (!userConfiguration.toFile().isFile) {
            Files.copy(defaultConfiguration, userConfiguration)
        } else {
            properties.load(userConfiguration.toFile().reader())
        }
        return properties
    }

    override fun getResourceAsPath(path: String): Path {
        return resourceSources
            .asSequence()
            .map { it.resolve(path) }
            .find { Files.isRegularFile(it) }
            ?: throw ResourceNotFoundException("Resource $path not found")
    }

    override fun destroy() {
        resourceSources
            .asSequence()
            .map { it.fileSystem }
            .filter { it != FileSystems.getDefault() }
            .forEach { it.close() }
    }
}