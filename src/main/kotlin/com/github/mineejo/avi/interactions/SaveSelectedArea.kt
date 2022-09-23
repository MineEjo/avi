package com.github.mineejo.avi.interactions

import com.github.mineejo.avi.area.VisualAreaWriter
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project
import org.jetbrains.annotations.NotNull


class SaveSelectedArea : AnAction() {
    override fun update(@NotNull e: AnActionEvent) {
        val project: Project? = e.project
        val editor: Editor? = e.getData(CommonDataKeys.EDITOR)

        // Set visibility only in the case of
        // existing project editor, and selection
        e.presentation.isEnabledAndVisible = project != null && editor != null && editor.selectionModel.hasSelection()
    }

    override fun actionPerformed(e: AnActionEvent) = VisualAreaWriter().add(e)
}
