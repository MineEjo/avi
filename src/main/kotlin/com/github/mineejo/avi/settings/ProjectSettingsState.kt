package com.github.mineejo.avi.settings

import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.openapi.project.Project
import com.intellij.util.xmlb.XmlSerializerUtil


/**
 * Supports storing the application settings in a persistent way.
 * The {@link State} and {@link Storage} annotations define the name of the data and the file name where
 * these persistent application settings are stored.
 */
@State(
	name = "org.mineejo.avi.settings.ProjectSettingsState",
	storages = [Storage("avi.xml")]
)
class ProjectSettingsState : PersistentStateComponent<ProjectSettingsState> {
	var areas: MutableList<String> = mutableListOf()

	fun getInstance(project: Project): ProjectSettingsState {
		return project.getService(ProjectSettingsState::class.java)
	}

	override fun getState(): ProjectSettingsState {
		return this
	}

	override fun loadState(state: ProjectSettingsState) {
		XmlSerializerUtil.copyBean(state, this)
	}
}
