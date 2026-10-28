package com.saravanamarriagebureau.ui.screens.matches

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saravanamarriagebureau.data.repository.MatchesRepository
import com.saravanamarriagebureau.ui.screens.forgetpassword.ForgetPasswordUIState
import com.saravanamarriagebureau.ui.screens.interests.SendInterestsUIState
import com.saravanamarriagebureau.util.ApiResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MatchesViewModel @Inject constructor(
    private val matchesRepository: MatchesRepository
) : ViewModel() {

    private val _uiStateToday =
        MutableStateFlow<MatchesUiState>(MatchesUiState.Idle)

    val uiStateToday = _uiStateToday.asStateFlow()

    private val _uiStatePast =
        MutableStateFlow<MatchesUiState>(MatchesUiState.Idle)

    val uiStatePast = _uiStatePast.asStateFlow()

    private val _uiStateRecommended =
        MutableStateFlow<MatchesUiState>(MatchesUiState.Idle)

    val uiStateRecommended = _uiStateRecommended.asStateFlow()

    private val _uiStateSendInterest =
        MutableStateFlow<SendInterestsUIState>(SendInterestsUIState.Idle)

    val uiStateSendInterest = _uiStateSendInterest.asStateFlow()

    private val _uiStateDeleteInterest =
        MutableStateFlow<DeleteInterestsUIState>(DeleteInterestsUIState.Idle)

    val uiStateDeleteInterest = _uiStateDeleteInterest.asStateFlow()

    fun getTodayMatches() {

        viewModelScope.launch {

            _uiStateToday.value = MatchesUiState.Loading

            when (
                val result = matchesRepository.getTodayMatches()
            ) {

                is ApiResult.Success -> {
                    _uiStateToday.value =
                        MatchesUiState.Success(
                            data = result.data,
                            message = result.message
                        )
                }

                is ApiResult.Error -> {
                    _uiStateToday.value =
                        MatchesUiState.Error(
                            message = result.message
                        )
                }

                is ApiResult.Loading -> Unit
            }
        }
    }

    fun getPastMatches() {

        viewModelScope.launch {

            _uiStatePast.value = MatchesUiState.Loading

            when (
                val result = matchesRepository.getPastMatches()
            ) {

                is ApiResult.Success -> {
                    _uiStatePast.value =
                        MatchesUiState.Success(
                            data = result.data,
                            message = result.message
                        )
                }

                is ApiResult.Error -> {
                    _uiStatePast.value =
                        MatchesUiState.Error(
                            message = result.message
                        )
                }

                is ApiResult.Loading -> Unit
            }
        }
    }

    fun getRecommendedMatches() {

        viewModelScope.launch {

            _uiStateRecommended.value = MatchesUiState.Loading

            when (
                val result = matchesRepository.getRecommendedMatches()
            ) {

                is ApiResult.Success -> {
                    _uiStateRecommended.value =
                        MatchesUiState.Success(
                            data = result.data,
                            message = result.message
                        )
                }

                is ApiResult.Error -> {
                    _uiStateRecommended.value =
                        MatchesUiState.Error(
                            message = result.message
                        )
                }

                is ApiResult.Loading -> Unit
            }
        }
    }

    fun sendInterest(profileId: String) {
        viewModelScope.launch {
            _uiStateSendInterest.value = SendInterestsUIState.Loading

            when (val result = matchesRepository.sendInterest(profileId = profileId)) {
                is ApiResult.Success -> {
                    _uiStateSendInterest.value =
                        SendInterestsUIState.Success(
                            data = result.data,
                            message = result.message
                        )
                }

                is ApiResult.Error -> {
                    _uiStateSendInterest.value =
                        SendInterestsUIState.Error(
                            result.message
                        )
                }

                else -> {}
            }
        }
    }

    fun deleteInterest(interestId: String) {
        viewModelScope.launch {
            _uiStateDeleteInterest.value = DeleteInterestsUIState.Loading

            when (val result = matchesRepository.deleteInterest(interestId = interestId)) {
                is ApiResult.Success -> {
                    _uiStateDeleteInterest.value =
                        DeleteInterestsUIState.Success(
                            data = result.data,
                            message = result.message
                        )
                }

                is ApiResult.Error -> {
                    _uiStateDeleteInterest.value =
                        DeleteInterestsUIState.Error(
                            result.message
                        )
                }

                else -> {}
            }
        }
    }

    fun resetState() {
        _uiStateToday.value = MatchesUiState.Idle
    }
}
