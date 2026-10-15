package com.saravanamarriagebureau.ui.screens.interests

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saravanamarriagebureau.data.repository.UserInterestsRepository
import com.saravanamarriagebureau.util.ApiResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserInterestsViewModel @Inject constructor(
    private val userInterestsRepository: UserInterestsRepository
) : ViewModel() {

    private val _uiState =
        MutableStateFlow<UserInterestsUIState>(UserInterestsUIState.Idle)

    val uiState = _uiState.asStateFlow()

    private val _uiStateSentInterest =
        MutableStateFlow<UserInterestsUIState>(UserInterestsUIState.Idle)

    val uiStateSentInterest = _uiStateSentInterest.asStateFlow()

    fun getSentInterests() {
        viewModelScope.launch {
            _uiStateSentInterest.value = UserInterestsUIState.Loading

            when (val result = userInterestsRepository.getSentInterests()) {
                is ApiResult.Success -> {
                    _uiStateSentInterest.value =
                        UserInterestsUIState.Success(
                            data = result.data,
                            message = result.message
                        )
                }

                is ApiResult.Error -> {
                    _uiStateSentInterest.value =
                        UserInterestsUIState.Error(
                            result.message
                        )
                }

                else -> {}
            }
        }
    }

    fun getReceivedInterests() {
        viewModelScope.launch {
            _uiState.value = UserInterestsUIState.Loading

            when (val result = userInterestsRepository.getReceivedInterests()) {
                is ApiResult.Success -> {
                    _uiState.value =
                        UserInterestsUIState.Success(
                            data = result.data,
                            message = result.message
                        )
                }

                is ApiResult.Error -> {
                    _uiState.value =
                        UserInterestsUIState.Error(
                            result.message
                        )
                }

                else -> {}
            }
        }
    }
}