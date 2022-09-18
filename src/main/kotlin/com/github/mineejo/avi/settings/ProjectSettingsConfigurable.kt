package com.github.mineejo.avi.settings

import com.intellij.openapi.options.Configurable
import org.jetbrains.annotations.Nls
import org.jetbrains.annotations.Nullable
import javax.swing.JComponent

class ProjectSettingsConfigurable : Configurable {

    private var mySettingsComponent: ProjectSettingsComponent? = null

    // A default constructor with no arguments is required because this implementation
    // is registered as an applicationConfigurable EP

    // A default constructor with no arguments is required because this implementation
    // is registered as an applicationConfigurable EP
    @Nls(capitalization = Nls.Capitalization.Title)
    override fun getDisplayName(): String {
        return "SDK: Application Settings Example"
    }

    @Nullable
    override fun createComponent(): JComponent? {
        mySettingsComponent = ProjectSettingsComponent()
        return mySettingsComponent!!.getPanel()
    }

    override fun isModified(): Boolean {
        return false
    }

    override fun apply() {}

    override fun reset() {}

    override fun disposeUIResources() {
        mySettingsComponent = null
    }

}