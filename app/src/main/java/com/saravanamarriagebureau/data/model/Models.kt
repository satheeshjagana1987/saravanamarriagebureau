package com.saravanamarriagebureau.data.model

data class LoginRequest(
    val mobile: String,
    val password: String
)

data class LoginResponse(
    val token: String,
    val userId: String,
    val name: String
)

data class RegisterRequest(
    val profileFor: String,
    val name: String,
    val mobile: String,
    val email: String?,
    val dob: String,
    val location: String
)

data class Profile(
    val id: String,
    val name: String,
    val age: Int,
    val height: String,
    val education: String,
    val profession: String,
    val location: String,
    val verified: Boolean = true,
    val imageUrl: String? = null
)

data class Match(
    val id: String,
    val profile: Profile,
    val matchPercent: Int = 90
)

data class Invoice(
    val invoiceId: String,
    val matchId: String,
    val branch: String,
    val amount: Double,
    val date: String,
    val status: String
)

data class Interest(
    val id: String,
    val name: String,
    val age: Int,
    val education: String,
    val verified: Boolean = true,
    val accept: Boolean = false,
    val reject: Boolean = true,
    val imageUrl: String? = null
)

data class LanguageOption(
    val name: String,     // Display name (e.g., "English")
    val code: String      // ISO language code (e.g., "en")
)

data class Branches(
    val name: String,
    val price: String
)
