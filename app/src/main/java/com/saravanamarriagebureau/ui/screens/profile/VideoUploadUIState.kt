package com.saravanamarriagebureau.ui.screens.profile

sealed interface VideoUploadUIState {
    data object Idle : VideoUploadUIState
    data object Loading : VideoUploadUIState
    data class Success(val message: String) : VideoUploadUIState
    data class Error(val message: String) : VideoUploadUIState
}