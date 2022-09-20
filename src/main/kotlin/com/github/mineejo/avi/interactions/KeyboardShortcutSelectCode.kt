package com.github.mineejo.avi.interactions

import com.github.mineejo.avi.interactions.writers.DotsWriter
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent


class KeyboardShortcutSelectCode : AnAction() {
    override fun actionPerformed(e: AnActionEvent) = DotsWriter(e).saveDots()
}