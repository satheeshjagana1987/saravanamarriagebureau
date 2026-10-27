package com.saravanamarriagebureau.ui.screens.admin.interests

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saravanamarriagebureau.data.repository.AdminInterestsRepository
import com.saravanamarriagebureau.util.ApiResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class AdminInterestsViewModel @Inject constructor(
    private val adminInterestsRepository: AdminInterestsRepository
) : ViewModel() {

    private val _uiStateAdminInterests =
        MutableStateFlow<AdminInterestsUIState>(AdminInterestsUIState.Idle)

    val uiStateAdminInterests = _uiStateAdminInterests.asStateFlow()

    private val _uiStateAdminInterestsById =
        MutableStateFlow<AdminInterestsByIdUIState>(AdminInterestsByIdUIState.Idle)

    val uiStateAdminInterestsById = _uiStateAdminInterestsById.asStateFlow()

    fun getAdminInterests() {

        viewModelScope.launch {

            _uiStateAdminInterests.value = AdminInterestsUIState.Loading

            when (
                val result = adminInterestsRepository.getAdminInterests()
            ) {

                is ApiResult.Success -> {
                    _uiStateAdminInterests.value =
                        AdminInterestsUIState.Success(
                            data = result.data,
                            message = result.message
                        )
                }

                is ApiResult.Error -> {
                    _uiStateAdminInterests.value =
                        AdminInterestsUIState.Error(
                            message = result.message
                        )
                }

                is ApiResult.Loading -> Unit
            }
        }
    }

    fun loadAdminInterestById(
        profileId: String
    ) {
        viewModelScope.launch {
            _uiStateAdminInterestsById.value = AdminInterestsByIdUIState.Loading

            when (val result = adminInterestsRepository.getAdminInterestById(
                interestId = profileId
            )) {
                is ApiResult.Success -> {
                    _uiStateAdminInterestsById.value =
                        AdminInterestsByIdUIState.Success(
                            data = result.data,
                            message = result.message
                        )
                }

                is ApiResult.Error -> {
                    _uiStateAdminInterestsById.value =
                        AdminInterestsByIdUIState.Error(
                            result.message
                        )
                }

                else -> {}
            }
        }
    }

    fun resetState() {
        _uiStateAdminInterests.value = AdminInterestsUIState.Idle
    }
}