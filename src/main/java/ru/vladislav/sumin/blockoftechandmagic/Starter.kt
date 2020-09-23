package ru.vladislav.sumin.blockoftechandmagic

import ru.vladislav.sumin.blockoftechandmagic.client.Game
import ru.vladislav.sumin.blockoftechandmagic.di.AppComponent
import ru.vladislav.sumin.blockoftechandmagic.di.DaggerAppComponent
import javax.inject.Inject

fun main() {
    Starter().start()
}

class Starter {
    @Inject
    lateinit var game: Game
    private lateinit var appComponent: AppComponent

    fun start() {
        appComponent = DaggerAppComponent.builder().build()
        appComponent.inject(this)
        game.run()
    }
}