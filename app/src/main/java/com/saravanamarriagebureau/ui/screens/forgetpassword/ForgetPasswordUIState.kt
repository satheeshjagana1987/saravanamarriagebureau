package com.saravanamarriagebureau.ui.screens.forgetpassword

import com.saravanamarriagebureau.data.model.ForgetPasswordResponse

sealed class ForgetPasswordUIState {
    data object Idle : ForgetPasswordUIState()

    data object Loading : ForgetPasswordUIState()

    data class Success(
        val data: ForgetPasswordResponse,
        val message: String? = null
    ) : ForgetPasswordUIState()

    data class Error(
        val message: String
    ) : ForgetPasswordUIState()
}