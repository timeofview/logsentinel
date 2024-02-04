package com.timeofview.logsentinel.ui

import com.timeofview.logsentinel.model.Sentinel
import javax.swing.Box
import javax.swing.BoxLayout
import javax.swing.JButton
import javax.swing.JPanel
import javax.swing.border.EmptyBorder

class LogSentinelSettingsComponent {
    val panel = JPanel()

    val logFilePathField = FileChooserComponent("Choose Log File")
    val sentinelComponents: MutableList<SentinelComponent> = mutableListOf()
    private val addNewSentinel = JButton("+")

    init {
        panel.layout = BoxLayout(panel, BoxLayout.Y_AXIS)
        panel.border = EmptyBorder(0, 0, 0, 0)
        panel.add(logFilePathField)
        for (sentinelComponent in sentinelComponents) {
            addNewSentinelToPanel(sentinelComponent)
        }
        panel.add(addNewSentinel)

        addNewSentinel.addActionListener{
            val sentinelComponent = SentinelComponent()
            sentinelComponents+= sentinelComponent
            panel.remove(addNewSentinel)
            addNewSentinelToPanel(sentinelComponent)
            panel.add(addNewSentinel)
        }
    }

    private fun addNewSentinelToPanel(sentinelComponent: SentinelComponent) {
        panel.add(sentinelComponent)
        panel.add(Box.createVerticalStrut(5))
    }

    fun setData(logFilePath: String, sentinels: List<Sentinel>) {
        logFilePathField.setSelectedFilePath(logFilePath)
        for (sentinel in sentinels) {
            val element = SentinelComponent()
            element.setSentinelData(sentinel)
            this.sentinelComponents.add(element)
        }
    }

}
