package com.saravanamarriagebureau.data.repository

import android.content.Context
import android.net.Uri
import com.saravanamarriagebureau.data.api.SmbApi
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.HttpException
import java.io.File
import java.io.IOException
import javax.inject.Inject

class ImageRepository @Inject constructor(
    private val api: SmbApi,
    @ApplicationContext private val context: Context
) {

    suspend fun uploadImage(uri: Uri): String =
        withContext(Dispatchers.IO) {

            val tempFile = File.createTempFile(
                "upload_",
                ".jpg",
                context.cacheDir
            )

            try {
                val input = context.contentResolver
                    .openInputStream(uri)
                    ?: throw IOException("Cannot open image")

                input.use { source ->
                    tempFile.outputStream().use { target ->
                        source.copyTo(target)
                    }
                }

                val requestBody = tempFile.asRequestBody(
                    "image/jpeg".toMediaType()
                )

                val imagePart = MultipartBody.Part.createFormData(
                    name = "image",
                    filename = "image.jpg",
                    body = requestBody
                )

                val response = api.uploadImage(imagePart)

                if (response.isSuccessful) {
                    response.body()?.message
                        ?: "Image uploaded successfully"
                } else {
                    throw HttpException(response)
                }
            } finally {
                tempFile.delete()
            }
        }
}