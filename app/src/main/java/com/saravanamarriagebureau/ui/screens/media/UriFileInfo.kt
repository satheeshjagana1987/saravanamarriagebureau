package com.saravanamarriagebureau.ui.screens.media

import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns

data class UriFileInfo(
    val name: String?,
    val size: Long?
)

fun Uri.getFileInfo(context: Context): UriFileInfo {
    var name: String? = null
    var size: Long? = null

    context.contentResolver.query(
        this,
        arrayOf(OpenableColumns.DISPLAY_NAME, OpenableColumns.SIZE),
        null, null, null
    )?.use { cursor ->
        if (cursor.moveToFirst()) {
            val nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
            val sizeIndex = cursor.getColumnIndex(OpenableColumns.SIZE)

            if (nameIndex != -1) name = cursor.getString(nameIndex)
            // SIZE can be null if unknown; guard the column read
            if (sizeIndex != -1 && !cursor.isNull(sizeIndex)) {
                size = cursor.getLong(sizeIndex)
            }
        }
    }

    return UriFileInfo(name, size)
}
