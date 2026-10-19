package com.saravanamarriagebureau.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.MutatePriority
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.saravanamarriagebureau.ui.navigation.Routes
import com.saravanamarriagebureau.ui.navigation.forgotPassword
import com.saravanamarriagebureau.ui.screens.forgetpassword.ForgotPasswordScreen
import com.saravanamarriagebureau.ui.screens.login.LoginScreen
import com.saravanamarriagebureau.ui.screens.userdashboard.MainScreenWithHamburger
import com.saravanamarriagebureau.ui.screens.registration.RegisterScreen
import com.saravanamarriagebureau.ui.screens.splash.SplashScreen
import com.saravanamarriagebureau.ui.screens.splash.WelcomeScreen
import com.saravanamarriagebureau.ui.screens.admin.AdminLandingScreen
import com.saravanamarriagebureau.util.AppPreferences
import com.saravanamarriagebureau.util.Constants.KEY_USER_TYPE

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SmbNavigationApp(
    modifier: Modifier = Modifier,
    appPreferences: AppPreferences
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.Splash,
        modifier = modifier
    ) {
        composable(Routes.Splash) {
            SplashScreen(
                onFinished = {
                    if (appPreferences.isLoggedIn() && appPreferences.getUserType() == KEY_USER_TYPE) {
                        navController.navigate(Routes.Main) {

                            popUpTo(Routes.Splash) {
                                inclusive = true
                            }

                            launchSingleTop = true
                        }
                    } else {
                        navController.navigate(Routes.Welcome) {

                            popUpTo(Routes.Splash) {
                                inclusive = true
                            }

                            launchSingleTop = true
                        }
                    }
                }
            )
        }

        composable(Routes.Welcome) {
            WelcomeScreen(
                onGetStarted = { navController.navigate(Routes.Register) },
                onLogin = { navController.navigate(Routes.Login) }
            )
        }

        composable(Routes.Login) {
            LoginScreen(
                onLogin = { navController.navigate(Routes.Main) },
                onRegister = { navController.navigate(Routes.Register) },
                onPrivacy = { },
                onForgot = { mobile ->
                    navController.navigate(
                        forgotPassword(mobile)
                    )
                }
            )
        }

        composable(
            route = "${Routes.ForgotPassword}/{mobile}",
            arguments = listOf(
                navArgument("mobile") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->

            val mobile = backStackEntry.arguments
                ?.getString("mobile")
                .orEmpty()

            ForgotPasswordScreen(
                mobile = mobile,
                onReset = {
                    navController.navigate(Routes.Login) {
                        popUpTo(Routes.Login) {
                            inclusive = false
                        }
                        launchSingleTop = true
                    }
                },

                onBack = {
                    val returned = navController.popBackStack()

                    if (!returned) {
                        navController.navigate(Routes.Login) {
                            launchSingleTop = true
                        }
                    }
                }
            )

        }

        composable(Routes.Register) {
            RegisterScreen(
                onNext = { navController.navigate(Routes.Login) },
                onBack = { navController.popBackStack() }
            )
        }

        composable(Routes.Main) {
            MainScreenWithHamburger(
                onLogout = {
                    navController.navigate(Routes.Login) {
                        appPreferences.saveLoginStatus(isLoggedIn = false)
                        popUpTo(navController.graph.id) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                }
            )
        }

        composable(Routes.Admin) {
            AdminLandingScreen()
        }

//        composable(Routes.Offer) {
//            OfferScreen { navController.navigate(Routes.Membership) }
//        }
//
//        composable(Routes.Membership) {
//            MembershipScreen(
//                onContinue = { navController.navigate(Routes.ProfileCreation) },
//                onBack = { navController.popBackStack() }
//            )
//        }
//
//        composable(Routes.ProfileCreation) {
//            ProfileCreationScreen(
//                onSubmit = { navController.navigate(Routes.Home) },
//                onBack = { navController.popBackStack() }
//            )
//        }
//
//        composable(Routes.VoiceBio) {
//            VoiceBioScreen(
//                onNext = { navController.navigate(Routes.ParentsTalk) },
//                onBack = { navController.popBackStack() }
//            )
//        }
//
//        composable(Routes.ParentsTalk) {
//            ParentsTalkScreen(
//                onNext = { navController.navigate(Routes.Home) },
//                onBack = { navController.popBackStack() }
//            )
//        }
//
//        composable(Routes.Home) {
//            HomeScreen(
//                onMatches = { navController.navigate(Routes.Matches) },
//                onProfile = { navController.navigate(Routes.Profile) },
//                onPrivacy = { navController.navigate(Routes.Privacy) }
//            )
//        }
//
//        composable(Routes.Profile) {
//            ProfileViewScreen(
//                onBack = { navController.popBackStack() },
//                onInterest = { navController.navigate(Routes.InterestSent) },
//                onVoice = { navController.navigate(Routes.AdminVoice) }
//            )
//        }
//
//        composable(Routes.Interests) {
//            InterestsScreen(
//                onBack = { navController.popBackStack() },
//                onInterestItemClick = { navController.navigate(Routes.Profile) }
//            )
//        }
//
//        composable(Routes.AdminVoice) {
//            AdminVoiceScreen(onBack = { navController.popBackStack() })
//        }
//
//        composable(Routes.Matches) {
//            MatchesScreen(
//                onBack = { navController.popBackStack() },
//                onProfile = { navController.navigate(Routes.Profile) },
//                onInterestSent = {
//                    navController.navigate(Routes.InterestSent)
//                }
//            )
//        }
//
//        composable(Routes.Privacy) {
//            PrivacyScreen(onBack = { navController.popBackStack() })
//        }
//
//        composable(Routes.InterestSent) {
//            InterestSentDialogScreen(
//                onMatches = { navController.navigate(Routes.Matches) },
//                onHome = { navController.navigate(Routes.Home) { popUpTo(Routes.Home) { inclusive = true } } }
//            )
//        }
//
//        composable(Routes.MatchFixed) {
//            MatchFixedScreen(
//                onBack = {navController.popBackStack()},
//            )
//        }
//
//        composable(Routes.Invoice) {
//            InvoiceScreen(
//                onPay = { navController.navigate(Routes.ContactsLocked) },
//                onBack = { navController.popBackStack() }
//            )
//        }
//
//        composable(Routes.ContactsLocked) {
//            ContactsLockedScreen(onBack = { navController.popBackStack() })
//        }
//
//        composable(Routes.AdminPanel) {
//            AdminPanelScreen()
//        }
    }
}
