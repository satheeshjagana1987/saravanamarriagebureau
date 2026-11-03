package com.saravanamarriagebureau.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.IOException
import androidx.core.graphics.scale

object ImageCompressor {

    suspend fun compress(
        context: Context,
        uri: Uri,
        maxDimension: Int = 1600,
        quality: Int = 75
    ): File = withContext(Dispatchers.IO) {

        val bounds = BitmapFactory.Options().apply {
            inJustDecodeBounds = true
        }

        context.contentResolver.openInputStream(uri)?.use {
            BitmapFactory.decodeStream(it, null, bounds)
        } ?: throw IOException("Cannot open image")

        if (bounds.outWidth <= 0 || bounds.outHeight <= 0) {
            throw IOException("Invalid image")
        }

        var sampleSize = 1
        while (
            bounds.outWidth / (sampleSize * 2) >= maxDimension &&
            bounds.outHeight / (sampleSize * 2) >= maxDimension
        ) {
            sampleSize *= 2
        }

        val options = BitmapFactory.Options().apply {
            inSampleSize = sampleSize
            inPreferredConfig = Bitmap.Config.RGB_565
        }

        val bitmap = context.contentResolver.openInputStream(uri)?.use {
            BitmapFactory.decodeStream(it, null, options)
        } ?: throw IOException("Unable to decode image")

        try {
            val scale = minOf(
                1f,
                maxDimension.toFloat() / bitmap.width,
                maxDimension.toFloat() / bitmap.height
            )

            val resized = if (scale < 1f) {
                bitmap.scale(
                    (bitmap.width * scale).toInt().coerceAtLeast(1),
                    (bitmap.height * scale).toInt().coerceAtLeast(1)
                )
            } else bitmap

            try {
                val output = File.createTempFile(
                    "compressed_",
                    ".jpg",
                    context.cacheDir
                )

                try {
                    output.outputStream().buffered().use {
                        if (!resized.compress(
                                Bitmap.CompressFormat.JPEG,
                                quality.coerceIn(1, 100),
                                it
                            )
                        ) {
                            throw IOException("Compression failed")
                        }
                    }
                    output
                } catch (e: Exception) {
                    output.delete()
                    throw e
                }
            } finally {
                if (resized !== bitmap) resized.recycle()
            }
        } finally {
            bitmap.recycle()
        }
    }
}