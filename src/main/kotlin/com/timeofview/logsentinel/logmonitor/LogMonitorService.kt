package com.timeofview.logsentinel.logmonitor

import com.intellij.openapi.Disposable
import com.intellij.openapi.components.Service
import com.intellij.openapi.project.Project

@Service(Service.Level.PROJECT)
class LogMonitorService(val project: Project) : Disposable {
    private val logListener = LogListener()

    fun startMonitoring() {
        logListener.startMonitoring()
    }

    override fun dispose() {
        logListener.stopMonitoring()
    }


}
