package com.saravanamarriagebureau.ui.screens.media

import com.saravanamarriagebureau.data.model.MediaUploadCompleteResponse
import com.saravanamarriagebureau.data.model.MediaUploadInitiatedResponse

sealed class MediaUploadCompleteUIState {

    data object Idle : MediaUploadCompleteUIState()

    data object Loading : MediaUploadCompleteUIState()

    data class Success(
        val data: MediaUploadCompleteResponse,
        val message: String? = null
    ) : MediaUploadCompleteUIState()

    data class Error(
        val message: String
    ) : MediaUploadCompleteUIState()
}