package ru.vladislavsumin.opengl

import org.lwjgl.opengl.GL33.*

class OpenGlSateManager {
    fun setDepthTest(isEnabled: Boolean) = setGlFeatureEnabled(isEnabled, GL_DEPTH_TEST)
    fun setCullFace(isEnabled: Boolean) = setGlFeatureEnabled(isEnabled, GL_CULL_FACE)

    private fun setGlFeatureEnabled(isEnabled: Boolean, feature: Int) {
        if (isEnabled) glEnable(feature)
        else glDisable(feature)
    }
}