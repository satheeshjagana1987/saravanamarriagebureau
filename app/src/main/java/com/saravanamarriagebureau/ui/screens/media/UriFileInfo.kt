package com.saravanamarriagebureau.ui.screens.media

import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns
import timber.log.Timber

data class UriFileInfo(
    val name: String?,
    val size: Long?
)

fun Uri.getFileInfo(context: Context): UriFileInfo {

    var name: String? = null
    var size: Long? = null

    try {
        context.contentResolver.query(
            this,
            arrayOf(
                OpenableColumns.DISPLAY_NAME,
                OpenableColumns.SIZE
            ),
            null,
            null,
            null
        )?.use { cursor ->

            if (cursor.moveToFirst()) {

                val nameIndex = cursor.getColumnIndex(
                    OpenableColumns.DISPLAY_NAME
                )

                val sizeIndex = cursor.getColumnIndex(
                    OpenableColumns.SIZE
                )

                if (nameIndex != -1) {
                    name = cursor.getString(nameIndex)
                }

                if (sizeIndex != -1 && !cursor.isNull(sizeIndex)) {
                    size = cursor.getLong(sizeIndex)
                }
            }
        }
    } catch (e: Exception) {
        Timber.e(e, "Unable to get URI file information")
    }

    // Fallback: Read actual stream size if provider returns null
    if (size == null) {
        try {
            context.contentResolver.openAssetFileDescriptor(this, "r")
                ?.use { descriptor ->
                    size = descriptor.length.takeIf { it >= 0 }
                }
        } catch (e: Exception) {
            Timber.e(e, "Unable to get URI file size")
        }
    }

    return UriFileInfo(
        name = name,
        size = size
    )
}
