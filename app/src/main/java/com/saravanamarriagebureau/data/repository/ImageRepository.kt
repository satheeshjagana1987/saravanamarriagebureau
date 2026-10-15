package com.saravanamarriagebureau.data.repository

import android.content.Context
import android.net.Uri
import com.saravanamarriagebureau.data.api.SmbApi
import com.saravanamarriagebureau.data.model.DeleteMediaResponse
import com.saravanamarriagebureau.data.model.ForgetPasswordRequest
import com.saravanamarriagebureau.data.model.ForgetPasswordResponse
import com.saravanamarriagebureau.util.ApiResult
import com.saravanamarriagebureau.util.AppPreferences
import com.saravanamarriagebureau.util.safeApiCall
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.HttpException
import java.io.File
import java.io.IOException
import javax.inject.Inject

class ImageRepository @Inject constructor(
    private val api: SmbApi,
    private val appPreferences: AppPreferences,
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
                    name = "media",
                    filename = "image.jpg",
                    body = requestBody
                )

                val response =
                    api.uploadImage(media = imagePart, mediaType = createTextPart("PRIMARY_PHOTO"))

                if (response.isSuccessful) {
                    val body = response.body()
                        ?: throw IOException("Empty upload response")

                    val mediaId = body.data.mediaId
                        ?: throw IOException("Media ID missing in response")

                    appPreferences.saveMediaId(mediaId)

                    body.message ?: "Image uploaded successfully"
                } else {
                    throw HttpException(response)
                }

            } finally {
                tempFile.delete()
            }
        }

    fun createTextPart(value: String): RequestBody {
        return value.toRequestBody("text/plain".toMediaTypeOrNull())
    }

    suspend fun deleteImage(
        mediaId: String,
    ): ApiResult<DeleteMediaResponse> {
        return safeApiCall {
            api.deleteImage(
                mediaId = mediaId
            )
        }
    }
}