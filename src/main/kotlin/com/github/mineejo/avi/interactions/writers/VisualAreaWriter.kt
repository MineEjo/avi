package com.github.mineejo.avi.interactions.writers

import com.github.mineejo.avi.enums.Area
import com.github.mineejo.avi.enums.AreaLine
import com.github.mineejo.avi.settings.ProjectSettingsState
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.actionSystem.PlatformDataKeys
import com.intellij.openapi.editor.Caret
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.Messages

class VisualAreaWriter(e: AnActionEvent) {
    private val titleWindow: String = "Adaptive Visual Interaction (AVI)"
    private val noSelectedText: String = "An invalid code area is selected!"
    private val noFileName = "Unable to define the file and save the selection!"

    private val editor: Editor = e.getRequiredData(CommonDataKeys.EDITOR)
    private val project: Project = e.getRequiredData(CommonDataKeys.PROJECT)
    private val fileName: String? = e.getData(PlatformDataKeys.VIRTUAL_FILE)?.name

    private val primaryCaret: Caret = editor.caretModel.primaryCaret
    private val start: Int = primaryCaret.selectionStart
    private val end: Int = primaryCaret.selectionEnd

    fun add() {
        if (primaryCaret.selectedText == null) return Messages.showErrorDialog(noSelectedText, titleWindow)
        if (fileName == null) return Messages.showErrorDialog(noFileName, titleWindow)

        val text: String = primaryCaret.selectedText ?: ""
        val id: Int = fileName.hashCode() + start + end + text.hashCode()

        val settings: ProjectSettingsState = ProjectSettingsState().getInstance(project)

        // ID is not unique and is compiled from data, if ID is the same = duplicate
        if (settings.areas.contains(id)) return

        settings.areas = settings.areas + arrayOf(
            "${Area.ID.content}$id",
            "${Area.FILE.content}$fileName",
            "${Area.LINES.content}$start${AreaLine.SEPARATOR.content}$end",
            "${Area.CONTENT.content}${text.trim()}"
        )

        primaryCaret.removeSelection()
    }
}