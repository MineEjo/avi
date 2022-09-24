package com.github.mineejo.avi.events

import com.github.mineejo.avi.area.VisualAreaController
import com.intellij.openapi.project.Project
import com.intellij.openapi.vcs.changes.shelf.ShelveChangesManager.PostStartupActivity

class Initialization : PostStartupActivity() {
	override fun runActivity(project: Project) {
		println("Plugin loaded after the project :D")

		VisualAreaController().validate(project)
	}
}
