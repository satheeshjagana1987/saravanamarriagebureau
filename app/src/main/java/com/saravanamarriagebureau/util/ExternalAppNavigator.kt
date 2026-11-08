package com.saravanamarriagebureau.util

interface ExternalAppNavigator {
    fun openDialer(phoneNumber: String)
    fun openWhatsApp(phoneNumber: String, message: String = "")
    fun openEmail(emailAddress: String, subject: String = "", body: String = "")
}