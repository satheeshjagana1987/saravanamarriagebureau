package com.saravanamarriagebureau.ui.screens.registration

import com.saravanamarriagebureau.data.model.RegisterResponse

sealed class RegisterUIState {

    data object Idle : RegisterUIState()

    data object Loading : RegisterUIState()

    data class Success(
        val data: RegisterResponse,
        val message: String? = null
    ) : RegisterUIState()

    data class Error(
        val message: String
    ) : RegisterUIState()
}