package ru.vladislav.sumin.blockoftechandmagic.userinput

import org.lwjgl.glfw.GLFW.*
import ru.vladislav.sumin.blockoftechandmagic.Game
import ru.vladislav.sumin.blockoftechandmagic.markers.MainThread
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserInputManagerImpl @Inject constructor(private val game: Game) : UserInputKeyCallBack {
    @MainThread
    override fun invoke(window: Long, key: Int, scancode: Int, action: Int, mods: Int) {
        println("Key: $key, scancode: $scancode, action:${action}, mods:${mods}")
        if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE) game.setNeedClose()
    }
}