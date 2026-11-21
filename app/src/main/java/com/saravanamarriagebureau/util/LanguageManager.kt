package com.saravanamarriagebureau.util

import android.app.LocaleManager
import android.content.Context
import android.content.res.Configuration
import android.os.Build
import android.os.LocaleList
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import java.util.Locale

object LanguageManager {

    fun applyLanguage(
        context: Context,
        languageCode: String
    ) {
        val locale = Locale.forLanguageTag(languageCode)

        Locale.setDefault(locale)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {

            val localeManager =
                context.getSystemService(LocaleManager::class.java)

            localeManager.applicationLocales =
                LocaleList.forLanguageTags(languageCode)

        } else {
            // Save the language for older Android versions.
            context.getSharedPreferences(
                "language_preferences",
                Context.MODE_PRIVATE
            ).edit()
                .putString("selected_language", languageCode)
                .apply()
        }
    }

    fun getCurrentLanguage(
        context: Context,
        preferences: LanguagePreferences
    ): String {

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {

            val localeManager =
                context.getSystemService(LocaleManager::class.java)

            val appLocales = localeManager.applicationLocales

            if (!appLocales.isEmpty) {
                appLocales[0].language
            } else {
                "en"
            }

        } else {
            preferences.getLanguage()
        }
    }
}