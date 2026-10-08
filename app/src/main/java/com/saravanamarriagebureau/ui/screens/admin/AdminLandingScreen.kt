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

@Composable
fun AdminLandingScreen() {

    val adminNavController = rememberNavController()

    val destinations = listOf(
        AdminDestination.Dashboard,
        AdminDestination.Users,
        AdminDestination.Matches,
        AdminDestination.Interests,
        AdminDestination.Payments
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
                // DashboardScreen()
            }

            composable(AdminDestination.Users.route) {
                // UserManagementScreen()
            }

            composable(AdminDestination.Matches.route) {
                // MatchesManagementScreen()
            }

            composable(AdminDestination.Interests.route) {
                // InterestManagementScreen()
            }

            composable(AdminDestination.Payments.route) {
                // PaymentManagementScreen()
            }
        }
    }
}

