package com.saravanamarriagebureau.ui.screens.media

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saravanamarriagebureau.data.repository.MediaUploadCompleteRepository
import com.saravanamarriagebureau.util.ApiResult
import com.saravanamarriagebureau.util.AppPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MediaUploadCompleteViewModel @Inject constructor(
    private val mediaUploadCompleteRepository: MediaUploadCompleteRepository,
    private val appPreferences: AppPreferences
): ViewModel() {
    private val _uiState =
        MutableStateFlow<MediaUploadCompleteUIState>(MediaUploadCompleteUIState.Idle)

    val uiState = _uiState.asStateFlow()

    fun mediaUploadComplete() {

        viewModelScope.launch {

            _uiState.value = MediaUploadCompleteUIState.Loading

            when (
                val result = mediaUploadCompleteRepository.mediaUploadComplete(
                   mediaId = appPreferences.getMediaId().toString()
                )
            ) {

                is ApiResult.Success -> {
                    _uiState.value =
                        MediaUploadCompleteUIState.Success(
                            data = result.data,
                            message = result.message
                        )
                }

                is ApiResult.Error -> {
                    _uiState.value =
                        MediaUploadCompleteUIState.Error(
                            message = result.message
                        )
                }

                is ApiResult.Loading -> Unit
            }
        }
    }
}