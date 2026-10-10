package com.saravanamarriagebureau.ui.navigation

object Routes {
    const val Splash = "splash"
    const val Welcome = "welcome"
    const val Login = "login"
    const val Register = "register"
    const val ForgotPassword = "forgot_password"
    const val Offer = "offer"
    const val Membership = "membership"
    const val ProfileCreation = "profile_creation"
    const val VoiceBio = "voice_bio"
    const val ParentsTalk = "parents_talk"
    const val Home = "home"
    const val Profile = "profile"
    const val AdminVoice = "admin_voice"
    const val Matches = "matches"
    const val Privacy = "privacy"
    const val Search = "search"
    const val InterestSent = "interest_sent"
    const val MatchFixed = "match_fixed"
    const val Invoice = "invoice"
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
    const val PendingProfiles = "pending_profiles"
    const val PendingPayments = "pending_payments"
    const val MutualInterests = "mutual_interests"
    const val AdminDashboard = "admin_dashboard"
    const val AdminSettings = "admin_settings"
    const val AdminInterests = "admin_interests"
    const val AdminPayments = "admin_payments"

}

fun profileRoute(profileId: String, type: String): String {
    return "${Routes.Profile}/$profileId/$type"
}

fun interestRoute(interestId: String): String {
    return "${Routes.Interests}/$interestId"
}
