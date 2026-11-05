package com.saravanamarriagebureau.util

import android.content.Context
import android.net.Uri
import android.util.Log

fun checkImageUri(context: Context, uri: Uri) {

    val resolver = context.contentResolver

    Log.d("IMAGE_DEBUG", "URI: $uri")
    Log.d("IMAGE_DEBUG", "Scheme: ${uri.scheme}")
    Log.d("IMAGE_DEBUG", "Authority: ${uri.authority}")

    try {

        resolver.openInputStream(uri)?.use { input ->

            val firstBytes = ByteArray(10)
            val bytesRead = input.read(firstBytes)

            Log.d(
                "IMAGE_DEBUG",
                "SUCCESS: URI opened. Bytes read: $bytesRead"
            )

        } ?: Log.e(
            "IMAGE_DEBUG",
            "FAILED: openInputStream returned NULL"
        )

    } catch (e: Exception) {

        Log.e(
            "IMAGE_DEBUG",
            "URI ACCESS ERROR: ${e.message}",
            e
        )
    }
}