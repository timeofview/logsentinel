package com.timeofview.logsentinel.logmonitor

import com.intellij.openapi.components.service
import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.ProjectActivity

class LogMonitorStartupActivity : ProjectActivity {

    override suspend fun execute(project: Project) {
        val logMonitorService = project.service<LogMonitorService>()
        logMonitorService.startMonitoring()
    }
}
