package com.github.mineejo.avi.interactions.writers

import com.github.mineejo.avi.enums.Dot
import com.github.mineejo.avi.enums.DotLine
import com.github.mineejo.avi.settings.ProjectSettingsState
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.actionSystem.PlatformDataKeys
import com.intellij.openapi.editor.Caret
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.Messages

class DotWriter(e: AnActionEvent) {
    private val titleWindow: String = "Adaptive Visual Interaction (AVI)"
    private val noSelectedText: String = "An invalid code area is selected!"
    private val noFileName = "Unable to define the file and save the selection!"

    private val editor: Editor = e.getRequiredData(CommonDataKeys.EDITOR)
    private val project: Project = e.getRequiredData(CommonDataKeys.PROJECT)
    private val fileName: String? = e.getData(PlatformDataKeys.VIRTUAL_FILE)?.name

    private val primaryCaret: Caret = editor.caretModel.primaryCaret
    private val start: Int = primaryCaret.selectionStart
    private val end: Int = primaryCaret.selectionEnd

    fun saveDots() {
        if (primaryCaret.selectedText == null) return Messages.showErrorDialog(noSelectedText, titleWindow)
        if (fileName == null) return Messages.showErrorDialog(noFileName, titleWindow)

        val text: String = primaryCaret.selectedText ?: ""
        val id: Int = fileName.hashCode() + start + end + text.hashCode()

        val settings: ProjectSettingsState = ProjectSettingsState().getInstance(project)

        // ID is not unique and is compiled from data, if ID is the same = duplicate
        if (settings.dots.contains(id)) return

        settings.dots = settings.dots + arrayOf(
            "${Dot.ID.content}$id",
            "${Dot.FILE.content}$fileName",
            "${Dot.LINES.content}$start${DotLine.SEPARATOR.content}$end",
            "${Dot.CONTENT.content}${text.trim()}"
        )

        primaryCaret.removeSelection()
    }
}