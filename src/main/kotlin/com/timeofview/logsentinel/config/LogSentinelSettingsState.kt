package com.timeofview.logsentinel.config

import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.ServiceManager
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.util.xmlb.XmlSerializerUtil
import com.timeofview.logsentinel.model.Sentinel

// Usa @State per definire il nome dello stato e dove dovrebbe essere salvato.
@State(
        name = "com.timeofview.logsentinel.LogSentinelSettingsState",
        storages = [Storage("LogSentinelSettings.xml")]
)
class LogSentinelSettingsState : PersistentStateComponent<LogSentinelSettingsState> {

    var logFilePath: String = ""
    var sentinels: List<Sentinel> = listOf()

    override fun getState(): LogSentinelSettingsState {
        return this
    }

    override fun loadState(state: LogSentinelSettingsState) {
        XmlSerializerUtil.copyBean(state, this)
    }

    companion object {
        val instance: LogSentinelSettingsState
            get() = ServiceManager.getService(LogSentinelSettingsState::class.java)
    }
}
