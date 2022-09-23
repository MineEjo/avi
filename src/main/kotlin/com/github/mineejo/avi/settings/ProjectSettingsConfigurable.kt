package com.github.mineejo.avi.settings

import com.intellij.openapi.options.Configurable
import org.jetbrains.annotations.Nls
import org.jetbrains.annotations.Nullable
import javax.swing.JComponent

class ProjectSettingsConfigurable : Configurable {
    private var component: ProjectSettingsComponent? = null
    private val displayName: String = "Adaptive Visual Interaction"

    // A default constructor with no arguments is required because this implementation
    // is registered as an applicationConfigurable EP

    // A default constructor with no arguments is required because this implementation
    // is registered as an applicationConfigurable EP
    @Nls(capitalization = Nls.Capitalization.Title)
    override fun getDisplayName(): String = displayName

    @Nullable
    override fun createComponent(): JComponent? {
        component = ProjectSettingsComponent()
        return component!!.getPanel()
    }

    override fun isModified(): Boolean {
        return false
    }

    override fun apply() {}

    override fun reset() {}

    override fun disposeUIResources() {
        component = null
    }
}
