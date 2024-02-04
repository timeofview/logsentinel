package com.timeofview.logsentinel.config

import com.intellij.openapi.components.service
import com.intellij.openapi.options.Configurable
import com.intellij.openapi.project.ProjectManager
import com.timeofview.logsentinel.logmonitor.LogMonitorService
import com.timeofview.logsentinel.ui.LogSentinelSettingsComponent
import javax.swing.JComponent


class LogSentinelConfigurable : Configurable {
    val project = ProjectManager.getInstance().defaultProject

    private var settingsComponent: LogSentinelSettingsComponent? = null

    private val settingsState: LogSentinelSettingsState
        get() = LogSentinelSettingsState.instance

    override fun createComponent(): JComponent? {
        settingsComponent = settingsComponent ?: LogSentinelSettingsComponent()
        return settingsComponent?.panel
    }

    override fun isModified(): Boolean {
        val component = settingsComponent ?: return false
        val currentSettings = settingsState
        val sentinelComponents = component.sentinelComponents.map { it.getSentinelData() }.toList()
        return component.logFilePathField.getSelectedFilePath() != currentSettings.logFilePath ||
                sentinelComponents != currentSettings.sentinels
    }

    override fun apply() {
        val component = settingsComponent ?: return
        val currentSettings = settingsState
        currentSettings.sentinels = mutableListOf()
        for (sentinelComponent in component.sentinelComponents) {
            currentSettings.sentinels += sentinelComponent.getSentinelData()
        }
        currentSettings.logFilePath = component.logFilePathField.getSelectedFilePath()
        val service = project.service<LogMonitorService>()
        service.startMonitoring()

    }

    override fun reset() {
        val currentSettings = settingsState
        settingsComponent?.setData(
            currentSettings.logFilePath,
            currentSettings.sentinels
        )
    }

    override fun getDisplayName(): String {
        return "LogSentinel"
    }

    override fun disposeUIResources() {
        settingsComponent = null
    }
}
