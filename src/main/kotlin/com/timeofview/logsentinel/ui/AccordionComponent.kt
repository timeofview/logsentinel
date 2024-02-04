package com.timeofview.logsentinel.ui

import java.awt.BorderLayout
import javax.swing.JButton
import javax.swing.JComponent
import javax.swing.JPanel


open class AccordionComponent(content: JComponent = JPanel()) : JPanel() {
    private val OPEN = "^"
    private val CLOSE = "ˇ"

    init {
        this.setLayout(BorderLayout())

        val toggleButton = JButton(CLOSE)
        val contentPanel = JPanel()
        contentPanel.add(content)
        contentPanel.isVisible = false // Inizialmente nascosto

        toggleButton.addActionListener {
            contentPanel.isVisible = !contentPanel.isVisible
            if (contentPanel.isVisible) {
                toggleButton.text = OPEN
            } else {
                toggleButton.text = CLOSE
            }
            this.revalidate()
            this.repaint()
        }

        this.add(toggleButton, BorderLayout.NORTH)
        this.add(contentPanel, BorderLayout.CENTER)

    }
}