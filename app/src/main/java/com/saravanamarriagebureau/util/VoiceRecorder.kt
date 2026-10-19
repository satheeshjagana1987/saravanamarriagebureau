package com.saravanamarriagebureau.util

import android.content.Context
import android.media.MediaRecorder
import java.io.File

class VoiceRecorder(context: Context) {

    private val outputFile = File(
        context.cacheDir,
        "voice_${System.currentTimeMillis()}.m4a"
    )

    private var recorder: MediaRecorder? = null

    fun start() {
        val mediaRecorder = MediaRecorder()

        mediaRecorder.setAudioSource(
            MediaRecorder.AudioSource.MIC
        )
        mediaRecorder.setOutputFormat(
            MediaRecorder.OutputFormat.MPEG_4
        )
        mediaRecorder.setAudioEncoder(
            MediaRecorder.AudioEncoder.AAC
        )
        mediaRecorder.setOutputFile(outputFile.absolutePath)

        mediaRecorder.prepare()
        mediaRecorder.start()

        recorder = mediaRecorder
    }

    fun stop(): File {
        val current = recorder
            ?: error("Recording has not started")

        try {
            current.stop()
        } finally {
            current.release()
            recorder = null
        }

        return outputFile
    }

    fun cancel() {
        recorder?.let { current ->
            try {
                current.stop()
            } catch (_: RuntimeException) {
                // Recording may be too short to finalize.
            } finally {
                current.release()
                recorder = null
                outputFile.delete()
            }
        }
    }
}