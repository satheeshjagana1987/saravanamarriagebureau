package com.saravanamarriagebureau.data.repository

import android.content.Context
import android.net.Uri
import com.saravanamarriagebureau.data.api.SmbApi
import com.saravanamarriagebureau.data.model.DeleteMediaResponse
import com.saravanamarriagebureau.util.ApiResult
import com.saravanamarriagebureau.util.AppPreferences
import com.saravanamarriagebureau.util.ImageCompressor
import com.saravanamarriagebureau.util.checkImageUri
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
import timber.log.Timber
import java.io.File
import java.io.IOException
import javax.inject.Inject

class ImageRepository @Inject constructor(
    private val api: SmbApi,
    private val appPreferences: AppPreferences,
    @ApplicationContext private val context: Context
) {

    suspend fun uploadImage(
        uri: Uri,
        mediaType: String
    ): String = withContext(Dispatchers.IO) {
        Timber.e("uriRepositoryuploadImageuri====>$uri")
        Timber.e("uriRepositoryuploadmediaType====>$mediaType")
        var compressedFile: File? = null
        checkImageUri(context, uri)
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

//            compressedFile = ImageCompressor.compress(
//                context = context,
//                uri = uri,
//                maxDimension = 1600,
//                quality = 75
//            )

//            if (!compressedFile.exists() || compressedFile.length() == 0L) {
//                throw IOException("Compressed image is empty")
//            }

            val requestBody = tempFile.asRequestBody(
                "image/jpeg".toMediaType()
            )

            val imagePart = MultipartBody.Part.createFormData(
                name = "media",
                filename = "image.jpg",
                body = requestBody
            )

            val response = api.uploadImage(
                media = imagePart,
                mediaType = createTextPart(mediaType)
            )

            if (response.isSuccessful) {

                val body = response.body()
                    ?: throw IOException("Empty upload response")

                body.data.mediaId
                    ?: throw IOException("Media ID missing in response")

            } else {
                throw HttpException(response)
            }

        } finally {
            tempFile?.delete()
//            compressedFile?.delete()
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

    suspend fun uploadVideo(
        context: Context,
        uri: Uri
    ) = withContext(Dispatchers.IO) {
        val part = uriToMultipart(context, uri)
        val response =
            api.uploadImage(media = part, mediaType = createTextPart("FAMILY_VIDEO"))

        if (response.isSuccessful) {
            val body = response.body()
                ?: throw IOException("Empty upload response")

            body.message ?: "Video uploaded successfully"
        } else {
            throw HttpException(response)
        }
    }

    fun uriToMultipart(
        context: Context,
        uri: Uri,
        partName: String = "media"
    ): MultipartBody.Part {
        val resolver = context.contentResolver

        val mimeType = resolver.getType(uri)
            ?: "application/octet-stream"

        val extension = when {
            mimeType == "video/mp4" -> ".mp4"
            mimeType == "audio/mp4" -> ".m4a"
            mimeType == "audio/3gpp" -> ".3gp"
            mimeType == "audio/mpeg" -> ".mp3"
            mimeType == "audio/aac" -> ".aac"
            mimeType == "audio/ogg" -> ".ogg"
            mimeType == "audio/wav" -> ".wav"
            else -> ".bin"
        }

        val tempFile = File.createTempFile(
            "upload_",
            extension,
            context.cacheDir
        )

        try {
            val input = resolver.openInputStream(uri)
                ?: throw IOException("Cannot open selected media")

            input.use { source ->
                tempFile.outputStream().use { destination ->
                    source.copyTo(destination)
                }
            }

            val requestBody = tempFile.asRequestBody(
                mimeType.toMediaType()
            )

            return MultipartBody.Part.createFormData(
                partName,
                tempFile.name,
                requestBody
            )
        } catch (e: Exception) {
            tempFile.delete()
            throw e
        }
    }

    suspend fun uploadVoiceNote(file: File) =
        withContext(Dispatchers.IO) {
            val requestBody = file.asRequestBody(
                "audio/mp3".toMediaType()
            )

            val part = MultipartBody.Part.createFormData(
                "media",
                file.name,
                requestBody
            )

            val response =
                api.uploadImage(media = part, mediaType = createTextPart("BRIDE_GROOM_VOICE_NOTE"))

            if (response.isSuccessful) {
                val body = response.body()
                    ?: throw IOException("Empty upload response")

                body.message ?: "Voice uploaded successfully"
            } else {
                throw HttpException(response)
            }
        }
}