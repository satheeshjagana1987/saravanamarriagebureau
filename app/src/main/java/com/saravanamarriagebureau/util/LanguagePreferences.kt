package com.saravanamarriagebureau.util

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LanguagePreferences  @Inject constructor(
    @ApplicationContext context: Context
) {
    private val preferences = context.getSharedPreferences(
        "language_preferences",
        Context.MODE_PRIVATE
    )

    fun getLanguage(): String {
        return preferences.getString("selected_language", "en") ?: "en"
    }

    fun saveLanguage(language: String) {
        preferences.edit()
            .putString("selected_language", language)
            .apply()
    }
}