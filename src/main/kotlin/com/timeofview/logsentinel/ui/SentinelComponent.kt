package com.timeofview.logsentinel.ui

import com.timeofview.logsentinel.model.Sentinel
import java.awt.GridLayout
import javax.swing.JCheckBox
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.JTextField

class SentinelComponent (panel: JPanel = JPanel(GridLayout(0, 2))) : AccordionComponent(panel) {

    private val triggerStringField = JTextField(20)
    private val messageStringField = JTextField(20)
    private val isEnabledPopupCheckbox = JCheckBox("Enable Popup Notifications")
    private val isEnabledSoundCheckbox = JCheckBox("Enable Sound Notifications")
    private val audioPathSelectorComponent = AudioPathSelectorComponent()

    init {
        panel.add(JLabel("Trigger String:"))
        panel.add(triggerStringField)

        panel.add(JLabel("Message String:"))
        panel.add(messageStringField)

        panel.add(isEnabledPopupCheckbox)
        panel.add(JLabel("")) // Placeholder

        panel.add(isEnabledSoundCheckbox)
        panel.add(JLabel("")) // Placeholder

        panel.add(audioPathSelectorComponent)

    }

    fun getSentinelData(): Sentinel {
        return Sentinel(
            triggerString = triggerStringField.text,
            messageString = messageStringField.text,
            isEnabledPopup = isEnabledPopupCheckbox.isSelected,
            isEnabledSound = isEnabledSoundCheckbox.isSelected,
            audioPath = audioPathSelectorComponent.getAudio()
        )
    }

    fun setSentinelData(sentinel: Sentinel) {
        triggerStringField.text = sentinel.triggerString
        messageStringField.text = sentinel.messageString
        isEnabledPopupCheckbox.isSelected = sentinel.isEnabledPopup
        isEnabledSoundCheckbox.isSelected = sentinel.isEnabledSound
        audioPathSelectorComponent.selectAudio(sentinel.audioPath)
    }
}
