package com.saravanamarriagebureau.ui.screens.admin

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.saravanamarriagebureau.ui.navigation.Routes
import com.saravanamarriagebureau.ui.navigation.profileRoute
import com.saravanamarriagebureau.ui.screens.admin.interests.AdminInterestsScreen
import com.saravanamarriagebureau.ui.screens.admin.users.AdminUserDetailsScreen
import com.saravanamarriagebureau.ui.screens.admin.users.AdminUsersScreen
import com.saravanamarriagebureau.ui.screens.userdashboard.LogoutDialog
import timber.log.Timber

@Composable
fun AdminLandingScreen(
    onLogout: () -> Unit
) {

    val adminNavController = rememberNavController()

    var showLogoutDialog by remember {
        mutableStateOf(false)
    }

    val destinations = listOf(
        AdminDestination.Users,
        AdminDestination.Interests,
        AdminDestination.Payments,
        AdminDestination.Settings
    )

    Scaffold(
        bottomBar = {
            AdminBottomBar(
                navController = adminNavController,
                destinations = destinations
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = adminNavController,
            startDestination = AdminDestination.Dashboard.route,
            modifier = Modifier.padding(innerPadding)
        ) {

            composable(AdminDestination.Dashboard.route) {
                AdminDashboardScreen(
                    onNavigate = { route ->
                        adminNavController.navigate(route)
                    }
                )
            }

            composable(AdminDestination.Interests.route) {
                AdminInterestsScreen(
                    onInterestItemClick = { interest ->
                        Timber.e("profile  InterestsScreen ==>  ${interest.type}")
                        adminNavController.navigate(
                            profileRoute(profileId = interest.id)
                        )
                    },
                )
            }

            composable(AdminDestination.Payments.route) {
                AdminPaymentsScreen()
            }

            composable(AdminDestination.Settings.route) {
                AdminSettingsScreen(
                    onBack = {
                        adminNavController.popBackStack()
                    },
                    onLogout = {
                        showLogoutDialog = true
                    }
                )
            }

            composable(Routes.Users) {
                AdminUsersScreen(
                    onProfile = { profile ->
                        adminNavController.navigate(
                            profileRoute(profile.id)
                        )
                    }
                )
            }

            composable(
                route = "${Routes.Profile}/{profileId}/{type}"
            ) { backStackEntry ->

                val profileId = backStackEntry.arguments
                    ?.getString("profileId")
                    ?: return@composable


                AdminUserDetailsScreen(
                    profileId = profileId,
                    onBack = {
                        adminNavController.popBackStack()
                    },

                    )
            }

            composable(Routes.PendingPayments) {
                AdminPaymentsScreen()
            }

            composable(Routes.PendingProfiles) {
                AdminUsersScreen(
                    onProfile = { profile ->
                        adminNavController.navigate(
                            profileRoute(profile.id)
                        )
                    }
                )
            }

            composable(Routes.MutualInterests) {
                AdminInterestsScreen(
                    onInterestItemClick = { interest ->
                        Timber.e("profile  InterestsScreen ==>  ${interest.type}")
                        adminNavController.navigate(
                            profileRoute(profileId = interest.id)
                        )
                    },
                )
            }
        }

        if (showLogoutDialog) {
            LogoutDialog(
                onLogout = {
                    showLogoutDialog = false
                    onLogout()
                },
                onDismiss = {
                    showLogoutDialog = false
                }
            )
        }
    }
}

