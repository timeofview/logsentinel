package com.timeofview.logsentinel.services

import com.intellij.notification.NotificationGroupManager
import com.intellij.notification.NotificationType
import com.intellij.openapi.project.ProjectManager

class NotificationService {


    fun showPopupNotification(message: String) {
        val notificationGroup = NotificationGroupManager.getInstance().getNotificationGroup("LogSentinelNotificationGroup")

        val project = ProjectManager.getInstance().defaultProject

        notificationGroup.createNotification(message, NotificationType.INFORMATION)
                .notify(project)
    }

    // Metodo per riprodurre un suono di notifica.
    fun playSoundNotification() {
        // Qui dovrai implementare la logica per riprodurre un suono.
        // Questo potrebbe essere semplice come riprodurre un file audio predefinito.
        // Ad esempio, puoi usare la classe Toolkit di Java per riprodurre un suono di sistema.
    }
}
