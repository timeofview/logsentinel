package com.timeofview.logsentinel.config

import com.intellij.openapi.fileChooser.FileChooser
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory
import com.timeofview.logsentinel.player.SoundPlayer
import com.timeofview.logsentinel.ui.FileChooserComponent
import javax.swing.*

class LogSentinelSettingsComponent {
    val panel = JPanel()

    private val logFilePathField = FileChooserComponent("Choose Log File")
    private val chooseLogFileButton = JButton("Choose Log File")

    init {
        panel.layout = BoxLayout(panel, BoxLayout.Y_AXIS)
        val firstRow = JPanel()
        firstRow.add(logFilePathField)

    }


}
