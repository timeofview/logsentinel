package com.timeofview.logsentinel.config

import com.intellij.openapi.fileChooser.FileChooser
import com.intellij.openapi.fileChooser.FileChooserDescriptor
import com.intellij.openapi.vfs.VirtualFile
import java.io.File
import javax.swing.JButton
import javax.swing.JComboBox
import javax.swing.JLabel
import javax.swing.JPanel

class AudioPathSelectorComponent {

    val panel = JPanel()
    private val audioPathComboBox = JComboBox<String>()
    private val addButton = JButton("Add")
    private val playButton = JButton("Play")

    init {
        panel.add(JLabel("Audio Path:"))
        panel.add(audioPathComboBox)
        panel.add(addButton)
        panel.add(playButton)

        loadAudioFiles()

        addButton.addActionListener {
            val fileChooserDescriptor = FileChooserDescriptor(true, false, false, false, true, false)
            fileChooserDescriptor.description = "Select Audio File"
            fileChooserDescriptor.title = "Add Audio File"
            val file = FileChooser.chooseFile(fileChooserDescriptor, null, null)
            file?.let { addAudioFile(it) }
        }

        playButton.addActionListener {
            val selectedAudioPath = audioPathComboBox.selectedItem as String?
            selectedAudioPath?.let { playAudioFile(it) }
        }
    }

    private fun loadAudioFiles() {
        // Carica l'elenco dei file audio dalle risorse e aggiungili al JComboBox
        val resourceFolder = File(this::class.java.classLoader.getResource("audio")?.toURI()!!)
        resourceFolder.listFiles()?.forEach {
            if (it.isFile) {
                audioPathComboBox.addItem(it.absolutePath)
            }
        }
    }

    private fun addAudioFile(file: VirtualFile) {
        // Aggiungi il file selezionato al JComboBox
        audioPathComboBox.addItem(file.path)
    }

    private fun playAudioFile(audioPath: String) {
        // Implementa la logica per riprodurre il file audio
    }
}
