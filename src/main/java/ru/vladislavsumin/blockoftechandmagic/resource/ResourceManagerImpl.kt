package ru.vladislavsumin.blockoftechandmagic.resource

import ru.vladislavsumin.blockoftechandmagic.resource.exceptions.ResourceNotFoundException
import ru.vladislavsumin.core.utils.BufferUtils
import java.io.*
import java.net.URI
import java.nio.file.*
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.collections.ArrayList

@Singleton
class ResourceManagerImpl @Inject constructor() : ResourceManager {
    private val resourceSources: MutableList<Path> = ArrayList()

    init {
        //TODO move to init fun
        val uri = javaClass.classLoader.getResource("resourceMark")!!.toURI()
        if (uri.scheme == "jar") {
            val createFileSystem = createFileSystem(URI.create(uri.toString().split("!").first()))
            println(createFileSystem)
        }
        resourceSources.add(Paths.get(uri).parent)
        println("AAAAA ALL DONE")
    }

    private fun createFileSystem(uri: URI): FileSystem {
        assert(uri.scheme == "jar")
        val env = mutableMapOf("create" to "true")
        return FileSystems.newFileSystem(uri, env)
    }

    override suspend fun getConfiguration(name: String): Properties {
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

    override fun getResourceAsStream(path: String): InputStream {
        return javaClass.classLoader.getResourceAsStream(path)
            ?: throw ResourceNotFoundException("Resource $path not found")
    }

    override fun getResourceAsPath(path: String): Path {
        val resource = javaClass.classLoader.getResource(path)
            ?: throw ResourceNotFoundException("Resource $path not found")
        return Paths.get(resource.toURI())
    }
}