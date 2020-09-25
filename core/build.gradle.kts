import ru.vladislavsumin.build.Configuration

plugins {
    java
    kotlin("jvm")
    kotlin("kapt")
}

dependencies {
    api(kotlin("stdlib"))
    with(Configuration.Dependencies) {
        api(coroutines)
    }

    with(Configuration.Dependencies.Test) {
        testImplementation(jUnit4)
    }
}
