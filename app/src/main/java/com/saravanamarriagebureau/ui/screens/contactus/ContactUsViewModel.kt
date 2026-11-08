package com.saravanamarriagebureau.ui.screens.contactus

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saravanamarriagebureau.data.model.EnquiryItem
import com.saravanamarriagebureau.data.repository.ContactUsRepository
import com.saravanamarriagebureau.util.ExternalAppNavigator
import com.saravanamarriagebureau.ui.screens.admin.enquiries.AdminEnquiriesUIState
import com.saravanamarriagebureau.ui.screens.admin.enquiries.AdminEnquiryUIState
import com.saravanamarriagebureau.util.ApiResult
import com.saravanamarriagebureau.util.AppPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContactUsViewModel @Inject constructor(
    private val appNavigator: ExternalAppNavigator,
    private val appPreferences: AppPreferences,
    private val contactUsRepository: ContactUsRepository
) : ViewModel() {

    fun onCallClick(phoneNumber: String) {
        appNavigator.openDialer(phoneNumber)
    }

    fun onWhatsAppClick(phoneNumber: String) {
        appNavigator.openWhatsApp(phoneNumber, "Hello! I am reaching out regarding...")
    }

    fun onEmailClick(email: String) {
        appNavigator.openEmail(email, "Inquiry", "Hello, I have a question about...")
    }

    fun profileName(): String? {
        return appPreferences.getProfileName()
    }

    fun profileMobileNumber(): String? {
        return appPreferences.getProfileMobile()
    }

    fun profileMail(): String? {
        return appPreferences.getProfileMail()
    }

    private val _uiStateEnquiries =
        MutableStateFlow<AdminEnquiriesUIState>(AdminEnquiriesUIState.Idle)

    val uiStateEnquiries = _uiStateEnquiries.asStateFlow()

    private val _uiStateEnquiryById =
        MutableStateFlow<AdminEnquiryUIState>(AdminEnquiryUIState.Idle)

    val uiStateEnquiryById = _uiStateEnquiryById.asStateFlow()

    private val _uiStateSubmitEnquiry =
        MutableStateFlow<SubmitEnquiryUIState>(SubmitEnquiryUIState.Idle)

    val uiStateSubmitEnquiry = _uiStateSubmitEnquiry.asStateFlow()

    private val _newEnquiries = MutableStateFlow<List<EnquiryItem>>(emptyList())
    val newEnquiries = _newEnquiries.asStateFlow()

    private val _resolvedEnquiries = MutableStateFlow<List<EnquiryItem>>(emptyList())
    val resolvedEnquiries = _resolvedEnquiries.asStateFlow()

    fun getEnquiries() {

        viewModelScope.launch {

            _uiStateEnquiries.value = AdminEnquiriesUIState.Loading

            when (
                val result = contactUsRepository.getEnquiries()
            ) {

                is ApiResult.Success -> {
                    _uiStateEnquiries.value =
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
                    _uiStateEnquiries.value =
                        AdminEnquiriesUIState.Error(
                            message = result.message
                        )
                }

                is ApiResult.Loading -> Unit
            }
        }
    }

    fun getEnquiryById(
        enquiryId: String
    ) {
        viewModelScope.launch {
            _uiStateEnquiryById.value = AdminEnquiryUIState.Loading

            when (val result = contactUsRepository.getEnquiryById(
                enquiryId = enquiryId
            )) {
                is ApiResult.Success -> {
                    _uiStateEnquiryById.value =
                        AdminEnquiryUIState.Success(
                            data = result.data,
                            message = result.message
                        )
                }

                is ApiResult.Error -> {
                    _uiStateEnquiryById.value =
                        AdminEnquiryUIState.Error(
                            result.message
                        )
                }

                else -> {}
            }
        }
    }

    fun submitEnquiry(
        fullName: String,
        mobileNumber: String,
        email: String,
        subject: String,
        message: String,
    ) {
        viewModelScope.launch {
            _uiStateSubmitEnquiry.value = SubmitEnquiryUIState.Loading

            when (val result = contactUsRepository.submitEnquiry(
                fullName = fullName,
                mobileNumber = mobileNumber,
                email = email,
                subject = subject,
                message = message
            )) {
                is ApiResult.Success -> {
                    _uiStateSubmitEnquiry.value =
                        SubmitEnquiryUIState.Success(
                            data = result.data,
                            message = result.message
                        )
                }

                is ApiResult.Error -> {
                    _uiStateSubmitEnquiry.value =
                        SubmitEnquiryUIState.Error(
                            result.message
                        )
                }

                else -> {}
            }
        }
    }
}