package com.saravanamarriagebureau.ui.screens.matches

import com.saravanamarriagebureau.data.model.Match

sealed class MatchesUiState {

    data object Idle : MatchesUiState()

    data object Loading : MatchesUiState()

    data class Success(
        val data: List<Match>,
        val message: String? = null
    ) : MatchesUiState()

    data class Error(
        val message: String
    ) : MatchesUiState()
}