package com.saravanamarriagebureau.ui.screens.media

import com.saravanamarriagebureau.data.model.MediaUploadInitiatedResponse

sealed class MediaUploadInitiatedUIState {

    data object Idle : MediaUploadInitiatedUIState()

    data object Loading : MediaUploadInitiatedUIState()

    data class Success(
        val data: MediaUploadInitiatedResponse,
        val message: String? = null
    ) : MediaUploadInitiatedUIState()

    data class Error(
        val message: String
    ) : MediaUploadInitiatedUIState()

}