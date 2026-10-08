package com.saravanamarriagebureau.ui.screens.admin

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun AdminBottomBar(
    navController: NavHostController,
    destinations: List<AdminDestination>
) {

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    NavigationBar {

        destinations.forEach { destination ->

            NavigationBarItem(
                selected = currentRoute == destination.route,

                onClick = {
                    navController.navigate(destination.route) {

                        // Avoid building multiple copies
                        // of the same destination
                        popUpTo(
                            navController.graph.startDestinationId
                        ) {
                            saveState = true
                        }

                        launchSingleTop = true
                        restoreState = true
                    }
                },

                icon = {
                    Icon(
                        imageVector = destination.icon,
                        contentDescription = destination.label
                    )
                },

                label = {
                    Text(
                        text = destination.label,
                        maxLines = 1
                    )
                }
            )
        }
    }
}