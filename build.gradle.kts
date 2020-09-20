import org.gradle.internal.os.OperatingSystem

plugins {
    java
    kotlin("jvm") version "1.4.10"
    kotlin("kapt") version "1.4.10"
}

group = "ru.vladislavsumin"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

tasks.withType<JavaCompile> {
    options.annotationProcessorPath = configurations.annotationProcessor.get()
}

val lwjglVersion = "3.2.1"

@Suppress("INACCESSIBLE_TYPE")
val lwjglNatives = when (OperatingSystem.current()) {
    OperatingSystem.LINUX -> "natives-linux"
    OperatingSystem.MAC_OS -> "natives-macos"
    OperatingSystem.WINDOWS -> "natives-windows"
    else -> throw Error("Unrecognized or unsupported Operating system. Please set \"lwjglNatives\" manually")
}

dependencies {
    implementation(kotlin("stdlib"))

    //TODO add Configuration.kt
    implementation("com.google.dagger:dagger:2.29.1")
    kapt("com.google.dagger:dagger-compiler:2.29.1")

//    implementation(platform("org.lwjgl:lwjgl-bom:$lwjglVersion"))

    implementation("org.lwjgl", "lwjgl", lwjglVersion)
    implementation("org.lwjgl", "lwjgl-assimp", lwjglVersion)
    implementation("org.lwjgl", "lwjgl-bgfx", lwjglVersion)
    implementation("org.lwjgl", "lwjgl-cuda", lwjglVersion)
    implementation("org.lwjgl", "lwjgl-egl", lwjglVersion)
    implementation("org.lwjgl", "lwjgl-glfw", lwjglVersion)
    implementation("org.lwjgl", "lwjgl-jawt", lwjglVersion)
    implementation("org.lwjgl", "lwjgl-jemalloc", lwjglVersion)
    implementation("org.lwjgl", "lwjgl-libdivide", lwjglVersion)
    implementation("org.lwjgl", "lwjgl-llvm", lwjglVersion)
    implementation("org.lwjgl", "lwjgl-lmdb", lwjglVersion)
    implementation("org.lwjgl", "lwjgl-lz4", lwjglVersion)
    implementation("org.lwjgl", "lwjgl-meow", lwjglVersion)
    implementation("org.lwjgl", "lwjgl-nanovg", lwjglVersion)
    implementation("org.lwjgl", "lwjgl-nfd", lwjglVersion)
    implementation("org.lwjgl", "lwjgl-nuklear", lwjglVersion)
    implementation("org.lwjgl", "lwjgl-odbc", lwjglVersion)
    implementation("org.lwjgl", "lwjgl-openal", lwjglVersion)
    implementation("org.lwjgl", "lwjgl-opencl", lwjglVersion)
    implementation("org.lwjgl", "lwjgl-opengl", lwjglVersion)
    implementation("org.lwjgl", "lwjgl-opengles", lwjglVersion)
    implementation("org.lwjgl", "lwjgl-openvr", lwjglVersion)
    implementation("org.lwjgl", "lwjgl-opus", lwjglVersion)
    implementation("org.lwjgl", "lwjgl-ovr", lwjglVersion)
    implementation("org.lwjgl", "lwjgl-par", lwjglVersion)
    implementation("org.lwjgl", "lwjgl-remotery", lwjglVersion)
    implementation("org.lwjgl", "lwjgl-rpmalloc", lwjglVersion)
    implementation("org.lwjgl", "lwjgl-sse", lwjglVersion)
    implementation("org.lwjgl", "lwjgl-stb", lwjglVersion)
    implementation("org.lwjgl", "lwjgl-tinyexr", lwjglVersion)
    implementation("org.lwjgl", "lwjgl-tinyfd", lwjglVersion)
    implementation("org.lwjgl", "lwjgl-tootle", lwjglVersion)
    implementation("org.lwjgl", "lwjgl-vma", lwjglVersion)
    implementation("org.lwjgl", "lwjgl-vulkan", lwjglVersion)
    implementation("org.lwjgl", "lwjgl-xxhash", lwjglVersion)
    implementation("org.lwjgl", "lwjgl-yoga", lwjglVersion)
    implementation("org.lwjgl", "lwjgl-zstd", lwjglVersion)
    runtimeOnly("org.lwjgl", "lwjgl", lwjglVersion, classifier = lwjglNatives)
    runtimeOnly("org.lwjgl", "lwjgl-assimp", lwjglVersion, classifier = lwjglNatives)
    runtimeOnly("org.lwjgl", "lwjgl-bgfx", lwjglVersion, classifier = lwjglNatives)
    runtimeOnly("org.lwjgl", "lwjgl-glfw", lwjglVersion, classifier = lwjglNatives)
    runtimeOnly("org.lwjgl", "lwjgl-jemalloc", lwjglVersion, classifier = lwjglNatives)
    runtimeOnly("org.lwjgl", "lwjgl-libdivide", lwjglVersion, classifier = lwjglNatives)
    runtimeOnly("org.lwjgl", "lwjgl-llvm", lwjglVersion, classifier = lwjglNatives)
    runtimeOnly("org.lwjgl", "lwjgl-lmdb", lwjglVersion, classifier = lwjglNatives)
    runtimeOnly("org.lwjgl", "lwjgl-lz4", lwjglVersion, classifier = lwjglNatives)
    runtimeOnly("org.lwjgl", "lwjgl-meow", lwjglVersion, classifier = lwjglNatives)
    runtimeOnly("org.lwjgl", "lwjgl-nanovg", lwjglVersion, classifier = lwjglNatives)
    runtimeOnly("org.lwjgl", "lwjgl-nfd", lwjglVersion, classifier = lwjglNatives)
    runtimeOnly("org.lwjgl", "lwjgl-nuklear", lwjglVersion, classifier = lwjglNatives)
    runtimeOnly("org.lwjgl", "lwjgl-openal", lwjglVersion, classifier = lwjglNatives)
    runtimeOnly("org.lwjgl", "lwjgl-opengl", lwjglVersion, classifier = lwjglNatives)
    runtimeOnly("org.lwjgl", "lwjgl-opengles", lwjglVersion, classifier = lwjglNatives)
    runtimeOnly("org.lwjgl", "lwjgl-openvr", lwjglVersion, classifier = lwjglNatives)
    runtimeOnly("org.lwjgl", "lwjgl-opus", lwjglVersion, classifier = lwjglNatives)
//    runtimeOnly("org.lwjgl", "lwjgl-ovr", lwjglVersion, classifier = lwjglNatives)
    runtimeOnly("org.lwjgl", "lwjgl-par", lwjglVersion, classifier = lwjglNatives)
    runtimeOnly("org.lwjgl", "lwjgl-remotery", lwjglVersion, classifier = lwjglNatives)
    runtimeOnly("org.lwjgl", "lwjgl-rpmalloc", lwjglVersion, classifier = lwjglNatives)
    runtimeOnly("org.lwjgl", "lwjgl-sse", lwjglVersion, classifier = lwjglNatives)
    runtimeOnly("org.lwjgl", "lwjgl-stb", lwjglVersion, classifier = lwjglNatives)
    runtimeOnly("org.lwjgl", "lwjgl-tinyexr", lwjglVersion, classifier = lwjglNatives)
    runtimeOnly("org.lwjgl", "lwjgl-tinyfd", lwjglVersion, classifier = lwjglNatives)
    runtimeOnly("org.lwjgl", "lwjgl-tootle", lwjglVersion, classifier = lwjglNatives)
    runtimeOnly("org.lwjgl", "lwjgl-vma", lwjglVersion, classifier = lwjglNatives)
    if (lwjglNatives == "natives-macos") runtimeOnly("org.lwjgl", "lwjgl-vulkan", lwjglVersion, classifier = lwjglNatives)
    runtimeOnly("org.lwjgl", "lwjgl-xxhash", lwjglVersion, classifier = lwjglNatives)
    runtimeOnly("org.lwjgl", "lwjgl-yoga", lwjglVersion, classifier = lwjglNatives)
    runtimeOnly("org.lwjgl", "lwjgl-zstd", lwjglVersion, classifier = lwjglNatives)
}
