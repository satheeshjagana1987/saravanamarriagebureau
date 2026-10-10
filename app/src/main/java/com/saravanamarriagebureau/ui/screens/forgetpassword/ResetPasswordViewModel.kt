package com.saravanamarriagebureau.ui.screens.forgetpassword

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saravanamarriagebureau.util.ApiResult
import com.saravanamarriagebureau.util.AppPreferences
import com.saravanamarriagebureau.util.Constants.NA
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResetPasswordViewModel @Inject constructor(
    private val resetPasswordRepository: ResetPasswordRepository,
    private val appPreferences: AppPreferences
): ViewModel() {

    private val _uiState =
        MutableStateFlow<ResetPasswordUiState>(ResetPasswordUiState.Idle)

    val uiState = _uiState.asStateFlow()

    fun resetPassword(
        newPassword: String,
        confirmPassword: String,
    ) {

        viewModelScope.launch {

            _uiState.value = ResetPasswordUiState.Loading

            when (
                val result = resetPasswordRepository.resetPassword(
                    resetToken = appPreferences.getResetToken() ?: NA,
                    newPassword = newPassword,
                    confirmPassword = confirmPassword
                )
            ) {

                is ApiResult.Success -> {
                    _uiState.value =
                        ResetPasswordUiState.Success(
                            data = result.data,
                            message = result.message
                        )
                }

                is ApiResult.Error -> {
                    _uiState.value =
                        ResetPasswordUiState.Error(
                            message = result.message
                        )
                }

                is ApiResult.Loading -> Unit
            }
        }
    }
}