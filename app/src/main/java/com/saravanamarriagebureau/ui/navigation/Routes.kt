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
}

fun profileRoute(profileId: String): String {
    return "${Routes.Profile}/$profileId"
}

fun interestRoute(interestId: String): String {
    return "${Routes.Interests}/$interestId"
}
