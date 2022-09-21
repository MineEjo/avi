package com.github.mineejo.avi.area

import com.github.mineejo.avi.settings.ProjectSettingsState
import com.intellij.openapi.project.Project

class VisualAreasChecker {
    private val areaFileIndex: Int = 1
    private val areaLinesIndex: Int = 2
    private val areaContentIndex: Int = 3

    fun validate(project: Project) {
        val settings: ProjectSettingsState = ProjectSettingsState().getInstance(project)
        val areas: MutableList<String> = mutableListOf()

        // Checks keys and removes corrupted data.
        for ((index, item) in settings.areas.withIndex()) {
            when {
                (item.startsWith(VisualArea.ID.content)) -> continue
                (item.startsWith(VisualArea.FILE.content)) -> continue
                (item.startsWith(VisualArea.LINES.content)) -> continue
                (item.startsWith(VisualArea.CONTENT.content)) -> continue
            }

            settings.areas.removeAt(index)
        }

        // Collects uncorrupted areas
        for ((index, areaId) in settings.areas.withIndex()) {
            if (!areaId.startsWith(VisualArea.ID.content)) continue

            val areaFile = settings.areas.getOrNull(index + areaFileIndex) ?: ""
            val areaLines = settings.areas.getOrNull(index + areaLinesIndex) ?: ""
            val areaContent = settings.areas.getOrNull(index + areaContentIndex) ?: ""

            when {
                (!areaFile.startsWith(VisualArea.FILE.content)) -> continue
                (!areaLines.startsWith(VisualArea.LINES.content)) -> continue
                (!areaContent.startsWith(VisualArea.CONTENT.content)) -> continue
            }

            areas.add(areaId)
            areas.add(areaFile)
            areas.add(areaLines)
            areas.add(areaContent)
        }

        if (settings.areas != areas) settings.areas = areas
    }
}