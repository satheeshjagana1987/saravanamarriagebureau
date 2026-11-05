package com.saravanamarriagebureau.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Log
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

object ImageCompressor {

    fun compress(
        context: Context,
        uri: Uri,
        maxDimension: Int = 1600,
        quality: Int = 75
    ): File {

        val resolver = context.contentResolver

        Log.d("IMAGE_COMPRESS", "Starting compression: $uri")

        // STEP 1: Read image dimensions

        val bounds = BitmapFactory.Options().apply {
            inJustDecodeBounds = true
        }

        try {

            resolver.openInputStream(uri)?.use { input ->

                BitmapFactory.decodeStream(
                    input,
                    null,
                    bounds
                )

            } ?: throw IOException("Cannot open image for bounds")

        } catch (e: Exception) {

            Log.e("IMAGE_COMPRESS", "STEP 1 FAILED", e)
            throw e
        }

        Log.d(
            "IMAGE_COMPRESS",
            "Dimensions: ${bounds.outWidth} x ${bounds.outHeight}"
        )

        if (bounds.outWidth <= 0 || bounds.outHeight <= 0) {
            throw IOException("Invalid image dimensions")
        }

        // STEP 2: Calculate sample size

        val sampleSize = calculateSampleSize(
            width = bounds.outWidth,
            height = bounds.outHeight,
            maxDimension = maxDimension
        )

        // STEP 3: Decode bitmap

        val options = BitmapFactory.Options().apply {
            inSampleSize = sampleSize
            inPreferredConfig = Bitmap.Config.ARGB_8888
        }

        val bitmap = try {

            resolver.openInputStream(uri)?.use { input ->

                BitmapFactory.decodeStream(
                    input,
                    null,
                    options
                )

            } ?: throw IOException("Cannot open image for decoding")

        } catch (e: Exception) {

            Log.e("IMAGE_COMPRESS", "STEP 3 FAILED", e)
            throw e
        } ?: throw IOException("Bitmap decoding returned null")

        // STEP 4: Save compressed file

        val outputFile = File.createTempFile(
            "compressed_",
            ".jpg",
            context.cacheDir
        )

        try {

            FileOutputStream(outputFile).use { output ->

                val success = bitmap.compress(
                    Bitmap.CompressFormat.JPEG,
                    quality.coerceIn(1, 100),
                    output
                )

                if (!success) {
                    throw IOException("Compression failed")
                }

                output.flush()
            }

            Log.d(
                "IMAGE_COMPRESS",
                "Compression SUCCESS: ${outputFile.length()} bytes"
            )

            return outputFile

        } catch (e: Exception) {

            outputFile.delete()

            Log.e("IMAGE_COMPRESS", "STEP 4 FAILED", e)

            throw e

        } finally {

            bitmap.recycle()
        }
    }

    private fun calculateSampleSize(
        width: Int,
        height: Int,
        maxDimension: Int
    ): Int {

        var sampleSize = 1

        while (
            width / (sampleSize * 2) >= maxDimension ||
            height / (sampleSize * 2) >= maxDimension
        ) {
            sampleSize *= 2
        }

        return sampleSize
    }
}