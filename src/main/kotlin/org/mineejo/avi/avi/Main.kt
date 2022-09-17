package org.mineejo.avi.avi

import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.PlatformDataKeys
import com.intellij.openapi.project.DumbAwareAction
import com.intellij.openapi.ui.Messages

class Main : DumbAwareAction() {
    override fun actionPerformed(e: AnActionEvent) {
        val project = e.getData(PlatformDataKeys.PROJECT)
        Messages.showMessageDialog(project, "Hi", "Greet", Messages.getInformationIcon())
    }
}
