package com.timeofview.logsentinel.ui

import com.timeofview.logsentinel.model.AUDIO_FILE
import com.timeofview.logsentinel.player.SoundPlayer
import javax.swing.JButton
import javax.swing.JComboBox
import javax.swing.JLabel
import javax.swing.JPanel

class AudioPathSelectorComponent : JPanel() {

    val panel = this
    private val audioPathComboBox = JComboBox<String>()
    private val addButton = FileChooserComponent("Add", { addAudioFile(it) })
    private val playButton = JButton("Play")

    init {
        panel.add(JLabel("Audio Path:"))
        panel.add(audioPathComboBox)
//        panel.add(addButton)
        panel.add(playButton)

        loadAudioFiles()
        addButton.isEnabled = false
        playButton.addActionListener {
            val selectedAudioPath = audioPathComboBox.selectedItem as String?
            selectedAudioPath?.let { playAudioFile(it) }
        }
    }

    public fun getAudio(): String {
        return audioPathComboBox.selectedItem?.toString() ?: ""
    }

    public fun selectAudio(audio: String) {
        if (audio == null) {
            return
        }
        for (i in 0..audioPathComboBox.itemCount) {
            val item = audioPathComboBox.getItemAt(i)
            if (item.toString() == audio) {
                audioPathComboBox.selectedItem = item
            }
        }
    }

    private fun loadAudioFiles() {
        for (audio in AUDIO_FILE.values()) {
            audioPathComboBox.addItem(audio.label)
        }
    }

    private fun addAudioFile(file: String) {
        audioPathComboBox.addItem(file)
    }

    private fun playAudioFile(audioLabel: String) {
        for (audio in AUDIO_FILE.values()) {
            if (audioLabel == audio.label) {
                val soundPlayer = SoundPlayer()
                soundPlayer.playSound(audio.path)
            }
        }

    }
}
