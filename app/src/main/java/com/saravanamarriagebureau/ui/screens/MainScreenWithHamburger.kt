package com.saravanamarriagebureau.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.saravanamarriagebureau.data.model.BottomBarScreen
import com.saravanamarriagebureau.data.model.DrawerScreen
import com.saravanamarriagebureau.ui.navigation.Routes
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreenWithHamburger() {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(
        initialValue = DrawerValue.Closed
    )

    val scope = rememberCoroutineScope()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val currentRoute = currentDestination?.route

    LaunchedEffect(currentRoute) {
        drawerState.close()
    }

    // -----------------------------------------
    // Bottom navigation
    // -----------------------------------------

    val bottomItems = listOf(
        BottomBarScreen.Home,
        BottomBarScreen.Matches,
        BottomBarScreen.Interests,
        BottomBarScreen.ProfileCreation
    )

    // -----------------------------------------
    // Drawer navigation
    // -----------------------------------------

    val drawerItems = listOf(
        DrawerScreen.ChangePassword,
        DrawerScreen.ChangeLanguage,
        DrawerScreen.Logout,

    )

    // -----------------------------------------
    // Screens WITHOUT drawer / bottom bar
    // -----------------------------------------

    val screensWithoutNavigation = setOf(
        Routes.Profile,
        DrawerScreen.ChangeLanguage.route,
        DrawerScreen.ChangePassword.route,
        Routes.InterestSent
    )

    val showMainNavigation =
        currentRoute !in screensWithoutNavigation

    // -----------------------------------------
    // App bar title
    // -----------------------------------------

    val appBarTitle = when (currentRoute) {

        BottomBarScreen.Home.route ->
            BottomBarScreen.Home.title

        BottomBarScreen.Matches.route ->
            BottomBarScreen.Matches.title

        BottomBarScreen.Interests.route ->
            BottomBarScreen.Interests.title

        BottomBarScreen.ProfileCreation.route ->
            BottomBarScreen.ProfileCreation.title

        Routes.Profile ->
            "Profile"

        DrawerScreen.ChangeLanguage.route ->
            "Change Language"

        DrawerScreen.ChangePassword.route ->
            "Change Password"

        Routes.InterestSent ->
            ""

        else ->
            ""
    }

    // =====================================================
    // ONE DRAWER
    // ONE SCAFFOLD
    // ONE NAVHOST
    // =====================================================

    ModalNavigationDrawer(

        drawerState = drawerState,

        drawerContent = {

            // Only show drawer content for main screens
            if (showMainNavigation) {

                ModalDrawerSheet {

                    Text(
                        text = "App Menu",
                        modifier = Modifier.padding(16.dp)
                    )

                    HorizontalDivider()

                    drawerItems.forEach { screen ->

                        val isSelected =
                            currentDestination
                                ?.hierarchy
                                ?.any {
                                    it.route == screen.route
                                } == true

                        NavigationDrawerItem(

                            icon = {
                                Icon(
                                    imageVector = screen.icon,
                                    contentDescription = screen.title
                                )
                            },

                            label = {
                                Text(screen.title)
                            },

                            selected = isSelected,

                            onClick = {

                                scope.launch {
                                    drawerState.close()
                                }

                                navController.navigate(screen.route) {

                                    launchSingleTop = true

                                    restoreState = true
                                }
                            },

                            modifier = Modifier.padding(
                                NavigationDrawerItemDefaults.ItemPadding
                            )
                        )
                    }
                }
            }
        }

    ) {

        // =================================================
        // SCAFFOLD
        // =================================================

        Scaffold(

            // ---------------------------------------------
            // TOP APP BAR
            // ---------------------------------------------

            topBar = {

                // Don't show hamburger on special screens
                if (showMainNavigation) {

                    TopAppBar(

                        title = {
                            Text(appBarTitle)
                        },

                        navigationIcon = {

                            IconButton(
                                onClick = {

                                    scope.launch {
                                        drawerState.open()
                                    }
                                }
                            ) {

                                Icon(
                                    imageVector = Icons.Default.Menu,
                                    contentDescription = "Open Drawer"
                                )
                            }
                        }
                    )

                } else {

                    // Special screens:
                    // NO navigationIcon

                    TopAppBar(
                        title = {
                            Text(appBarTitle)
                        }
                    )
                }
            },

            // ---------------------------------------------
            // BOTTOM BAR
            // ---------------------------------------------

            bottomBar = {

                if (showMainNavigation) {

                    NavigationBar {

                        bottomItems.forEach { screen ->

                            val isSelected =
                                currentDestination
                                    ?.hierarchy
                                    ?.any {
                                        it.route == screen.route
                                    } == true

                            NavigationBarItem(

                                icon = {
                                    Icon(
                                        imageVector = screen.icon,
                                        contentDescription = screen.title
                                    )
                                },

                                label = {
                                    Text(screen.title)
                                },

                                selected = isSelected,

                                onClick = {

                                    navController.navigate(screen.route) {

                                        popUpTo(
                                            navController.graph
                                                .findStartDestination()
                                                .id
                                        ) {
                                            saveState = true
                                        }

                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                }
                            )
                        }
                    }
                }
            }

        ) { paddingValues ->

            // =================================================
            // ONE AND ONLY ONE NAVHOST
            // =================================================

            NavHost(

                navController = navController,

                startDestination = BottomBarScreen.Home.route,

                modifier = Modifier.padding(paddingValues)

            ) {

                // =================================================
                // HOME
                // =================================================

                composable(BottomBarScreen.Home.route) {

                    MatchFixedScreen(
                        onBack = {
                            navController.popBackStack()
                        }
                    )
                }

                // =================================================
                // MATCHES
                // =================================================

                composable(BottomBarScreen.Matches.route) {

                    MatchesScreen(

                        onBack = {
                            navController.popBackStack()
                        },

                        onProfile = {
                            navController.navigate(Routes.Profile)
                        },

                        onInterestSent = {
                            navController.navigate(Routes.InterestSent)
                        }
                    )
                }

                // =================================================
                // INTERESTS
                // =================================================

                composable(BottomBarScreen.Interests.route) {

                    InterestsScreen(

                        onBack = {
                            navController.popBackStack()
                        },

                        onInterestItemClick = {
                            navController.navigate(Routes.Profile)
                        }
                    )
                }

                // =================================================
                // PROFILE CREATION
                // =================================================

                composable(BottomBarScreen.ProfileCreation.route) {

                    ProfileCreationScreen(

                        onSubmit = {
                            navController.navigate(
                                BottomBarScreen.Home.route
                            )
                        },

                        onBack = {
                            navController.popBackStack()
                        }
                    )
                }

                // =================================================
                // PROFILE VIEW
                // =================================================

                composable(Routes.Profile) {

                    ProfileViewScreen(

                        onBack = {
                            navController.popBackStack()
                        },

                        onInterest = {
                            navController.navigate(
                                Routes.InterestSent
                            )
                        },

                        onVoice = {
                            navController.navigate(
                                Routes.AdminVoice
                            )
                        }
                    )
                }

                // =================================================
                // CHANGE LANGUAGE
                // =================================================

                composable(
                    DrawerScreen.ChangeLanguage.route
                ) {

                    ChangeLanguageScreen(

                        onBack = {
                            navController.popBackStack()
                        }
                    )
                }

                // =================================================
                // CHANGE PASSWORD
                // =================================================

                composable(
                    DrawerScreen.ChangePassword.route
                ) {

                    ChangePasswordScreen(

                        onBack = {
                            navController.popBackStack()
                        }
                    )
                }

                // =================================================
                // INTEREST SENT
                // =================================================

                composable(Routes.InterestSent) {

                    InterestSentDialogScreen(

                        onMatches = {

                            navController.navigate(
                                BottomBarScreen.Matches.route
                            )
                        },

                        onHome = {

                            navController.navigate(
                                BottomBarScreen.Home.route
                            ) {

                                popUpTo(
                                    BottomBarScreen.Home.route
                                ) {
                                    inclusive = true
                                }
                            }
                        }
                    )
                }

                // =================================================
                // ADMIN VOICE
                // =================================================

                composable(Routes.AdminVoice) {

                    AdminVoiceScreen(
                        onBack = {
                            navController.popBackStack()
                        }
                    )
                }

                // =================================================
                // PRIVACY
                // =================================================

                composable(Routes.Privacy) {

                    PrivacyScreen(
                        onBack = {
                            navController.popBackStack()
                        }
                    )
                }

                // =================================================
                // INVOICE
                // =================================================

                composable(Routes.Invoice) {

                    InvoiceScreen(

                        onPay = {
                            navController.navigate(
                                Routes.ContactsLocked
                            )
                        },

                        onBack = {
                            navController.popBackStack()
                        }
                    )
                }

                // =================================================
                // CONTACTS LOCKED
                // =================================================

                composable(Routes.ContactsLocked) {

                    ContactsLockedScreen(
                        onBack = {
                            navController.popBackStack()
                        }
                    )
                }

                // =================================================
                // ADMIN PANEL
                // =================================================

                composable(Routes.AdminPanel) {
                    AdminPanelScreen()
                }
            }
        }
    }
}




