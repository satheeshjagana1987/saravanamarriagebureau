package com.saravanamarriagebureau.ui.screens.forgetpassword

import com.saravanamarriagebureau.data.model.VerifyResetOTP

sealed class VerifyResetOTPPasswordUIState {

    data object Idle : VerifyResetOTPPasswordUIState()

    data object Loading : VerifyResetOTPPasswordUIState()

    data class Success(
        val data: VerifyResetOTP,
        val message: String? = null
    ) : VerifyResetOTPPasswordUIState()

    data class Error(
        val message: String
    ) : VerifyResetOTPPasswordUIState()
}