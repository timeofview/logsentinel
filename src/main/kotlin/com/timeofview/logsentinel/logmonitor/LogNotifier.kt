package com.timeofview.logsentinel.logmonitor

import com.timeofview.logsentinel.config.LogSentinelSettingsState
import com.timeofview.logsentinel.services.NotificationService

class LogNotifier {

    private val settingsState = LogSentinelSettingsState.instance
    private val notificationService = NotificationService()

    // Metodo per inviare una notifica quando la stringa specifica è rilevata nel log.
    fun notify(logLine: String) {
        if (logLine.contains("Started MfwApplication")) {
//            if (settingsState.isPopupNotificationEnabled) {
//                // Mostra una notifica popup se abilitato nelle impostazioni.
//                notificationService.showPopupNotification("LogSentinel Alert: $logLine")
//            }
//            if (settingsState.isSoundNotificationEnabled) {
//                // Riproduci un suono di notifica se abilitato nelle impostazioni.
//                notificationService.playSoundNotification()
//            }
        }
    }
}
