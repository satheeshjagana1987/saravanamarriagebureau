package com.saravanamarriagebureau.ui.screens.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saravanamarriagebureau.data.repository.ProfileRepository
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

    private val _uiStateProfileUpdate =
        MutableStateFlow<ProfileUpdateUIState>(ProfileUpdateUIState.Idle)

    val uiStateProfileUpdate = _uiStateProfileUpdate.asStateFlow()

    private val _uiStateById =
        MutableStateFlow<ProfileUIState>(ProfileUIState.Idle)

    val uiStateById = _uiStateById.asStateFlow()


    fun loadProfile() {
        viewModelScope.launch {
            _uiState.value = ProfileUIState.Loading

            when (val result = profileRepository.getProfile()) {
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
                            result.message
                        )
                }

                else -> {}
            }
        }
    }

    fun loadProfileById(
        profileId: String
    ) {
        viewModelScope.launch {
            _uiStateById.value = ProfileUIState.Loading

            when (val result = profileRepository.getProfileById(
                profileId = profileId
            )) {
                is ApiResult.Success -> {
                    _uiStateById.value =
                        ProfileUIState.Success(
                            data = result.data,
                            message = result.message
                        )
                }

                is ApiResult.Error -> {
                    _uiStateById.value =
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
        city: String,
        district: String,
        maritalStatus: String,
        placeOfBirth: String,
        birthTime: String,
        birthStar: String,
        rasi: String,
        workLocation: String,
        nativePlace: String,
        familyDetails: String,
        income: String
    ) {

        viewModelScope.launch {

            _uiStateProfileUpdate.value = ProfileUpdateUIState.Loading

            when (
                val result = profileRepository.updateProfile(
                    height = height,
                    education = education,
                    profession = profession,
                    religion = religion,
                    fullAddress = fullAddress,
                    email = email,
                    city = city,
                    district = district,
                    maritalStatus = maritalStatus,
                    placeOfBirth = placeOfBirth,
                    birthTime = birthTime,
                    birthStar = birthStar,
                    rasi = rasi,
                    workLocation = workLocation,
                    nativePlace = nativePlace,
                    familyDetails = familyDetails,
                    income = income

                )
            ) {

                is ApiResult.Success -> {

                    _uiStateProfileUpdate.value =
                        ProfileUpdateUIState.Success(
                            message = result.message
                        )
                }

                is ApiResult.Error -> {

                    _uiStateProfileUpdate.value =
                        ProfileUpdateUIState.Error(
                            message = result.message
                        )
                }

                is ApiResult.Loading -> Unit
            }
        }
    }
}