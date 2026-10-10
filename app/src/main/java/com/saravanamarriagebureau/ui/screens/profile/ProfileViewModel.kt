package com.saravanamarriagebureau.ui.screens.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saravanamarriagebureau.data.repository.ProfileRepository
import com.saravanamarriagebureau.data.repository.RegisterRepository
import com.saravanamarriagebureau.ui.screens.registration.RegisterUIState
import com.saravanamarriagebureau.util.ApiResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileRepository: ProfileRepository
) : ViewModel() {

    private val _uiState =
        MutableStateFlow<ProfileUIState>(ProfileUIState.Idle)

    val uiState = _uiState.asStateFlow()


    fun loadProfile(profileId: String) {
        viewModelScope.launch {
            _uiState.value = ProfileUIState.Loading

            when (val result = profileRepository.getProfile()) {
                is ApiResult.Success -> {
                    val response = result.data

                    if (response.success) {
                        _uiState.value =
                            ProfileUIState.Success(
                                data = response
                            )
                    } else {
                        _uiState.value =
                            ProfileUIState.Error(
                                "Unable to load profile"
                            )
                    }
                }

                is ApiResult.Error -> {
                    _uiState.value =
                        ProfileUIState.Error(
                            result.message
                        )
                }

                else -> {}
            }
        }
    }

    fun updateProfile(
        height: String,
        education: String,
        profession: String,
        religion: String,
        email: String,
        fullAddress: String,
    ) {

        viewModelScope.launch {

            _uiState.value = ProfileUIState.Loading

            when (
                val result = profileRepository.updateProfile(
                    height = height,
                    education = education,
                    profession = profession,
                    religion = religion,
                    fullAddress = fullAddress,
                    email = email,
                )
            ) {

                is ApiResult.Success -> {

                    _uiState.value =
                        ProfileUIState.Success(
                            data = result.data,
                            message = result.message
                        )
                }

                is ApiResult.Error -> {

                    _uiState.value =
                        ProfileUIState.Error(
                            message = result.message
                        )
                }

                is ApiResult.Loading -> Unit
            }
        }
    }
}