package com.saravanamarriagebureau.ui.screens.interests

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saravanamarriagebureau.data.repository.UserInterestsRepository
import com.saravanamarriagebureau.ui.screens.matches.DeleteInterestsUIState
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
        MutableStateFlow<UserInterestsAcceptRejectUIState>(UserInterestsAcceptRejectUIState.Idle)

    val uiStateAcceptInterest = _uiStateAcceptInterest.asStateFlow()

    private val _uiStateRejectInterest =
        MutableStateFlow<UserInterestsAcceptRejectUIState>(UserInterestsAcceptRejectUIState.Idle)

    val uiStateRejectInterest = _uiStateRejectInterest.asStateFlow()

    private val _uiStateDeleteInterest =
        MutableStateFlow<SendInterestsUIState>(SendInterestsUIState.Idle)

    val uiStateDeleteInterest = _uiStateDeleteInterest.asStateFlow()


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
            _uiStateAcceptInterest.value = UserInterestsAcceptRejectUIState.Loading

            when (val result = userInterestsRepository.acceptReceivedInterest(
                interestId = interestId
            )) {
                is ApiResult.Success -> {
                    _uiStateAcceptInterest.value =
                        UserInterestsAcceptRejectUIState.Success(
                            data = result.data,
                            message = result.message
                        )
                }

                is ApiResult.Error -> {
                    _uiStateAcceptInterest.value =
                        UserInterestsAcceptRejectUIState.Error(
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
            _uiStateRejectInterest.value = UserInterestsAcceptRejectUIState.Loading

            when (val result = userInterestsRepository.rejectReceivedInterest(
                interestId = interestId
            )) {
                is ApiResult.Success -> {
                    _uiStateRejectInterest.value =
                        UserInterestsAcceptRejectUIState.Success(
                            data = result.data,
                            message = result.message
                        )
                }

                is ApiResult.Error -> {
                    _uiStateRejectInterest.value =
                        UserInterestsAcceptRejectUIState.Error(
                            result.message
                        )
                }

                else -> {}
            }
        }
    }

    fun deleteInterest(interestId: String) {
        viewModelScope.launch {
            _uiStateDeleteInterest.value = SendInterestsUIState.Loading

            when (val result = userInterestsRepository.deleteInterest(interestId = interestId)) {
                is ApiResult.Success -> {
                    _uiStateDeleteInterest.value =
                        SendInterestsUIState.Success(
                            data = result.data,
                            message = result.message
                        )
                }

                is ApiResult.Error -> {
                    _uiStateDeleteInterest.value =
                        SendInterestsUIState.Error(
                            result.message
                        )
                }

                else -> {}
            }
        }
    }

    fun resetDeleteInterestState() {
        _uiStateDeleteInterest.value = SendInterestsUIState.Idle
    }
}