package com.saravanamarriagebureau.ui.screens.admin

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CardMembership
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.Interests
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.QueryStats
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import com.saravanamarriagebureau.R
import com.saravanamarriagebureau.ui.navigation.Routes

sealed class AdminDestination(
    val route: String,
    @StringRes val label: Int,
    val icon: ImageVector
) {
    data object Dashboard : AdminDestination(
        route = Routes.AdminDashboard,
        label = R.string.dashboard,
        icon = Icons.Default.Dashboard
    )

    data object Users : AdminDestination(
        route = "users",
        label = R.string.users,
        icon = Icons.Default.People
    )

    data object Memberships : AdminDestination(
        route = "memberships",
        label = R.string.plans,
        icon = Icons.Default.CardMembership
    )

    data object Interests : AdminDestination(
        route = "interests",
        label = R.string.interests,
        icon = Icons.Default.Interests
    )

    data object Enquiries : AdminDestination(
        route = "enquiries",
        label =R.string.enquiries,
        icon = Icons.Default.QueryStats
    )

    data object Settings : AdminDestination(
        route = "settings",
        label = R.string.settings,
        icon = Icons.Default.Settings
    )
}