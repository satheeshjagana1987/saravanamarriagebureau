package com.saravanamarriagebureau.ui.screens.admin

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Interests
import androidx.compose.material.icons.filled.Payments
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.saravanamarriagebureau.ui.navigation.Routes

sealed class AdminDestination (
    val route: String,
    val label: String,
    val icon: ImageVector
) {
    data object Dashboard : AdminDestination(
        route = Routes.AdminDashboard,
        label = "Dashboard",
        icon = Icons.Default.Dashboard
    )

    data object Users : AdminDestination(
        route = "users",
        label = "Users",
        icon = Icons.Default.People
    )

    data object Matches : AdminDestination(
        route = "matches",
        label = "Matches",
        icon = Icons.Default.Favorite
    )

    data object Interests : AdminDestination(
        route = "interests",
        label = "Interests",
        icon = Icons.Default.Interests
    )

    data object Payments : AdminDestination(
        route = "payments",
        label = "Payments",
        icon = Icons.Default.Payments
    )

    data object Settings : AdminDestination(
        route = "settings",
        label = "Settings",
        icon = Icons.Default.Settings
    )
}