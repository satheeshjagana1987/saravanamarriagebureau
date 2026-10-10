package com.saravanamarriagebureau.ui.screens.profile

import android.net.Uri

data class ImageUiState(
    val imageUri: Uri? = null,
    val isLoading: Boolean = false,
    val message: String? = null,
    val error: String? = null
)