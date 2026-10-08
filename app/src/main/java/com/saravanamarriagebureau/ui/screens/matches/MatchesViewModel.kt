package com.saravanamarriagebureau.ui.screens.matches

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saravanamarriagebureau.data.model.Profile
import com.saravanamarriagebureau.data.repository.SmbRepository
import com.saravanamarriagebureau.ui.screens.demoProfiles
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class MatchesUiState(
    val loading: Boolean = false,
    val profiles: List<Profile> = demoProfiles,
    val message: String? = null
)

@HiltViewModel
class MatchesViewModel @Inject constructor(
    private val repository: SmbRepository
) : ViewModel() {

    private val _state = MutableStateFlow(MatchesUiState())
    val state: StateFlow<MatchesUiState> = _state.asStateFlow()

    fun load() {
        viewModelScope.launch {
            _state.value = _state.value.copy(loading = true)
            runCatching { repository.matches() }
                .onSuccess { matches ->
                    _state.value = MatchesUiState(profiles = matches.map { it.profile })
                }
                .onFailure {
                    _state.value = MatchesUiState(
                        profiles = demoProfiles,
                        message = "Showing demo profiles. Connect your API to load live matches."
                    )
                }
        }
    }

    fun sendInterest(profileId: String) {
        viewModelScope.launch {
            runCatching { repository.sendInterest(profileId) }
            _state.value = _state.value.copy(message = "Interest sent successfully")
        }
    }
}
