package com.saravanamarriagebureau.ui.screens.media

import com.saravanamarriagebureau.data.api.SmbApi
import com.saravanamarriagebureau.data.model.MediaUploadInitiatedRequest
import com.saravanamarriagebureau.data.model.MediaUploadInitiatedResponse
import com.saravanamarriagebureau.util.ApiResult
import com.saravanamarriagebureau.util.safeApiCall
import javax.inject.Inject

class MediaUploadInitiatedRepository @Inject constructor(
    private val api: SmbApi
) {

    suspend fun mediaUploadInitiated(
         mediaType: String,
         fileSize: Long,
         originalFileName: String,
    ): ApiResult<MediaUploadInitiatedResponse> {
        return safeApiCall {
            api.mediaUploadInitiated(
                MediaUploadInitiatedRequest(
                    mediaType = mediaType,
                    contentType = "application/json",
                    fileSize = fileSize,
                    originalFileName = originalFileName
                )
            )
        }
    }
}