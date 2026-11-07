package com.saravanamarriagebureau.ui.screens.admin

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.saravanamarriagebureau.ui.navigation.Routes
import com.saravanamarriagebureau.ui.navigation.adminEditMembershipDetailsRoute
import com.saravanamarriagebureau.ui.navigation.adminEnquiryDetailsRoute
import com.saravanamarriagebureau.ui.navigation.adminInterestDetailsRoute
import com.saravanamarriagebureau.ui.navigation.adminUserDetailsRoute
import com.saravanamarriagebureau.ui.screens.admin.enquiries.AdminEnquiriesScreen
import com.saravanamarriagebureau.ui.screens.admin.enquiries.AdminEnquiryDetailsScreen
import com.saravanamarriagebureau.ui.screens.admin.interests.AdminInterestDetailsScreen
import com.saravanamarriagebureau.ui.screens.admin.interests.AdminInterestsScreen
import com.saravanamarriagebureau.ui.screens.admin.membership.AddMembershipPlanScreen
import com.saravanamarriagebureau.ui.screens.admin.membership.AdminMembershipPlanScreen
import com.saravanamarriagebureau.ui.screens.admin.membership.EditMembershipScreen
import com.saravanamarriagebureau.ui.screens.admin.users.AdminUserDetailsScreen
import com.saravanamarriagebureau.ui.screens.admin.users.AdminUsersScreen
import com.saravanamarriagebureau.ui.screens.userdashboard.LogoutDialog

@RequiresApi(Build.VERSION_CODES.O)
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
        AdminDestination.Memberships,
        AdminDestination.Enquiries,
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
            startDestination = AdminDestination.Users.route,
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
                        adminNavController.navigate(
                            adminInterestDetailsRoute(interestId = interest.id)
                        )
                    },
                )
            }

            composable(Routes.AdminAddMembershipPlan) {
                AddMembershipPlanScreen(
                    onBack = {
                        adminNavController.popBackStack()
                    }
                )

            }

            composable(AdminDestination.Memberships.route) {
                AdminMembershipPlanScreen(
                    openEdit = { planId ->
                        adminNavController.navigate(
                            adminEditMembershipDetailsRoute(planId = planId)
                        )
                    },
                    onNext = {
                        adminNavController.navigate(Routes.AdminAddMembershipPlan)
                    }
                )
            }

            composable(
                route = Routes.AdminEditMembershipPlan,
                arguments = listOf(
                    navArgument("planId") {
                        type = NavType.StringType
                    },

                    )
            ) { backStackEntry ->

                val planId =
                    backStackEntry.arguments?.getString("planId").orEmpty()

                EditMembershipScreen(
                    planId = planId,
                    onBack = {
                        adminNavController.popBackStack()
                    },
                )
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
                    onProfile = { profileId, isFromPending ->
                        adminNavController.navigate(
                            adminUserDetailsRoute(userId = profileId, isFromPending = isFromPending)
                        )
                    }
                )
            }

            composable(
                route = Routes.AdminUserDetails,
                arguments = listOf(
                    navArgument("userId") {
                        type = NavType.StringType
                    },
                    navArgument("isFromPending") {
                        type = NavType.BoolType
                    }

                )
            ) { backStackEntry ->

                val userId =
                    backStackEntry.arguments?.getString("userId").orEmpty()

                val isFromPending =
                    backStackEntry.arguments?.getBoolean("isFromPending") ?: false


                AdminUserDetailsScreen(
                    profileId = userId,
                    isFromPending = isFromPending,
                    onBack = {
                        adminNavController.popBackStack()
                    },

                    )
            }

            composable(Routes.PendingPayments) {
                AdminPaymentsScreen()
            }

//            composable(Routes.PendingProfiles) {
//                AdminUsersScreen(
//                    onProfile = { profile ->
//                        adminNavController.navigate(
//                            profileRoute(profile.id)
//                        )
//                    }
//                )
//            }

            composable(Routes.MutualInterests) {
                AdminInterestsScreen(
                    onInterestItemClick = { interest ->
                        adminNavController.navigate(
                            adminInterestDetailsRoute(interestId = interest.id)
                        )
                    },
                )
            }

            composable(
                route = Routes.AdminInterestDetails,
                arguments = listOf(
                    navArgument("interestId") {
                        type = NavType.StringType
                    }
                )
            ) { backStackEntry ->

                val interestId =
                    backStackEntry.arguments?.getString("interestId").orEmpty()
                AdminInterestDetailsScreen(
                    onBack = {
                        adminNavController.popBackStack()
                    },
                    interestId = interestId
                )
            }

            composable(AdminDestination.Enquiries.route) {
                AdminEnquiriesScreen(
                    onEnquiryItemClicked = { enquiry ->
                        adminNavController.navigate(
                            adminEnquiryDetailsRoute(enquiryId = enquiry.id)
                        )
                    },
                )
            }

            composable(
                route = Routes.AdminEnquiryDetails,
                arguments = listOf(
                    navArgument("enquiryId") {
                        type = NavType.StringType
                    }
                )
            ) { backStackEntry ->

                val enquiryId =
                    backStackEntry.arguments?.getString("enquiryId").orEmpty()
                AdminEnquiryDetailsScreen(
                    onBack = {
                        adminNavController.popBackStack()
                    },
                    enquiryId = enquiryId
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

