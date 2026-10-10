package com.saravanamarriagebureau.ui.screens.forgetpassword

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saravanamarriagebureau.data.repository.VerifyResetOTPRepository
import com.saravanamarriagebureau.util.ApiResult
import com.saravanamarriagebureau.util.AppPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VerifyResetOTPViewModel @Inject constructor(
    private val verifyResetOTPRepository: VerifyResetOTPRepository,
    private val appPreferences: AppPreferences
): ViewModel() {
    private val _uiState =
        MutableStateFlow<VerifyResetOTPPasswordUIState>(VerifyResetOTPPasswordUIState.Idle)

    val uiState = _uiState.asStateFlow()

    fun verifyResetOTP(
        mobile: String,
        otp: String
    ) {

        viewModelScope.launch {

            _uiState.value = VerifyResetOTPPasswordUIState.Loading

            when (
                val result = verifyResetOTPRepository.verifyResetOTPPassword(
                    mobile = mobile,
                    otp = otp
                )
            ) {

                is ApiResult.Success -> {
                    _uiState.value =
                        VerifyResetOTPPasswordUIState.Success(
                            data = result.data,
                            message = result.message
                        )
                    appPreferences.saveResetToken(
                        result.data.resetToken
                    )
                }

                is ApiResult.Error -> {
                    _uiState.value =
                        VerifyResetOTPPasswordUIState.Error(
                            message = result.message
                        )
                }

                is ApiResult.Loading -> Unit
            }
        }
    }
}