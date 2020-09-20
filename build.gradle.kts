import ru.vladislavsumin.build.Configuration

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

dependencies {
    implementation(kotlin("stdlib"))
    with(Configuration.Dependencies) {
        implementation(dagger)
        kapt(daggerCompiler)
    }

    // implementation(platform("org.lwjgl:lwjgl-bom:$lwjglVersion"))
    with(Configuration.Dependencies.LWJGL) {
        implementation(lwjgl)
        implementation(assimp)
        implementation(bgfx)
        implementation(cuda)
        implementation(egl)
        implementation(glfw)
        implementation(jawt)
        implementation(jemalloc)
        implementation(libdivide)
        implementation(llvm)
        implementation(lmdb)
        implementation(lz4)
        implementation(meow)
        implementation(nanovg)
        implementation(nfd)
        implementation(nuklear)
        implementation(odbc)
        implementation(openal)
        implementation(opencl)
        implementation(opengl)
        implementation(opengles)
        implementation(openvr)
        implementation(opus)
        //implementation(ovr)
        implementation(par)
        implementation(remotery)
        implementation(rpmalloc)
        implementation(sse)
        implementation(stb)
        implementation(tinyexr)
        implementation(tinyfd)
        implementation(tootle)
        implementation(vma)
        implementation(vulkan)
        implementation(xxhash)
        implementation(yoga)
        implementation(zstd)
    }
    with(Configuration.Dependencies.LWJGL.Runtime) {
        runtimeOnly(lwjgl)
        runtimeOnly(assimp)
        runtimeOnly(bgfx)
        runtimeOnly(glfw)
        runtimeOnly(jemalloc)
        runtimeOnly(libdivide)
        runtimeOnly(llvm)
        runtimeOnly(lmdb)
        runtimeOnly(lz4)
        runtimeOnly(meow)
        runtimeOnly(nanovg)
        runtimeOnly(nfd)
        runtimeOnly(nuklear)
        runtimeOnly(openal)
        runtimeOnly(opengl)
        runtimeOnly(opengles)
        runtimeOnly(openvr)
        runtimeOnly(opus)
        //runtimeOnly(ovr)
        runtimeOnly(par)
        runtimeOnly(remotery)
        runtimeOnly(rpmalloc)
        runtimeOnly(sse)
        runtimeOnly(stb)
        runtimeOnly(tinyexr)
        runtimeOnly(tinyfd)
        runtimeOnly(tootle)
        runtimeOnly(vma)
        runtimeOnly(xxhash)
        runtimeOnly(yoga)
        runtimeOnly(zstd)
        if (Configuration.Versions.lwjglNatives == "natives-macos")
            runtimeOnly(vulkan)
    }
}
