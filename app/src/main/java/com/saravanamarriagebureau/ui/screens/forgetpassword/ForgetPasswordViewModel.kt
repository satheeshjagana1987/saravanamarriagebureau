package com.saravanamarriagebureau.ui.screens.forgetpassword

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saravanamarriagebureau.data.repository.ForgetPasswordRepository
import com.saravanamarriagebureau.util.ApiResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForgetPasswordViewModel @Inject constructor(
    private val forgetPasswordRepository: ForgetPasswordRepository
) : ViewModel() {

    private val _uiState =
        MutableStateFlow<ForgetPasswordUIState>(ForgetPasswordUIState.Idle)

    val uiState = _uiState.asStateFlow()

    fun forgetPassword(
        mobile: String
    ) {

        viewModelScope.launch {

            _uiState.value = ForgetPasswordUIState.Loading

            when (
                val result = forgetPasswordRepository.forgetPassword(
                    mobile = mobile,
                )
            ) {

                is ApiResult.Success -> {
                    _uiState.value =
                        ForgetPasswordUIState.Success(
                            data = result.data,
                            message = result.message
                        )
                }

                is ApiResult.Error -> {
                    _uiState.value =
                        ForgetPasswordUIState.Error(
                            message = result.message
                        )
                }

                is ApiResult.Loading -> Unit
            }
        }
    }

    fun resetState() {
        _uiState.value = ForgetPasswordUIState.Idle
    }
}