package com.saravanamarriagebureau.ui.screens.admin

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.saravanamarriagebureau.ui.SmbNavigationApp
import com.saravanamarriagebureau.ui.navigation.Routes
import com.saravanamarriagebureau.ui.navigation.profileRoute
import com.saravanamarriagebureau.ui.screens.interests.InterestsScreen
import com.saravanamarriagebureau.ui.screens.profile.ProfileViewScreen
import timber.log.Timber

@Composable
fun AdminLandingScreen() {

    val adminNavController = rememberNavController()

    val destinations = listOf(
        AdminDestination.Dashboard,
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
                            profileRoute(profileId = interest.id, type = interest.type.name)
                        )
                    },
                )
            }

            composable(AdminDestination.Payments.route) {
               AdminPaymentsScreen()
            }

            composable(AdminDestination.Settings.route) {
                AdminSettingsScreen()
            }

            composable(Routes.Users) {
                AdminUsersScreen(
                    onProfile = { profile ->
                        adminNavController.navigate(
                            profileRoute(profile.id, type = "")
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

                val profileType = backStackEntry.arguments
                    ?.getString("type")
                    ?: return@composable

                AdminUserDetailsScreen(
                    profileId = profileId,
                    profileType = profileType,

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
                            profileRoute(profile.id, type = "")
                        )
                    }
                )
            }

            composable(Routes.MutualInterests) {
                AdminInterestsScreen(
                    onInterestItemClick = { interest ->
                        Timber.e("profile  InterestsScreen ==>  ${interest.type}")
                        adminNavController.navigate(
                            profileRoute(profileId = interest.id, type = interest.type.name)
                        )
                    },
                )
            }
        }
    }
}

