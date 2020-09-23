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
    }
}
