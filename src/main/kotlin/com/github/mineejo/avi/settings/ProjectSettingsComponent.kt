package com.github.mineejo.avi.settings

import com.intellij.ui.components.JBLabel
import com.intellij.util.ui.FormBuilder
import javax.swing.JPanel


class ProjectSettingsComponent {
    private val label: String = "..."
    private var myMainPanel: JPanel? = null

    init {
        myMainPanel = FormBuilder.createFormBuilder()
            .addComponent(JBLabel(label), 1)
            .addComponentFillVertically(JPanel(), 0)
            .panel
    }

    fun getPanel(): JPanel? {
        return myMainPanel
    }
}