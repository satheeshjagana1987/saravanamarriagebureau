package com.saravanamarriagebureau.ui.screens.admin.enquiries

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saravanamarriagebureau.data.model.EnquiryItem
import com.saravanamarriagebureau.data.repository.AdminEnquiriesRepository
import com.saravanamarriagebureau.util.ApiResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AdminEnquiriesViewModel @Inject constructor(
    private val adminEnquiriesRepository: AdminEnquiriesRepository
) : ViewModel() {

    private val _uiStateAdminEnquiries =
        MutableStateFlow<AdminEnquiriesUIState>(AdminEnquiriesUIState.Idle)

    val uiStateAdminEnquiries = _uiStateAdminEnquiries.asStateFlow()

    private val _uiStateAdminEnquiryById =
        MutableStateFlow<AdminEnquiryUIState>(AdminEnquiryUIState.Idle)

    val uiStateAdminEnquiryById = _uiStateAdminEnquiryById.asStateFlow()

    private val _uiStateAdminEnquiryResolved =
        MutableStateFlow<AdminEnquiryUIState>(AdminEnquiryUIState.Idle)

    val uiStateAdminEnquiryResolved = _uiStateAdminEnquiryResolved.asStateFlow()

    private val _newEnquiries = MutableStateFlow<List<EnquiryItem>>(emptyList())
    val newEnquiries = _newEnquiries.asStateFlow()

    private val _resolvedEnquiries = MutableStateFlow<List<EnquiryItem>>(emptyList())
    val resolvedEnquiries = _resolvedEnquiries.asStateFlow()

    fun getAdminEnquiries() {

        viewModelScope.launch {

            _uiStateAdminEnquiries.value = AdminEnquiriesUIState.Loading

            when (
                val result = adminEnquiriesRepository.getAdminEnquiries()
            ) {

                is ApiResult.Success -> {
                    _uiStateAdminEnquiries.value =
                        AdminEnquiriesUIState.Success(
                            data = result.data,
                            message = result.message
                        )

                    if(result.data.isNotEmpty()){
                        _newEnquiries.value = result.data.filter {
                            it.status == "NEW"
                        }

                        _resolvedEnquiries.value = result.data.filter {
                            it.status == "RESOLVED"
                        }
                    }
                }

                is ApiResult.Error -> {
                    _uiStateAdminEnquiries.value =
                        AdminEnquiriesUIState.Error(
                            message = result.message
                        )
                }

                is ApiResult.Loading -> Unit
            }
        }
    }

    fun getAdminEnquiryById(
        enquiryId: String
    ) {
        viewModelScope.launch {
            _uiStateAdminEnquiryById.value = AdminEnquiryUIState.Loading

            when (val result = adminEnquiriesRepository.getAdminEnquiryById(
                enquiryId = enquiryId
            )) {
                is ApiResult.Success -> {
                    _uiStateAdminEnquiryById.value =
                        AdminEnquiryUIState.Success(
                            data = result.data,
                            message = result.message
                        )
                }

                is ApiResult.Error -> {
                    _uiStateAdminEnquiryById.value =
                        AdminEnquiryUIState.Error(
                            result.message
                        )
                }

                else -> {}
            }
        }
    }

    fun updateAdminEnquiryById(
        enquiryId: String,
        comment: String,
    ) {
        viewModelScope.launch {
            _uiStateAdminEnquiryResolved.value = AdminEnquiryUIState.Loading

            when (val result = adminEnquiriesRepository.updateAdminEnquiryById(
                enquiryId = enquiryId,
                comment = comment,
            )) {
                is ApiResult.Success -> {
                    _uiStateAdminEnquiryResolved.value =
                        AdminEnquiryUIState.Success(
                            data = result.data,
                            message = result.message
                        )
                }

                is ApiResult.Error -> {
                    _uiStateAdminEnquiryResolved.value =
                        AdminEnquiryUIState.Error(
                            result.message
                        )
                }

                else -> {}
            }
        }
    }
}