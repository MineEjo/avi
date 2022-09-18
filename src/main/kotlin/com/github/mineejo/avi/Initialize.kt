package com.github.mineejo.avi

import com.intellij.openapi.project.Project
import com.intellij.openapi.vcs.changes.shelf.ShelveChangesManager.PostStartupActivity

class Initialize : PostStartupActivity() {
    override fun runActivity(project: Project) {
        println("Plugin loaded after the project :D")
    }
}