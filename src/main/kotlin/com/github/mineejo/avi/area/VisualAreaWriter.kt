package com.github.mineejo.avi.area

import com.github.mineejo.avi.settings.ProjectSettingsState
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.actionSystem.PlatformDataKeys
import com.intellij.openapi.editor.Caret
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.Messages

class VisualAreaWriter {
    private val titleWindow: String = "Adaptive Visual Interaction (AVI)"
    private val noSelectedText: String = "An invalid code area is selected!"
    private val noFileName = "Unable to define the file and save the selection!"

    fun add(e: AnActionEvent) {
        val editor: Editor = e.getRequiredData(CommonDataKeys.EDITOR)
        val project: Project = e.getRequiredData(CommonDataKeys.PROJECT)
        val fileName: String? = e.getData(PlatformDataKeys.VIRTUAL_FILE)?.name

        val primaryCaret: Caret = editor.caretModel.primaryCaret
        val start: Int = primaryCaret.selectionStart
        val end: Int = primaryCaret.selectionEnd

        if (primaryCaret.selectedText == null) return Messages.showErrorDialog(noSelectedText, titleWindow)
        if (fileName == null) return Messages.showErrorDialog(noFileName, titleWindow)

        val text: String = primaryCaret.selectedText ?: ""
        val id: String = (fileName.hashCode() + start + end + text.hashCode()).toString()

        val settings: ProjectSettingsState = ProjectSettingsState().getInstance(project)

        // ID is not unique and is compiled from data, if ID is the same = duplicate.
        if (settings.areas.contains(id)) return

        settings.areas.add("${VisualArea.ID.content}$id")
        settings.areas.add("${VisualArea.FILE.content}$fileName")
        settings.areas.add("${VisualArea.LINES.content}$start${VisualAreaLine.SEPARATOR.content}$end")
        settings.areas.add("${VisualArea.CONTENT.content}${text.trim()}")

        primaryCaret.removeSelection()
    }
}
