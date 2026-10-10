package com.saravanamarriagebureau.ui.screens.matches

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saravanamarriagebureau.data.model.Match
import com.saravanamarriagebureau.data.repository.MatchesRepository
import com.saravanamarriagebureau.util.ApiResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MatchesViewModel @Inject constructor(
    private val matchesRepository: MatchesRepository
) : ViewModel() {

    private val _matches = MutableStateFlow<List<Match>>(emptyList())
    val matches: StateFlow<List<Match>> = _matches.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    init {
        fetchMatches()
    }

    fun fetchMatches() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            try {
                when (val result = matchesRepository.getMatches()) {
                    is ApiResult.Success -> {
                        _matches.value = result.data
                    }

                    is ApiResult.Error -> {
                        _error.value = result.message
                    }

                    else -> {}
                }
            } catch (e: Exception) {
                _error.value = e.message ?: "Something went wrong"
            } finally {
                _isLoading.value = false
            }
        }
    }
}
