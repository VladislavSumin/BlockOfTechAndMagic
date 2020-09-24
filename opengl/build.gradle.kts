import ru.vladislavsumin.build.Configuration

plugins {
    java
    kotlin("jvm")
    kotlin("kapt")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))

    with(Configuration.Dependencies) {
        implementation(coroutines)
        api(glm)
    }

// implementation(platform("org.lwjgl:lwjgl-bom:$lwjglVersion"))
    with(Configuration.Dependencies.LWJGL) {
        api(lwjgl)
        api(assimp)
        api(bgfx)
        api(cuda)
        api(egl)
        api(glfw)
        api(jawt)
        api(jemalloc)
        api(libdivide)
        api(llvm)
        api(lmdb)
        api(lz4)
        api(meow)
        api(nanovg)
        api(nfd)
        api(nuklear)
        api(odbc)
        api(openal)
        api(opencl)
        api(opengl)
        api(opengles)
        api(openvr)
        api(opus)
        //api(ovr)
        api(par)
        api(remotery)
        api(rpmalloc)
        api(sse)
        api(stb)
        api(tinyexr)
        api(tinyfd)
        api(tootle)
        api(vma)
        api(vulkan)
        api(xxhash)
        api(yoga)
        api(zstd)
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

    with(Configuration.Dependencies.Test) {
        testImplementation(jUnit4)
    }
}
