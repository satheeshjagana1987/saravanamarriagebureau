package com.saravanamarriagebureau.util

import android.content.Context
import android.content.SharedPreferences
import com.saravanamarriagebureau.util.Constants.KEY_ACCESS_TOKEN
import com.saravanamarriagebureau.util.Constants.KEY_IS_LOGGED_IN
import com.saravanamarriagebureau.util.Constants.KEY_USER_ID
import com.saravanamarriagebureau.util.Constants.KEY_USER_NAME
import com.saravanamarriagebureau.util.Constants.PREF_NAME
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppPreferences @Inject constructor(
    @ApplicationContext context: Context
) {
    private val preferences: SharedPreferences =
        context.getSharedPreferences(
            PREF_NAME,
            Context.MODE_PRIVATE
        )

    fun saveUserId(userId: String) {
        preferences.edit()
            .putString(KEY_USER_ID, userId)
            .apply()
    }

    fun getUserId(): String? {
        return preferences.getString(KEY_USER_ID, null)
    }

    fun saveLoginStatus(isLoggedIn: Boolean) {
        preferences.edit()
            .putBoolean(KEY_IS_LOGGED_IN, isLoggedIn)
            .apply()
    }

    fun isLoggedIn(): Boolean {
        return preferences.getBoolean(KEY_IS_LOGGED_IN, false)
    }

    fun saveAccessToken(accessToken: String) {
        preferences.edit()
            .putString(KEY_ACCESS_TOKEN, accessToken)
            .apply()
    }

    fun getAccessToken(): String? {
        return preferences.getString(KEY_ACCESS_TOKEN, null)
    }

    fun saveUserName(name: String) {
        preferences.edit()
            .putString(KEY_USER_NAME, name)
            .apply()
    }

    fun getUserName(): String? {
        return preferences.getString(KEY_USER_NAME, null)
    }

    fun clearPreferences() {
        preferences.edit().clear().apply()
    }
}