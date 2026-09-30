package com.saravanamarriagebureau.data.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.saravanamarriagebureau.ui.navigation.Routes

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

// Primary Bottom Bar Destinations
sealed class BottomBarScreen(val route: String, val title: String, val icon: ImageVector) {
    object Home : BottomBarScreen(Routes.Home, "Home", Icons.Default.Home)
    object Matches : BottomBarScreen(Routes.Matches, "Matches", Icons.Default.Search)
    object Interests : BottomBarScreen(Routes.Interests, "Interests", Icons.Default.Person)
    object ProfileCreation : BottomBarScreen(Routes.ProfileCreation, "Profile", Icons.Default.Person)
}

// Secondary Side Drawer Destinations
sealed class DrawerScreen(val route: String, val title: String, val icon: ImageVector) {
    object ChangeLanguage : DrawerScreen(Routes.ChangeLanguage, "ChangeLanguage", Icons.Default.Settings)
    object ChangePassword : DrawerScreen(Routes.ChangePassword, "ChangePassword", Icons.Default.Info)
    object Logout : DrawerScreen(Routes.Logout, "Logout", Icons.AutoMirrored.Filled.ExitToApp)
}
