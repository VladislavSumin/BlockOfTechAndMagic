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

    implementation(project(":opengl"))

    with(Configuration.Dependencies) {
        implementation(coroutines)
        implementation(dagger)
        kapt(daggerCompiler)
    }
}
