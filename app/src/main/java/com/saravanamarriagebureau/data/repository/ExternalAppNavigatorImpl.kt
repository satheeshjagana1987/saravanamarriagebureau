package com.saravanamarriagebureau.data.repository

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.saravanamarriagebureau.util.ExternalAppNavigator
import com.saravanamarriagebureau.util.ToastUtils
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton
import androidx.core.net.toUri


@Singleton
class ExternalAppNavigatorImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : ExternalAppNavigator {
    override fun openDialer(phoneNumber: String) {
        val intent = Intent(Intent.ACTION_DIAL).apply { //
            data = Uri.parse("tel:$phoneNumber") //
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) // Required when launching from application context
        }
        safelyStartActivity(intent, "No dialer application found")
    }

    override fun openWhatsApp(phoneNumber: String, message: String) {

        // Remove spaces and special characters
        val formattedNumber = phoneNumber.filter { it.isDigit() }

        // Encode message
        val encodedMessage = Uri.encode(message)

        // Create WhatsApp URL
        val url = "https://wa.me/$formattedNumber?text=$encodedMessage"

        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse(url)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }

        safelyStartActivity(intent, "WhatsApp is not installed")
    }

    override fun openEmail(
        emailAddress: String,
        subject: String,
        body: String
    ) {
        val intent =
            Intent(Intent.ACTION_SENDTO).apply { // ACTION_SENDTO filters explicitly for email clients
                data = "mailto:".toUri()
                putExtra(Intent.EXTRA_EMAIL, arrayOf(emailAddress)) //
                putExtra(Intent.EXTRA_SUBJECT, subject) //
                putExtra(Intent.EXTRA_TEXT, body) //
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
        safelyStartActivity(intent, "No email client found")
    }

    private fun safelyStartActivity(intent: Intent, errorMessage: String) {
        try {
            context.startActivity(intent) //
        } catch (e: Exception) {
            ToastUtils.error(context, errorMessage)
        }
    }
}