package com.saravanamarriagebureau.util

import android.content.Context
import android.content.SharedPreferences
import com.saravanamarriagebureau.util.Constants.KEY_ACCESS_TOKEN
import com.saravanamarriagebureau.util.Constants.KEY_IS_LOGGED_IN
import com.saravanamarriagebureau.util.Constants.KEY_MEDIA_ID
import com.saravanamarriagebureau.util.Constants.KEY_MEDIA_URL
import com.saravanamarriagebureau.util.Constants.KEY_REFRESH_TOKEN
import com.saravanamarriagebureau.util.Constants.KEY_REMEMBER_ME
import com.saravanamarriagebureau.util.Constants.KEY_RESET_TOKEN
import com.saravanamarriagebureau.util.Constants.KEY_USER_ID
import com.saravanamarriagebureau.util.Constants.KEY_USER_NAME
import com.saravanamarriagebureau.util.Constants.KEY_USER_TYPE
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

    fun saveRefreshToken(refreshToken: String) {
        preferences.edit()
            .putString(KEY_REFRESH_TOKEN, refreshToken)
            .apply()
    }

    fun getRefreshToken(): String? {
        return preferences.getString(KEY_REFRESH_TOKEN, null)
    }

    fun saveLoginStatus(isLoggedIn: Boolean) {
        preferences.edit()
            .putBoolean(KEY_IS_LOGGED_IN, isLoggedIn)
            .apply()
    }

    fun isLoggedIn(): Boolean {
        return preferences.getBoolean(KEY_IS_LOGGED_IN, false)
    }

    fun isRememberMe(rememberMe: Boolean) {
        preferences.edit()
            .putBoolean(KEY_REMEMBER_ME, rememberMe)
            .apply()
    }

    fun isRememberMe(): Boolean {
        return preferences.getBoolean(KEY_REMEMBER_ME, false)
    }

    fun saveAccessToken(accessToken: String) {
        preferences.edit()
            .putString(KEY_ACCESS_TOKEN, accessToken)
            .apply()
    }

    fun getAccessToken(): String? {
        return preferences.getString(KEY_ACCESS_TOKEN, null)
    }

    fun saveResetToken(resetToken: String) {
        preferences.edit()
            .putString(KEY_RESET_TOKEN, resetToken)
            .apply()
    }

    fun getResetToken(): String? {
        return preferences.getString(KEY_RESET_TOKEN, null)
    }

    fun saveUserType(userType: String) {
        preferences.edit()
            .putString(KEY_USER_TYPE, userType)
            .apply()
    }

    fun getUserType(): String? {
        return preferences.getString(KEY_USER_TYPE, null)
    }

    fun saveMediaURL(mediaUrl: String) {
        preferences.edit()
            .putString(KEY_MEDIA_URL, mediaUrl)
            .apply()
    }

    fun getMediaURL(): String? {
        return preferences.getString(KEY_MEDIA_URL, null)
    }

    fun saveMediaId(mediaId: String) {
        preferences.edit()
            .putString(KEY_MEDIA_ID, mediaId)
            .apply()
    }

    fun getMediaId(): String? {
        return preferences.getString(KEY_MEDIA_ID, null)
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