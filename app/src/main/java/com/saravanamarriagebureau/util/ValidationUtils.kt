package com.saravanamarriagebureau.util

object ValidationUtils {

    fun validateName(name: String): String? {
        return when {
            name.isBlank() ->
                "$name is required"

            name.trim().length < 2 ->
                "$name must be at least 2 characters"

            !name.trim().matches(Regex("^[\\p{L}]+(?:[ '\\-][\\p{L}]+)*$")) ->
                "Enter a valid $name"

            else -> null
        }
    }

    fun validateEmail(email: String): String? {
        return when {
            email.isBlank() ->
                "Email is required"

            !android.util.Patterns.EMAIL_ADDRESS
                .matcher(email.trim())
                .matches() ->
                "Enter a valid email address"

            else -> null
        }
    }

    fun validateMobile(mobile: String): String? {
        return when {
            mobile.isBlank() ->
                "Mobile number is required"

            !mobile.matches(Regex("^[6-9]\\d{9}$")) ->
                "Enter a valid 10-digit mobile number"

            else -> null
        }
    }

    fun validatePassword(password: String): String? {
        return when {
            password.isBlank() ->
                "Password is required"

            password.length < 8 ->
                "Password must contain at least 8 characters"

            else -> null
        }
    }

    fun validateConfirmPassword(
        password: String,
        confirmPassword: String
    ): String? {
        return when {
            confirmPassword.isBlank() ->
                "Confirm password is required"

            password != confirmPassword ->
                "Passwords do not match"

            else -> null
        }
    }

    fun validateField(field: String): String? {
        return when {
            field.isBlank() ->
                "$field is required"

            else -> null
        }
    }
}