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

    private val _uiStateReceived =
        MutableStateFlow<UserInterestsUIState>(UserInterestsUIState.Idle)

    val uiStateReceived = _uiStateReceived.asStateFlow()

    private val _uiStateSentInterest =
        MutableStateFlow<UserInterestsUIState>(UserInterestsUIState.Idle)

    val uiStateSentInterest = _uiStateSentInterest.asStateFlow()

    private val _uiStateAcceptInterest =
        MutableStateFlow<UserInterestsUIState>(UserInterestsUIState.Idle)

    val uiStateAcceptInterest = _uiStateAcceptInterest.asStateFlow()

    private val _uiStateRejectInterest =
        MutableStateFlow<UserInterestsUIState>(UserInterestsUIState.Idle)

    val uiStateRejectInterest = _uiStateRejectInterest.asStateFlow()

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
            _uiStateReceived.value = UserInterestsUIState.Loading

            when (val result = userInterestsRepository.getReceivedInterests()) {
                is ApiResult.Success -> {
                    _uiStateReceived.value =
                        UserInterestsUIState.Success(
                            data = result.data,
                            message = result.message
                        )
                }

                is ApiResult.Error -> {
                    _uiStateReceived.value =
                        UserInterestsUIState.Error(
                            result.message
                        )
                }

                else -> {}
            }
        }
    }

    fun acceptReceivedInterest(
        interestId: String
    ) {
        viewModelScope.launch {
            _uiStateAcceptInterest.value = UserInterestsUIState.Loading

            when (val result = userInterestsRepository.acceptReceivedInterest(
                interestId = interestId
            )) {
                is ApiResult.Success -> {
                    _uiStateAcceptInterest.value =
                        UserInterestsUIState.Success(
                            data = result.data,
                            message = result.message
                        )
                }

                is ApiResult.Error -> {
                    _uiStateAcceptInterest.value =
                        UserInterestsUIState.Error(
                            result.message
                        )
                }

                else -> {}
            }
        }
    }

    fun rejectReceivedInterest(
        interestId: String
    ) {
        viewModelScope.launch {
            _uiStateRejectInterest.value = UserInterestsUIState.Loading

            when (val result = userInterestsRepository.rejectReceivedInterest(
                interestId = interestId
            )) {
                is ApiResult.Success -> {
                    _uiStateRejectInterest.value =
                        UserInterestsUIState.Success(
                            data = result.data,
                            message = result.message
                        )
                }

                is ApiResult.Error -> {
                    _uiStateRejectInterest.value =
                        UserInterestsUIState.Error(
                            result.message
                        )
                }

                else -> {}
            }
        }
    }
}