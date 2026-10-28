package com.saravanamarriagebureau.ui.screens.userdashboard

import android.os.Build
import androidx.annotation.RequiresApi
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.saravanamarriagebureau.data.model.BottomBarScreen
import com.saravanamarriagebureau.data.model.DrawerScreen
import com.saravanamarriagebureau.ui.navigation.Routes
import com.saravanamarriagebureau.ui.navigation.paymentRoute
import com.saravanamarriagebureau.ui.navigation.profileRoute
import com.saravanamarriagebureau.ui.screens.AdminVoiceScreen
import com.saravanamarriagebureau.ui.screens.ContactsLockedScreen
import com.saravanamarriagebureau.ui.screens.PrivacyScreen
import com.saravanamarriagebureau.ui.screens.changepassword.ChangePasswordScreen
import com.saravanamarriagebureau.ui.screens.interests.InterestSentDialogScreen
import com.saravanamarriagebureau.ui.screens.interests.InterestsScreen
import com.saravanamarriagebureau.ui.screens.invoice.InvoiceScreen
import com.saravanamarriagebureau.ui.screens.invoice.MatchFixedScreen
import com.saravanamarriagebureau.ui.screens.matches.MatchesScreen
import com.saravanamarriagebureau.ui.screens.profile.ProfileCreationScreen
import com.saravanamarriagebureau.ui.screens.profile.ProfileViewScreen
import kotlinx.coroutines.launch
import timber.log.Timber

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreenWithHamburger(
    onLogout: () -> Unit
) {
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
        BottomBarScreen.Search,
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

    val isProfileScreen =
        currentDestination?.hierarchy?.any {
            it.route == Routes.Profile
        } == true

    val showMainNavigation =
        currentRoute !in setOf(
            DrawerScreen.ChangeLanguage.route,
            DrawerScreen.ChangePassword.route,
            Routes.InterestSent,
            Routes.ChoosePlan,
            Routes.ChangeLanguage,
            Routes.MatchFixed,
            Routes.Invoice,
            Routes.Profile,
        ) && !isProfileScreen

    // -----------------------------------------
    // App bar title
    // -----------------------------------------

    val appBarTitle = when (currentRoute) {

        BottomBarScreen.Home.route ->
            BottomBarScreen.Home.title

        BottomBarScreen.Matches.route ->
            BottomBarScreen.Matches.title

        BottomBarScreen.Search.route ->
            BottomBarScreen.Search.title

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
                    HomeScreen(
                        onViewPlans = {
                            navController.navigate(Routes.ChoosePlan)
                        }
                    )
                }

                // =================================================
                // MATCHES
                // =================================================

                composable(BottomBarScreen.Matches.route) {

                    MatchesScreen(
                        onProfile = { profile ->
                            navController.navigate(
                                profileRoute(profile.id)
                            )
                        },

                        onInterestSent = {
                            navController.navigate(Routes.InterestSent)
                        },

                        onInterestRemoved = {
                            navController.navigate(
                                BottomBarScreen.Matches.route
                            ) {

                                popUpTo(
                                    BottomBarScreen.Matches.route
                                ) {
                                    inclusive = true
                                }
                            }
                        }
                    )
                }

                composable(BottomBarScreen.Search.route) {
                    SearchScreen()
                }

                // =================================================
                // INTERESTS
                // =================================================

                composable(BottomBarScreen.Interests.route) {

                    InterestsScreen(
                        onInterestItemClick = { interest ->
                            navController.navigate(
                                profileRoute(profileId = interest.id)
                            )
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
//                            navController.popBackStack()
                        }
                    )
                }

                // =================================================
                // PROFILE VIEW
                // =================================================

                composable(
                    route = Routes.Profile,
                    arguments = listOf(
                        navArgument("profileId") {
                            type = NavType.StringType
                        }
                    )
                ) { backStackEntry ->

                    val profileId =
                        backStackEntry.arguments?.getString("profileId").orEmpty()
                    Timber.e("profileId==>$profileId")

                    ProfileViewScreen(
                        profileId = profileId,
                        profileType = "",

                        onBack = {
                            navController.popBackStack()
                        },

                        onInterest = {
                            navController.navigate(Routes.InterestSent)
                        },

                        onMatchFixed = {
                            navController.navigate(Routes.MatchFixed)
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
                        },
                        onLogin = onLogout
                    )
                }

                composable(
                    DrawerScreen.Logout.route
                ) {
                    LogoutDialog(
                        onLogout = onLogout,
                        onDismiss = {
                            navController.navigate(Routes.Home)
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

                // Choose Plan
                composable(Routes.ChoosePlan) {
                    ChooseYourPlanScreen(
                        onBack = {
                            navController.popBackStack()
                        },
                        onContinue = { selectedPlan ->
                            Timber.e("selectedPlan ==> $selectedPlan")
                            navController.navigate(
                                paymentRoute(selectedPlan = selectedPlan)
                            )
                        }
                    )
                }

                // Match Fixed
                composable(Routes.MatchFixed) {
                    MatchFixedScreen(
                        onBack = {
                            navController.popBackStack()
                        },
                        onInvoice = {
                            navController.navigate(
                                paymentRoute(selectedPlan = "")
                            )
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

                composable(
                    route = Routes.Invoice,
                    arguments = listOf(
                        navArgument("selectedPlan") {
                            type = NavType.StringType
                        }
                    )
                ) { backStackEntry ->

                    val selectedPlan =
                        backStackEntry.arguments?.getString("selectedPlan") ?: ""

                    InvoiceScreen(
                        onPay = {
                            navController.navigate(
                                Routes.ContactsLocked
                            )
                        },

                        onBack = {
                            navController.popBackStack()
                        },
                        selectedPlan = selectedPlan
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

            }
        }
    }
}




