package com.saravanamarriagebureau.ui

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.saravanamarriagebureau.ui.navigation.Routes
import com.saravanamarriagebureau.ui.screens.AdminPanelScreen
import com.saravanamarriagebureau.ui.screens.AdminVoiceScreen
import com.saravanamarriagebureau.ui.screens.ContactsLockedScreen
import com.saravanamarriagebureau.ui.screens.ForgotPasswordScreen
import com.saravanamarriagebureau.ui.screens.HomeScreen
import com.saravanamarriagebureau.ui.screens.InterestSentScreen
import com.saravanamarriagebureau.ui.screens.InterestsScreen
import com.saravanamarriagebureau.ui.screens.InvoiceScreen
import com.saravanamarriagebureau.ui.screens.LoginScreen
import com.saravanamarriagebureau.ui.screens.MatchFixedScreen
import com.saravanamarriagebureau.ui.screens.MatchesScreen
import com.saravanamarriagebureau.ui.screens.MembershipScreen
import com.saravanamarriagebureau.ui.screens.OfferScreen
import com.saravanamarriagebureau.ui.screens.ParentsTalkScreen
import com.saravanamarriagebureau.ui.screens.PrivacyScreen
import com.saravanamarriagebureau.ui.screens.ProfileCreationScreen
import com.saravanamarriagebureau.ui.screens.ProfileViewScreen
import com.saravanamarriagebureau.ui.screens.RegisterScreen
import com.saravanamarriagebureau.ui.screens.SplashScreen
import com.saravanamarriagebureau.ui.screens.VoiceBioScreen
import com.saravanamarriagebureau.ui.screens.WelcomeScreen
import com.saravanamarriagebureau.ui.viewmodel.MatchesViewModel

@Composable
fun SmbNavigationApp() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.Splash
    ) {
        composable(Routes.Splash) {
            SplashScreen (navController = navController)
        }

        composable(Routes.Welcome) {
            WelcomeScreen(
                onGetStarted = { navController.navigate(Routes.Register) },
                onLogin = { navController.navigate(Routes.Login) }
            )
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
                onNext = { navController.navigate(Routes.Offer) },
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

        composable(Routes.Home) {
            HomeScreen(
                onMatches = { navController.navigate(Routes.Matches) },
                onProfile = { navController.navigate(Routes.Profile) },
                onPrivacy = { navController.navigate(Routes.Privacy) }
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
            val vm: MatchesViewModel = hiltViewModel()
            MatchesScreen(
                onBack = { navController.popBackStack() },
                onProfile = { navController.navigate(Routes.Profile) },
                onInterestSent = {
                    vm.sendInterest("demo")
                    navController.navigate(Routes.InterestSent)
                }
            )
        }

        composable(Routes.Privacy) {
            PrivacyScreen(onBack = { navController.popBackStack() })
        }

        composable(Routes.InterestSent) {
            InterestSentScreen(
                onMatches = { navController.navigate(Routes.Matches) },
                onHome = { navController.navigate(Routes.Home) { popUpTo(Routes.Home) { inclusive = true } } }
            )
        }

        composable(Routes.MatchFixed) {
            MatchFixedScreen { navController.navigate(Routes.Invoice) }
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
