package com.timeofview.logsentinel.ui

import com.intellij.openapi.fileChooser.FileChooser
import com.intellij.openapi.fileChooser.FileChooserDescriptor
import com.intellij.openapi.vfs.VirtualFile
import javax.swing.JButton
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.JTextField

class FileChooserComponent(private val label: String, private val onClick: (path: String) -> Unit = {}) : JPanel() {

    private val panel = this
    private val textField = JTextField(20)
    private val browseButton = JButton("Browse")

    init {
        panel.add(JLabel(label))
        panel.add(textField)
        panel.add(browseButton)

        browseButton.addActionListener {
            val fileChooserDescriptor = FileChooserDescriptor(true, false, false, false, false, false)
            val selectedFile = FileChooser.chooseFile(fileChooserDescriptor, null, null)
            selectedFile?.let { updateTextFieldWithPath(it) }
            onClick(textField.text)
        }
    }

    private fun updateTextFieldWithPath(file: VirtualFile) {
        textField.text = file.path
    }

    fun getSelectedFilePath(): String = textField.text

    fun setSelectedFilePath(path: String) {
        textField.text = path
    }
}
