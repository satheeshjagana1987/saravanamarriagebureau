package com.saravanamarriagebureau.ui.screens.forgetpassword

import com.saravanamarriagebureau.data.model.ResetPasswordResponse

sealed class ResetPasswordUiState {
    data object Idle : ResetPasswordUiState()

    data object Loading : ResetPasswordUiState()

    data class Success(
        val data: ResetPasswordResponse,
        val message: String? = null
    ) : ResetPasswordUiState()

    data class Error(
        val message: String
    ) : ResetPasswordUiState()
}