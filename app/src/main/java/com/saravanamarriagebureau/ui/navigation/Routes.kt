package com.saravanamarriagebureau.ui.navigation

import android.net.Uri

object Routes {
    const val Splash = "splash"
    const val Welcome = "welcome"
    const val Login = "login"
    const val Register = "register"
    const val ForgotPassword = "forgot_password/{mobile}"
    const val Offer = "offer"
    const val Membership = "membership"
    const val ProfileCreation = "profile_creation"
    const val VoiceBio = "voice_bio"
    const val ParentsTalk = "parents_talk"
    const val Home = "home"
    const val Profile = "profile/{profileId}"
    const val AdminVoice = "admin_voice"
    const val Matches = "matches"
    const val Privacy = "privacy"
    const val Search = "search"
    const val InterestSent = "interest_sent"
    const val MatchFixed = "match_fixed"
    const val Invoice = "invoice/{selectedPlan}"
    const val ContactsLocked = "contacts_locked"
    const val AdminPanel = "admin_panel"
    const val Interests = "interests"
    const val Main = "main"
    const val ChangePassword = "change_password"
    const val ChangeLanguage = "change_language"
    const val Logout = "logout"
    const val ChoosePlan = "choose_plan"
    const val profileWithStepper = "profile_with_stepper"
    const val Admin = "admin"
    const val Users = "users"
    const val AdminUserDetails = "admin_user_details/{userId}"
    const val PendingProfiles = "pending_profiles"
    const val PendingPayments = "pending_payments"
    const val MutualInterests = "mutual_interests"
    const val AdminDashboard = "admin_dashboard"
    const val AdminInterestDetails = "admin_interest_details/{interestId}"
    const val AdminAddMembershipPlan = "admin_add_membership_plan"
    const val AdminEditMembershipPlan = "admin_edit_membership_plan/{planId}"
    const val AdminInterests = "admin_interests"
    const val AdminPayments = "admin_payments"

}

fun profileRoute(profileId: String): String {
    return "profile/${Uri.encode(profileId)}"
}

fun adminInterestDetailsRoute(interestId: String): String {
    return "admin_interest_details/${Uri.encode(interestId)}"
}

fun adminUserDetailsRoute(userId: String): String {
    return "admin_user_details/${Uri.encode(userId)}"
}

fun adminEditMembershipDetailsRoute(planId: String): String {
    return "admin_edit_membership_plan/${Uri.encode(planId)}"
}

fun paymentRoute(selectedPlan: String): String {
    return "invoice/${Uri.encode(selectedPlan)}"
}


fun forgotPassword(mobile: String): String {
    return "forgot_password/${Uri.encode(mobile)}"
}
