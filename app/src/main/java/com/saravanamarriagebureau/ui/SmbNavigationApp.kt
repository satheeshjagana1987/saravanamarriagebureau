package com.saravanamarriagebureau.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.saravanamarriagebureau.ui.navigation.Routes
import com.saravanamarriagebureau.ui.screens.ForgotPasswordScreen
import com.saravanamarriagebureau.ui.screens.LoginScreen
import com.saravanamarriagebureau.ui.screens.MainScreenWithHamburger
import com.saravanamarriagebureau.ui.screens.RegisterScreen
import com.saravanamarriagebureau.ui.screens.SplashScreen
import com.saravanamarriagebureau.ui.screens.WelcomeScreen
import com.saravanamarriagebureau.ui.screens.admin.AdminDestination
import com.saravanamarriagebureau.ui.screens.admin.AdminLandingScreen

@Composable
fun SmbNavigationApp(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.Splash,
        modifier = modifier
    ) {
        composable(Routes.Splash) {
            SplashScreen(
                onFinished = {

                    navController.navigate(Routes.Welcome) {

                        popUpTo(Routes.Splash) {
                            inclusive = true
                        }

                        launchSingleTop = true
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
                onLogin = { navController.navigate(Routes.Admin) },
                onRegister = { navController.navigate(Routes.Register) },
                onPrivacy = { },
                onForgot = { navController.navigate(Routes.ForgotPassword) }
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

        composable(Routes.Main) {
            MainScreenWithHamburger()
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
