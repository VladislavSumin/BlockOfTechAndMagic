package ru.vladislavsumin.build

import org.gradle.internal.os.OperatingSystem

object Configuration {
    object Versions {
        const val lwjgl = "3.2.1"

        //TODO move to other class
        @Suppress("INACCESSIBLE_TYPE")
        val lwjglNatives = when (OperatingSystem.current()) {
            OperatingSystem.LINUX -> "natives-linux"
            OperatingSystem.MAC_OS -> "natives-macos"
            OperatingSystem.WINDOWS -> "natives-windows"
            else -> throw Error("Unrecognized or unsupported Operating system. Please set \"lwjglNatives\" manually")
        }

        const val dagger = "2.29.1"
    }

    object Dependencies {
        const val dagger = "com.google.dagger:dagger:${Versions.dagger}"
        const val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"

        object LWJGL {
            const val lwjgl = "org.lwjgl:lwjgl:${Versions.lwjgl}"
            const val assimp = "org.lwjgl:lwjgl-assimp:${Versions.lwjgl}"
            const val bgfx = "org.lwjgl:lwjgl-bgfx:${Versions.lwjgl}"
            const val cuda = "org.lwjgl:lwjgl-cuda:${Versions.lwjgl}"
            const val egl = "org.lwjgl:lwjgl-egl:${Versions.lwjgl}"
            const val glfw = "org.lwjgl:lwjgl-glfw:${Versions.lwjgl}"
            const val jawt = "org.lwjgl:lwjgl-jawt:${Versions.lwjgl}"
            const val jemalloc = "org.lwjgl:lwjgl-jemalloc:${Versions.lwjgl}"
            const val libdivide = "org.lwjgl:lwjgl-libdivide:${Versions.lwjgl}"
            const val llvm = "org.lwjgl:lwjgl-llvm:${Versions.lwjgl}"
            const val lmdb = "org.lwjgl:lwjgl-lmdb:${Versions.lwjgl}"
            const val lz4 = "org.lwjgl:lwjgl-lz4:${Versions.lwjgl}"
            const val meow = "org.lwjgl:lwjgl-meow:${Versions.lwjgl}"
            const val nanovg = "org.lwjgl:lwjgl-nanovg:${Versions.lwjgl}"
            const val nfd = "org.lwjgl:lwjgl-nfd:${Versions.lwjgl}"
            const val nuklear = "org.lwjgl:lwjgl-nuklear:${Versions.lwjgl}"
            const val odbc = "org.lwjgl:lwjgl-odbc:${Versions.lwjgl}"
            const val openal = "org.lwjgl:lwjgl-openal:${Versions.lwjgl}"
            const val opencl = "org.lwjgl:lwjgl-opencl:${Versions.lwjgl}"
            const val opengl = "org.lwjgl:lwjgl-opengl:${Versions.lwjgl}"
            const val opengles = "org.lwjgl:lwjgl-opengles:${Versions.lwjgl}"
            const val openvr = "org.lwjgl:lwjgl-openvr:${Versions.lwjgl}"
            const val opus = "org.lwjgl:lwjgl-opus:${Versions.lwjgl}"
            const val ovr = "org.lwjgl:lwjgl-ovr:${Versions.lwjgl}"
            const val par = "org.lwjgl:lwjgl-par:${Versions.lwjgl}"
            const val remotery = "org.lwjgl:lwjgl-remotery:${Versions.lwjgl}"
            const val rpmalloc = "org.lwjgl:lwjgl-rpmalloc:${Versions.lwjgl}"
            const val sse = "org.lwjgl:lwjgl-sse:${Versions.lwjgl}"
            const val stb = "org.lwjgl:lwjgl-stb:${Versions.lwjgl}"
            const val tinyexr = "org.lwjgl:lwjgl-tinyexr:${Versions.lwjgl}"
            const val tinyfd = "org.lwjgl:lwjgl-tinyfd:${Versions.lwjgl}"
            const val tootle = "org.lwjgl:lwjgl-tootle:${Versions.lwjgl}"
            const val vma = "org.lwjgl:lwjgl-vma:${Versions.lwjgl}"
            const val vulkan = "org.lwjgl:lwjgl-vulkan:${Versions.lwjgl}"
            const val xxhash = "org.lwjgl:lwjgl-xxhash:${Versions.lwjgl}"
            const val yoga = "org.lwjgl:lwjgl-yoga:${Versions.lwjgl}"
            const val zstd = "org.lwjgl:lwjgl-zstd:${Versions.lwjgl}"

            object Runtime {
                val lwjgl = "org.lwjgl:lwjgl:${Versions.lwjgl}:${Versions.lwjglNatives}"
                val assimp = "org.lwjgl:lwjgl-assimp:${Versions.lwjgl}:${Versions.lwjglNatives}"
                val bgfx = "org.lwjgl:lwjgl-bgfx:${Versions.lwjgl}:${Versions.lwjglNatives}"
                val glfw = "org.lwjgl:lwjgl-glfw:${Versions.lwjgl}:${Versions.lwjglNatives}"
                val jemalloc = "org.lwjgl:lwjgl-jemalloc:${Versions.lwjgl}:${Versions.lwjglNatives}"
                val libdivide = "org.lwjgl:lwjgl-libdivide:${Versions.lwjgl}:${Versions.lwjglNatives}"
                val llvm = "org.lwjgl:lwjgl-llvm:${Versions.lwjgl}:${Versions.lwjglNatives}"
                val lmdb = "org.lwjgl:lwjgl-lmdb:${Versions.lwjgl}:${Versions.lwjglNatives}"
                val lz4 = "org.lwjgl:lwjgl-lz4:${Versions.lwjgl}:${Versions.lwjglNatives}"
                val meow = "org.lwjgl:lwjgl-meow:${Versions.lwjgl}:${Versions.lwjglNatives}"
                val nanovg = "org.lwjgl:lwjgl-nanovg:${Versions.lwjgl}:${Versions.lwjglNatives}"
                val nfd = "org.lwjgl:lwjgl-nfd:${Versions.lwjgl}:${Versions.lwjglNatives}"
                val nuklear = "org.lwjgl:lwjgl-nuklear:${Versions.lwjgl}:${Versions.lwjglNatives}"
                val openal = "org.lwjgl:lwjgl-openal:${Versions.lwjgl}:${Versions.lwjglNatives}"
                val opengl = "org.lwjgl:lwjgl-opengl:${Versions.lwjgl}:${Versions.lwjglNatives}"
                val opengles = "org.lwjgl:lwjgl-opengles:${Versions.lwjgl}:${Versions.lwjglNatives}"
                val openvr = "org.lwjgl:lwjgl-openvr:${Versions.lwjgl}:${Versions.lwjglNatives}"
                val opus = "org.lwjgl:lwjgl-opus:${Versions.lwjgl}:${Versions.lwjglNatives}"
                val ovr = "org.lwjgl:lwjgl-ovr:${Versions.lwjgl}:${Versions.lwjglNatives}"
                val par = "org.lwjgl:lwjgl-par:${Versions.lwjgl}:${Versions.lwjglNatives}"
                val remotery = "org.lwjgl:lwjgl-remotery:${Versions.lwjgl}:${Versions.lwjglNatives}"
                val rpmalloc = "org.lwjgl:lwjgl-rpmalloc:${Versions.lwjgl}:${Versions.lwjglNatives}"
                val sse = "org.lwjgl:lwjgl-sse:${Versions.lwjgl}:${Versions.lwjglNatives}"
                val stb = "org.lwjgl:lwjgl-stb:${Versions.lwjgl}:${Versions.lwjglNatives}"
                val tinyexr = "org.lwjgl:lwjgl-tinyexr:${Versions.lwjgl}:${Versions.lwjglNatives}"
                val tinyfd = "org.lwjgl:lwjgl-tinyfd:${Versions.lwjgl}:${Versions.lwjglNatives}"
                val tootle = "org.lwjgl:lwjgl-tootle:${Versions.lwjgl}:${Versions.lwjglNatives}"
                val vma = "org.lwjgl:lwjgl-vma:${Versions.lwjgl}:${Versions.lwjglNatives}"
                val xxhash = "org.lwjgl:lwjgl-xxhash:${Versions.lwjgl}:${Versions.lwjglNatives}"
                val yoga = "org.lwjgl:lwjgl-yoga:${Versions.lwjgl}:${Versions.lwjglNatives}"
                val zstd = "org.lwjgl:lwjgl-zstd:${Versions.lwjgl}:${Versions.lwjglNatives}"
                val vulkan = "org.lwjgl:lwjgl-vulkan:${Versions.lwjgl}:${Versions.lwjglNatives}"
            }
        }
    }
}