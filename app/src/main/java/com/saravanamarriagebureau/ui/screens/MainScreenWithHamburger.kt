package com.saravanamarriagebureau.ui.screens

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
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
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    // Monitor backstack to know exactly which screen is active
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    // Separate UI menu lists
    val bottomItems = listOf(
        BottomBarScreen.Home,
        BottomBarScreen.Matches,
        BottomBarScreen.Interests,
        BottomBarScreen.Profile
    )
    val drawerItems = listOf(DrawerScreen.ChangePassword, DrawerScreen.ChangeLanguage)

    // 2. Dynamically determine the title based on the active route
    val appBarTitle = when (currentDestination?.route) {
        BottomBarScreen.Home.route -> BottomBarScreen.Home.title
        BottomBarScreen.Matches.route -> BottomBarScreen.Matches.title
        BottomBarScreen.Profile.route -> BottomBarScreen.Profile.title
        BottomBarScreen.Interests.route -> BottomBarScreen.Interests.title
        else -> "My App" // Default fallback title
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Text("App Menu", modifier = Modifier.padding(16.dp))
                HorizontalDivider()

                // 🛑 SIDE DRAWER MENU LOOP
                drawerItems.forEach { screen ->
                    val isSelected =
                        currentDestination?.hierarchy?.any { it.route == screen.route } == true

                    NavigationDrawerItem(
                        icon = { Icon(screen.icon, contentDescription = screen.title) },
                        label = { Text(screen.title) },
                        selected = isSelected,
                        onClick = {
                            scope.launch { drawerState.close() }
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                    )
                }
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = appBarTitle) },
                    navigationIcon = {
                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
                            Icon(Icons.Default.Menu, contentDescription = "Open Drawer")
                        }
                    }
                )
            },
            bottomBar = {
                // 🛑 BOTTOM NAVIGATION BAR LOOP
                NavigationBar {
                    bottomItems.forEach { screen ->
                        val isSelected =
                            currentDestination?.hierarchy?.any { it.route == screen.route } == true

                        NavigationBarItem(
                            icon = { Icon(screen.icon, contentDescription = screen.title) },
                            label = { Text(screen.title) },
                            selected = isSelected,
                            onClick = {
                                navController.navigate(screen.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
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
        ) { paddingValues ->
            // Combined Destination Graph
            NavHost(
                navController = navController,
                startDestination = BottomBarScreen.Home.route,
                modifier = Modifier.padding(paddingValues)
            ) {
                // Bottom Bar Screens
                composable(BottomBarScreen.Home.route) {
                    MatchFixedScreen(
                        onBack = { navController.popBackStack() },
                    )
                }
                composable(BottomBarScreen.Matches.route) {
                    MatchesScreen(
                        onBack = { navController.popBackStack() },
                        onProfile = { navController.navigate(Routes.Profile) },
                        onInterestSent = {
                            navController.navigate(Routes.InterestSent)
                        }
                    )
                }
                composable(BottomBarScreen.Profile.route) {
                    ProfileCreationScreen(
                        onSubmit = { navController.navigate(Routes.Home) },
                        onBack = { navController.popBackStack() }
                    )
                }

                composable(BottomBarScreen.Interests.route) {
                    InterestsScreen(
                        onBack = { navController.popBackStack() },
                        onInterestItemClick = { navController.navigate(Routes.Profile) }
                    )
                }

                // Drawer Screens
                composable(DrawerScreen.ChangeLanguage.route) {
                    ChangeLanguageScreen(
                        onBack = { navController.popBackStack() },
                    )
                }
                composable(DrawerScreen.ChangePassword.route) {
                    ChangePasswordScreen(
                        onBack = { navController.popBackStack() },
                    )
                }

                composable(Routes.Splash) {
                    SplashScreen (navController = navController)
                }

                composable(Routes.Welcome) {
                    WelcomeScreen(
                        onGetStarted = { navController.navigate(Routes.Register) },
                        onLogin = { navController.navigate(Routes.Login) }
                    )
                }

                composable(Routes.Main) {
                    MainScreenWithHamburger()
                }

                composable(Routes.Login) {
                    LoginScreen(
                        onLogin = { navController.navigate(Routes.Home) },
                        onRegister = { navController.navigate(Routes.Register) },
                        onPrivacy = { }
                    )
                }

                composable(Routes.ForgotPassword) {
                    ForgotPasswordScreen(
                        onReset = { navController.navigate(Routes.Login) },
                        onBack = { navController.popBackStack() }
                    )
                }

                composable(Routes.Register) {
                    RegisterScreen(
                        onNext = { navController.navigate(Routes.Login) },
                        onBack = { navController.popBackStack() }
                    )
                }

                composable(Routes.Offer) {
                    OfferScreen { navController.navigate(Routes.Membership) }
                }

                composable(Routes.Membership) {
                    MembershipScreen(
                        onContinue = { navController.navigate(Routes.ProfileCreation) },
                        onBack = { navController.popBackStack() }
                    )
                }

                composable(Routes.ProfileCreation) {
                    ProfileCreationScreen(
                        onSubmit = { navController.navigate(Routes.Home) },
                        onBack = { navController.popBackStack() }
                    )
                }

                composable(Routes.VoiceBio) {
                    VoiceBioScreen(
                        onNext = { navController.navigate(Routes.ParentsTalk) },
                        onBack = { navController.popBackStack() }
                    )
                }

                composable(Routes.ParentsTalk) {
                    ParentsTalkScreen(
                        onNext = { navController.navigate(Routes.Home) },
                        onBack = { navController.popBackStack() }
                    )
                }

                composable(Routes.Profile) {
                    ProfileViewScreen(
                        onBack = { navController.popBackStack() },
                        onInterest = { navController.navigate(Routes.InterestSent) },
                        onVoice = { navController.navigate(Routes.AdminVoice) }
                    )
                }

                composable(Routes.Interests) {
                    InterestsScreen(
                        onBack = { navController.popBackStack() },
                        onInterestItemClick = { navController.navigate(Routes.Profile) }
                    )
                }

                composable(Routes.AdminVoice) {
                    AdminVoiceScreen(onBack = { navController.popBackStack() })
                }

                composable(Routes.Matches) {
                    MatchesScreen(
                        onBack = { navController.popBackStack() },
                        onProfile = { navController.navigate(Routes.Profile) },
                        onInterestSent = {
                            navController.navigate(Routes.InterestSent)
                        }
                    )
                }

                composable(Routes.Privacy) {
                    PrivacyScreen(onBack = { navController.popBackStack() })
                }

                composable(Routes.InterestSent) {
                    InterestSentDialogScreen(
                        onMatches = { navController.navigate(Routes.Matches) },
                        onHome = { navController.navigate(Routes.Home) { popUpTo(Routes.Home) { inclusive = true } } }
                    )
                }

                composable(Routes.Invoice) {
                    InvoiceScreen(
                        onPay = { navController.navigate(Routes.ContactsLocked) },
                        onBack = { navController.popBackStack() }
                    )
                }

                composable(Routes.ContactsLocked) {
                    ContactsLockedScreen(onBack = { navController.popBackStack() })
                }

                composable(Routes.AdminPanel) {
                    AdminPanelScreen()
                }
            }
        }
    }
}

