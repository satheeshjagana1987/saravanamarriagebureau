package com.saravanamarriagebureau.ui.screens.profile

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saravanamarriagebureau.data.repository.ImageRepository
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImageViewModel @Inject constructor(
    private val repository: ImageRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ImageUiState())
    val uiState = _uiState.asStateFlow()

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
        val uri = _uiState.value.imageUri ?: return

        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    isLoading = true,
                    message = null,
                    error = null
                )
            }

            try {
                val result = repository.uploadImage(uri)

                _uiState.update {
                    it.copy(
                        isLoading = false,
                        message = result
                    )
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        error = e.message ?: "Upload failed"
                    )
                }
            }
        }
    }
}