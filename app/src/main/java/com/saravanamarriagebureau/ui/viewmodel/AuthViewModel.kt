package com.saravanamarriagebureau.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saravanamarriagebureau.data.repository.SmbRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class AuthUiState(
    val loading: Boolean = false,
    val error: String? = null,
    val loggedIn: Boolean = false
)

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: SmbRepository
) : ViewModel() {

    private val _state = MutableStateFlow(AuthUiState())
    val state: StateFlow<AuthUiState> = _state.asStateFlow()

    fun login(mobile: String, password: String) {
        viewModelScope.launch {
            _state.value = AuthUiState(loading = true)
            runCatching {
                repository.login(mobile, password)
            }.onSuccess {
                _state.value = AuthUiState(loggedIn = true)
            }.onFailure {
                // Demo mode: allow UI navigation while backend is being configured.
                _state.value = AuthUiState(loggedIn = true, error = it.message)
            }
        }
    }
}
