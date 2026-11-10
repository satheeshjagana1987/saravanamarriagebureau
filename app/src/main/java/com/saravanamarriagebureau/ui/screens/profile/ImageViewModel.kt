package com.saravanamarriagebureau.ui.screens.profile

import android.content.Context
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saravanamarriagebureau.data.repository.ImageRepository
import com.saravanamarriagebureau.util.ApiResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
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

    private val _imageStates =
        MutableStateFlow<Map<Int, ImageUiState>>(emptyMap())

    val imageStates = _imageStates.asStateFlow()

    private val _uploadVideoState = MutableStateFlow(ImageUiState())
    val uploadVideoState = _uploadVideoState.asStateFlow()

    private val _uploadGroomBrideUIState =
        MutableStateFlow<UploadGroomBrideVoiceNote>(UploadGroomBrideVoiceNote.Idle)

    val uploadGroomBrideUIState = _uploadGroomBrideUIState.asStateFlow()

    fun setImage(index: Int, uri: Uri) {
        Timber.e("uriviewmodelsetImageuri====>$uri")
        _imageStates.update { current ->
            current + (
                    index to ImageUiState(
                        imageUri = uri
                    )
                    )
        }
    }

    fun uploadImage(index: Int) {
        Timber.e("uriviewmodelUploadImageIndex====>$index")
        val currentState = _imageStates.value[index]
            ?: return

        if (currentState.isLoading) return

        val uri = currentState.imageUri ?: return

        Timber.e("uriviewmodeluploadImageuri====>$uri")

        val mediaType = when (index) {
            0, 1, 2 -> "PRIMARY_PHOTO"
            else -> return
        }

        viewModelScope.launch {
            _imageStates.update { states ->
                val latest = states[index] ?: currentState
                states + (
                        index to latest.copy(
                            isLoading = true,
                            success = false,
                            message = null,
                            error = null
                        )
                        )
            }

            try {
                val result = repository.uploadImage(
                    uri = uri,
                    mediaType = mediaType
                )

                _imageStates.update { states ->
                    val latest = states[index] ?: currentState

                    states + (
                            index to latest.copy(
                                isLoading = false,
                                success = true,
                                message = result
                            )
                            )
                }
            } catch (e: Exception) {
                Timber.e("size  catch====>${e.message}  code==>$e")
                _imageStates.update { states ->
                    val latest = states[index] ?: currentState

                    states + (
                            index to latest.copy(
                                isLoading = false,
                                success = false,
                                error = e.message ?: "Upload failed"
                            )
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

    fun resetImageState(index: Int) {
        _imageStates.update { states ->
            val current = states[index] ?: return@update states

            states + (
                    index to current.copy(
                        success = false,
                        error = null,
                        message = null
                    )
                    )
        }
    }
}