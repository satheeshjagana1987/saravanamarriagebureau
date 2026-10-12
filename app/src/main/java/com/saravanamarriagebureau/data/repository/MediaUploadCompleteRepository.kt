package com.saravanamarriagebureau.data.repository

import com.saravanamarriagebureau.data.api.SmbApi
import com.saravanamarriagebureau.data.model.MediaUploadCompleteResponse
import com.saravanamarriagebureau.util.ApiResult
import com.saravanamarriagebureau.util.safeApiCall
import javax.inject.Inject

class MediaUploadCompleteRepository @Inject constructor(
    private val api: SmbApi,
) {

    suspend fun mediaUploadComplete(
        mediaId: String
    ): ApiResult<MediaUploadCompleteResponse> {
        return safeApiCall {
            api.mediaUploadCompleted(mediaId = mediaId)
        }
    }
}