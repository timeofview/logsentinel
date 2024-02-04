package com.timeofview.logsentinel.logmonitor

import com.timeofview.logsentinel.config.LogSentinelSettingsState
import com.timeofview.logsentinel.services.NotificationService
import java.io.File
import java.io.RandomAccessFile
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class LogListener {
    private val settingsState = LogSentinelSettingsState.instance
    private val notificationService = NotificationService()
    private var lastKnownPosition = 0L
    private val executor = Executors.newSingleThreadScheduledExecutor()

    fun startMonitoring() {
        val logFileString = settingsState.logFilePath
        val logFile = File(logFileString)

        if (!logFile.exists()) {
            println("Log file does not exist: $logFileString")
            return
        }

        var lastModified = logFile.lastModified()
        readLastLines(logFile) {}

        executor.scheduleWithFixedDelay({
            if (logFile.lastModified() > lastModified) {
                lastModified = logFile.lastModified()
                readLastLines(logFile) { processLogLine(it) }
            }
        }, 0, 100, TimeUnit.MILLISECONDS)
    }

    private fun readLastLines(logFile: File, onNewLine: (s: String) -> Unit) {
        RandomAccessFile(logFile, "r").use { randomAccessFile ->
            randomAccessFile.seek(lastKnownPosition)

            var line: String?
            while (randomAccessFile.readLine().also { line = it } != null) {
                line?.let { onNewLine(it) }
            }

            lastKnownPosition = randomAccessFile.filePointer
        }
    }

    fun stopMonitoring() {
        executor.shutdownNow()
    }

    private fun processLogLine(logLine: String) {
        if (logLine.contains("Started MfwApplication")) {
            sendNotification(logLine)
        }
    }

    private fun sendNotification(logLine: String) {
//        if (settingsState.isPopupNotificationEnabled) {
//            notificationService.showPopupNotification(logLine)
//        }
//        if (settingsState.isSoundNotificationEnabled) {
//            notificationService.playSoundNotification()
//        }
    }
}
