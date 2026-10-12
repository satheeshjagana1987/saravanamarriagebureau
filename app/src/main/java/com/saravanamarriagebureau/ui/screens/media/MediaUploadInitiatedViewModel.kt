package com.saravanamarriagebureau.ui.screens.media

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saravanamarriagebureau.util.ApiResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MediaUploadInitiatedViewModel @Inject constructor(
    private val mediaUploadInitiatedRepository: MediaUploadInitiatedRepository
): ViewModel() {

    private val _uiState =
        MutableStateFlow<MediaUploadInitiatedUIState>(MediaUploadInitiatedUIState.Idle)

    val uiState = _uiState.asStateFlow()

    fun mediaUploadInitiated(
        mediaType: String,
        fileSize: Long,
        originalFileName: String,
    ) {

        viewModelScope.launch {

            _uiState.value = MediaUploadInitiatedUIState.Loading

            when (
                val result = mediaUploadInitiatedRepository.mediaUploadInitiated(
                    mediaType = mediaType,
                    fileSize = fileSize,
                    originalFileName = originalFileName
                )
            ) {

                is ApiResult.Success -> {
                    _uiState.value =
                        MediaUploadInitiatedUIState.Success(
                            data = result.data,
                            message = result.message
                        )
                }

                is ApiResult.Error -> {
                    _uiState.value =
                        MediaUploadInitiatedUIState.Error(
                            message = result.message
                        )
                }

                is ApiResult.Loading -> Unit
            }
        }
    }
}