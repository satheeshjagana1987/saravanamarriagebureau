package com.saravanamarriagebureau.util

import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat

object LanguageManager {

    fun changeLanguage(languageCode: String) {

        val localeList =
            LocaleListCompat.forLanguageTags(languageCode)

        AppCompatDelegate.setApplicationLocales(localeList)
    }

    fun getCurrentLanguage(): String {

        return AppCompatDelegate
            .getApplicationLocales()
            .get(0)
            ?.language ?: "en"
    }

}