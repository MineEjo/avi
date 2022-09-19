package com.github.mineejo.avi.interactions

import com.github.mineejo.avi.settings.ProjectSettingsState
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.actionSystem.PlatformDataKeys
import com.intellij.openapi.editor.Caret
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project
import org.jetbrains.annotations.NotNull


class ContextMenuSelectCode : AnAction() {
    override fun update(@NotNull e: AnActionEvent) {
        val project: Project? = e.project
        val editor: Editor? = e.getData(CommonDataKeys.EDITOR)

        // Set visibility only in the case of
        // existing project editor, and selection
        e.presentation.isEnabledAndVisible = project != null && editor != null && editor.selectionModel.hasSelection()
    }

    // !Todo: Transfer simple functions to return to notifications or turn off menus.
    override fun actionPerformed(e: AnActionEvent) {
        val editor: Editor = e.getRequiredData(CommonDataKeys.EDITOR)
        val project: Project = e.getRequiredData(CommonDataKeys.PROJECT)
        val fileName: String? = e.getData(PlatformDataKeys.VIRTUAL_FILE)?.name

        val primaryCaret: Caret = editor.caretModel.primaryCaret
        val start: Int = primaryCaret.selectionStart
        val end: Int = primaryCaret.selectionEnd

        if (primaryCaret.selectedText == null || fileName == null) return

        val text: String = primaryCaret.selectedText ?: ""
        val id: Int = fileName.hashCode() + start + end + text.hashCode()

        val settings: ProjectSettingsState = ProjectSettingsState().getInstance(project)

        if (settings.dots.contains(id)) return

        settings.dots = settings.dots + arrayOf(id, fileName, arrayOf(start, end), text)

        primaryCaret.removeSelection()
    }
}