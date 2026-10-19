package com.saravanamarriagebureau.ui.screens.profile

import android.app.Application
import android.content.Context
import android.net.Uri
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saravanamarriagebureau.data.repository.ImageRepository
import com.saravanamarriagebureau.ui.screens.forgetpassword.ForgetPasswordUIState
import com.saravanamarriagebureau.ui.screens.registration.RegisterUIState
import com.saravanamarriagebureau.util.ApiResult
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.internal.Contexts.getApplication
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import javax.inject.Inject

@HiltViewModel
class ImageViewModel @Inject constructor(
    private val repository: ImageRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ImageUiState())
    val uiState = _uiState.asStateFlow()

    private val _uiStateDeleteImage =
        MutableStateFlow<DeleteMediaUIState>(DeleteMediaUIState.Idle)

    val uiStateDeleteImage = _uiStateDeleteImage.asStateFlow()

    private val _uploadVideoState = MutableStateFlow(ImageUiState())
    val uploadVideoState = _uploadVideoState.asStateFlow()

    private val _uploadGroomBrideUIState =
        MutableStateFlow<UploadGroomBrideVoiceNote>(UploadGroomBrideVoiceNote.Idle)

    val uploadGroomBrideUIState = _uploadGroomBrideUIState.asStateFlow()

    fun setImage(uri: Uri) {
        _uiState.update {
            it.copy(
                imageUri = uri,
                message = null,
                error = null
            )
        }
    }

    fun uploadImage() {
        // Prevent duplicate uploads
        if (_uiState.value.isLoading) return
        val uri = _uiState.value.imageUri ?: return

        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    isLoading = true,
                    message = null,
                    error = null,
                    success = false
                )
            }

            try {
                val result = repository.uploadImage(uri)

                _uiState.update {
                    it.copy(
                        isLoading = false,
                        message = result,
                        success = true
                    )
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        success = false,
                        error = e.message ?: "Upload failed"
                    )
                }
            }
        }
    }

    fun deleteImage(
        mediaId: String,
    ) {
        viewModelScope.launch {
            _uiStateDeleteImage.value = DeleteMediaUIState.Loading
            when (
                val result = repository.deleteImage(
                    mediaId = mediaId,
                )
            ) {

                is ApiResult.Success -> {

                    _uiStateDeleteImage.value =
                        DeleteMediaUIState.Success(
                            data = result.data,
                            message = result.message
                        )
                }

                is ApiResult.Error -> {

                    _uiStateDeleteImage.value =
                        DeleteMediaUIState.Error(
                            message = result.message
                        )
                }

                is ApiResult.Loading -> Unit
            }
        }
    }

    fun uploadVideo(uri: Uri, context: Context) {
        if (_uploadVideoState.value.isLoading) return
        val uri = _uploadVideoState.value.imageUri ?: return

        viewModelScope.launch {
            _uploadVideoState.update {
                it.copy(
                    isLoading = true,
                    message = null,
                    error = null,
                    success = false
                )
            }

            try {
                val message = repository.uploadVideo(
                    context = context,
                    uri = uri
                )

                _uploadVideoState.update {
                    it.copy(
                        isLoading = false,
                        message = message,
                        success = true
                    )
                }

            } catch (e: Exception) {
                _uploadVideoState.update {
                    it.copy(
                        isLoading = false,
                        success = false,
                        error = e.message ?: "Upload failed"
                    )
                }
            }
        }
    }

    fun uploadVoiceGroomOrBrideRecord(file: File) {
        viewModelScope.launch {
            _uploadGroomBrideUIState.value = UploadGroomBrideVoiceNote.Loading

            try {
                val message = repository.uploadVoiceNote(
                    file = file
                )

                _uploadGroomBrideUIState.value =
                    UploadGroomBrideVoiceNote.Success(
                        message
                    )

            } catch (e: Exception) {
                _uploadGroomBrideUIState.value = UploadGroomBrideVoiceNote.Error(
                    e.message ?: "Voice upload failed"
                )
            }
        }
    }
}