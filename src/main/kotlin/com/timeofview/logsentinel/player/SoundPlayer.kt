package com.timeofview.logsentinel.player

import javazoom.jl.player.Player

class SoundPlayer {
    fun playSound(audioPath: String) {
        try {
            val audioStream = this::class.java.classLoader.getResource("audio/$audioPath")
            val inputStream = audioStream?.openStream()
            val player = Player(inputStream)
            Thread {
                try {
                    player.play()
                } catch (e: Exception) {
                    println("Errore durante la riproduzione: ${e.localizedMessage}")
                }
            }.start()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
