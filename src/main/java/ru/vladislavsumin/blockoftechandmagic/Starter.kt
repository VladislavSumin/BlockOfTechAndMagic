package ru.vladislavsumin.blockoftechandmagic

import ru.vladislavsumin.blockoftechandmagic.client.Game
import ru.vladislavsumin.blockoftechandmagic.di.AppComponent
import ru.vladislavsumin.blockoftechandmagic.di.DaggerAppComponent
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