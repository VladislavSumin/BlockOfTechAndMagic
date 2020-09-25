package ru.vladislav.sumin.blockoftechandmagic.client.userinput

import org.lwjgl.glfw.GLFW.*
import ru.vladislav.sumin.blockoftechandmagic.client.Game
import ru.vladislavsumin.opengl.markers.MainThread
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserInputManagerImpl @Inject constructor() : UserInputManager, UserInputKeyCallBack {
    override val pressedKeys: BooleanArray = BooleanArray(1024)

    @MainThread
    override fun invoke(window: Long, key: Int, scancode: Int, action: Int, mods: Int) {
        when (action) {
            GLFW_PRESS -> pressedKeys[key] = true
            GLFW_RELEASE -> pressedKeys[key] = false
        }
//        if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE) game.setNeedClose()
    }
}