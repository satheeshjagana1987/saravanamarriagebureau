package com.saravanamarriagebureau.ui.screens.matches

import com.saravanamarriagebureau.data.model.MatchesData

sealed class MatchesUiState {

    data object Idle : MatchesUiState()

    data object Loading : MatchesUiState()

    data class Success(
        val data: List<MatchesData>,
        val message: String? = null
    ) : MatchesUiState()

    data class Error(
        val message: String
    ) : MatchesUiState()
}