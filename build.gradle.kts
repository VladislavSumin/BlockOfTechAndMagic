import ru.vladislavsumin.build.Configuration

plugins {
    java
    kotlin("jvm") version "1.4.10"
    kotlin("kapt") version "1.4.10"
}

group = "ru.vladislavsumin"
version = "1.0-SNAPSHOT"

allprojects {
    repositories {
        mavenCentral()
        maven { setUrl("https://jitpack.io") }
    }
}

dependencies {
    implementation(project(":opengl"))

    with(Configuration.Dependencies) {
        implementation(dagger)
        kapt(daggerCompiler)
    }
}
