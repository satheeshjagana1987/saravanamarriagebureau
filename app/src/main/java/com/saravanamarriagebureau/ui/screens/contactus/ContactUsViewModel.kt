package com.saravanamarriagebureau.ui.screens.contactus

import androidx.lifecycle.ViewModel
import com.saravanamarriagebureau.ui.interfaces.ExternalAppNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ContactUsViewModel @Inject constructor(
    private val appNavigator: ExternalAppNavigator
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
}