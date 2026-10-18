package com.saravanamarriagebureau.ui.screens.changepassword

import com.saravanamarriagebureau.data.model.ResetPasswordResponse

sealed class ChangePasswordUIState {

    data object Idle : ChangePasswordUIState()

    data object Loading : ChangePasswordUIState()

    data class Success(
        val data: ResetPasswordResponse,
        val message: String? = null
    ) : ChangePasswordUIState()

    data class Error(
        val message: String
    ) : ChangePasswordUIState()
}