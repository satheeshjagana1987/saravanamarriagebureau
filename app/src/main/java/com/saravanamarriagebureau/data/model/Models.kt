package com.saravanamarriagebureau.data.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Interests
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.saravanamarriagebureau.data.enums.InterestStatus
import com.saravanamarriagebureau.data.enums.InterestTab
import com.saravanamarriagebureau.ui.navigation.Routes

data class LoginRequest(
    val identifier: String,
    val password: String
)

data class LoginResponse(
    val success: Boolean,
    val data: LoginData,
    val pagination: Pagination,
    val requestId: String,
    val timestamp: String
)

data class LoginData(
    val accessToken: String,
    val tokenType: String,
    val refreshToken: String,
    val expiresIn: String,
    val userType: String?,
    val user: User,
    val additionalProp1: Map<String, Any> = emptyMap()
)

data class User(
    val id: String,
    val fullName: String,
    val mobile: String,
    val email: String,
    val status: String,
    val gender: String,
    val isAdmin: Boolean,
)

data class RefreshTokenRequest(
    val refreshToken: String,
)

data class UserData(
    val id: String,
    val fullName: String,
    val gender: String,
    val mobile: String,
    val email: String,
    val status: String
)

data class RegisterRequest(
    val fullName: String,
    val mobile: String,
    val email: String,
    val dateOfBirth: String,
    val password: String,
    val confirmPassword: String,
    val gender: String,
    val registeringFor: String = "SELF"
)

data class RegisterResponse(
    val message: String
)

data class Profile(
    val id: String = "",
    val name: String,
    val age: String,
    val height: String,
    val education: String,
    val profession: String,
    val location: String,
    val responded: Boolean = false,
    val imageUrl: String? = null,
    val mobileNumber: String,
    val isAdminVerified: Int = 0,
    val isPaidUser: Boolean = false,
    val religion: String,
    val gender: String = "",
    val isMatchFixed: Boolean = false,
    val email: String = "",
    val maritalStatus: String = "",
    val fullAddress: String = "",

)

data class ProfileRequest(
    val height: String,
    val education: String,
    val profession: String,
    val religion: String,
    val email: String,
    val fullAddress: String,
    val city: String,
    val district: String,
    val maritalStatus: String,
    val placeOfBirth: String,
    val birthTime: String,
    val birthStar: String,
    val rasi: String,
    val workLocation: String,
    val nativePlace: String,
    val familyDetails: String,
    val income: String,
)

data class ProfileData(
    val id: String,
    val userId: String,
    val fullName: String,
    val mobile: String,
    val dateOfBirth: String,
    val email: String,
    val image: ProfileMedia,
    val videos: List<ProfileMedia>,
    val voiceNotes: List<ProfileMedia>,
    val isPaidUser: Boolean,
    val isAdminVerified: Boolean,
    val height: String?,
    val education: String?,
    val profession: String?,
    val city: String?,
    val district: String?,
    val fullAddress: String?,
    val maritalStatus: String?,
    val religion: String?,
    val gender: String?,
    val community: String?,
    val family: FamilyData,
    val horoscope: String?,
    val alternateMobile: String?,
    val registeringFor: String,
    val lookingFor: String?,
    val createdAt: String,
    val updatedAt: String,
    val placeOfBirth: String?,
    val birthTime: String?,
    val birthStar: String?,
    val rasi: String?,
    val workLocation: String?,
    val nativePlace: String?,
    val familyDetails: String?,
    val income: String?,
)

data class ProfileMedia(
    val mediaId: String,
    val mediaType: String,
    val status: String,
    val downloadEndpoint: String
)

data class FamilyData(
    val fatherName: String,
    val motherName: String,
    val siblings: String
)

data class Match(
    val id: String,
    val name: String,
    val age: String,
    val height: String,
    val education: String,
    val profession: String,
    val imageUrl: String? = null,
)

data class Invoice(
    val invoiceId: String,
    val matchId: String,
    val branch: String,
    val amount: Double,
    val date: String,
    val status: String
)

data class Interest(
    val id: String,
    val name: String,
    val age: Int,
    val height: String,
    val education: String,
    val profession: String,
    val location: String,
    val imageUrl: String? = null,
    val mobileNumber: Int,
    val isAdminVerified: Boolean = false,
    val isPaidUser: Boolean = false,
    val religion: String,
    val accept: Boolean = false,
    val reject: Boolean = true,
    val isMatched: Boolean = false,
    val type: InterestTab,
    val status: InterestStatus,
    val isPaymentDone: Boolean = false,
)

data class UserInterestsResponse(
    val success: Boolean,
    val data: List<UserInterestsData>,
    val pagination: Pagination,
    val requestId: String,
    val timestamp: String
)

data class UserInterestsData(
    val id: String,
    val senderProfileId: String,
    val receiverProfileId: String,
    val sender: InterestProfile,
    val receiver: InterestProfile,
    val status: String,
    val createdAt: String,
    val updatedAt: String
)

data class InterestProfile(
    val fullName: String?,

    val isAdminVerified: Boolean,

    val dateOfBirth: String,

    val profession: String?,

    val city: String?,
    val image: String?
)

data class Pagination(
    val total: Int,
    val limit: Int,
    val offset: Int,
    val hasMore: Boolean
)


data class ForgetPasswordRequest(
    val mobile: String,
)

data class VerifyResetOTPRequest(
    val otp: String,
    val mobile: String,
)

data class ResetPasswordRequest(
    val resetToken: String,
    val newPassword: String,
    val confirmPassword: String,
)

data class VerifyResetOTP(
    val resetToken: String,
    val expiresIn: String
)

data class ResetPasswordResponse(
    val success: Boolean,
    val data: ResetPasswordData,
    val requestId: String,
    val timestamp: String
)

data class ResetPasswordData(
    val message: String
)

data class ChangePasswordRequest(
    val oldPassword: String,
    val newPassword: String,
    val confirmPassword: String
)

data class ForgetPasswordResponse(
    val success: Boolean,
    val message: String,
)

data class DeleteMediaResponse(
    val success: Boolean,
    val data: DeleteMediaData,
    val requestId: String,
    val timestamp: String
)

data class DeleteMediaData(
    val mediaId: String,
    val status: String,
    val mediaType: String,
)

data class MediaUploadInitiatedRequest(
    val mediaType: String,
    val contentType: String,
    val fileSize: Long,
    val originalFileName: String,
)

data class MediaUploadInitiatedResponse(
    val success: Boolean,
    val message: String,
)

data class UploadResponse(
    val success: Boolean,
    val data: UploadData,
    val message: String
)

data class UploadData(
    val mediaId: String
)

data class MediaUploadCompleteResponse(
    val success: Boolean,
    val message: String,
)

data class LanguageOption(
    val name: String,     // Display name (e.g., "English")
    val code: String      // ISO language code (e.g., "en")
)

data class Branches(
    val name: String,
    val price: String
)

data class MatchesResponse(
    val success: Boolean,
    val data: List<MatchesData>,
    val pagination: MatchesPagination,
    val requestId: String,
    val timestamp: String
)

data class MatchesData(
    val id: String,

    val userId: String,

    val isAdminVerified: Boolean,
    val isInterested: Boolean,

    val height: String?,

    val education: String?,

    val profession: String?,

    val income: String?,

    val city: String?,

    val district: String?,

    val maritalStatus: String?,

    val religion: String?,

    val community: String?,

    val family: Family,

    val horoscope: String?,

    val registeringFor: String,

    val createdAt: String,

    val updatedAt: String,

    val fullName: String,

    val gender: String,

    val dateOfBirth: String,

    val status: String,

    val image: String?
)

data class Family(
    val fatherName: String?,

    val motherName: String?,

    val siblings: String?
)

data class MatchesPagination(
    val total: Int,

    val limit: Int,

    val offset: Int,

    val hasMore: Boolean
)

// Primary Bottom Bar Destinations
sealed class BottomBarScreen(val route: String, val title: String, val icon: ImageVector) {
    object Home : BottomBarScreen(Routes.Home, "Home", Icons.Default.Home)
    object Matches : BottomBarScreen(Routes.Matches, "Matches", Icons.Default.Groups)
    object Search : BottomBarScreen(Routes.Search, "Search", Icons.Default.Search)
    object Interests : BottomBarScreen(Routes.Interests, "Interests", Icons.Default.Interests)
    object ProfileCreation :
        BottomBarScreen(Routes.profileWithStepper, "Profile", Icons.Default.Person)
}

// Secondary Side Drawer Destinations
sealed class DrawerScreen(val route: String, val title: String, val icon: ImageVector) {
    object ChangeLanguage :
        DrawerScreen(Routes.ChangeLanguage, "ChangeLanguage", Icons.Default.Settings)

    object ChangePassword :
        DrawerScreen(Routes.ChangePassword, "ChangePassword", Icons.Default.Password)

    object Logout : DrawerScreen(Routes.Logout, "Logout", Icons.AutoMirrored.Filled.ExitToApp)
}

val demoInterests = listOf(
    Interest(
        id = "1",
        name = "Pooja S",
        age = 26,
        height = "5'4\"",
        education = "B.Tech",
        profession = "Software Engineer",
        location = "Guntur",
        imageUrl = null,
        mobileNumber = 1234567890,
        isAdminVerified = true,
        isPaidUser = true,
        religion = "Hindu",
        accept = false,
        reject = false,
        isMatched = false,
        type = InterestTab.RECEIVED,
        status = InterestStatus.PENDING
    ),
    Interest(
        id = "2",
        name = "Sai Charan",
        age = 28,
        height = "5'8\"",
        education = "MBA",
        profession = "Business",
        location = "Hyderabad",
        imageUrl = null,
        mobileNumber = 1234567890,
        isAdminVerified = true,
        religion = "Hindu",
        accept = true,
        reject = false,
        isMatched = false,
        type = InterestTab.RECEIVED,
        status = InterestStatus.ACCEPTED
    ),
    Interest(
        id = "3",
        name = "Lavanya",
        age = 25,
        height = "5'3\"",
        education = "B.Sc",
        profession = "Teacher",
        location = "Bangalore",
        imageUrl = null,
        mobileNumber = 1234567890,
        isAdminVerified = true,
        religion = "Muslim",
        accept = false,
        reject = true,
        isMatched = false,
        type = InterestTab.RECEIVED,
        status = InterestStatus.REJECTED
    ),

    Interest(
        id = "4",
        name = "Pooja S",
        age = 26,
        height = "5'4\"",
        education = "B.Tech",
        profession = "Software Engineer",
        location = "Guntur",
        imageUrl = null,
        mobileNumber = 1234567890,
        isAdminVerified = true,
        isPaidUser = true,
        religion = "Hindu",
        accept = false,
        reject = false,
        isMatched = false,
        type = InterestTab.SENT,
        status = InterestStatus.PENDING
    ),
    Interest(
        id = "5",
        name = "Sai Charan",
        age = 28,
        height = "5'8\"",
        education = "MBA",
        profession = "Business",
        location = "Hyderabad",
        imageUrl = null,
        mobileNumber = 1234567890,
        isAdminVerified = true,
        religion = "Hindu",
        accept = true,
        reject = false,
        isMatched = false,
        type = InterestTab.SENT,
        status = InterestStatus.ACCEPTED
    ),
    Interest(
        id = "6",
        name = "Lavanya",
        age = 25,
        height = "5'3\"",
        education = "B.Sc",
        profession = "Teacher",
        location = "Bangalore",
        imageUrl = null,
        mobileNumber = 1234567890,
        isAdminVerified = true,
        religion = "Muslim",
        accept = false,
        reject = true,
        isMatched = false,
        type = InterestTab.SENT,
        status = InterestStatus.REJECTED
    ),

    Interest(
        id = "7",
        name = "Pooja S",
        age = 26,
        height = "5'4\"",
        education = "B.Tech",
        profession = "Software Engineer",
        location = "Guntur",
        imageUrl = null,
        mobileNumber = 1234567890,
        isAdminVerified = true,
        isPaidUser = true,
        religion = "Hindu",
        accept = false,
        reject = false,
        isMatched = true,
        type = InterestTab.MATCH_FIXED,
        status = InterestStatus.PENDING
    ),
    Interest(
        id = "8",
        name = "Sai Charan",
        age = 28,
        height = "5'8\"",
        education = "MBA",
        profession = "Business",
        location = "Hyderabad",
        imageUrl = null,
        mobileNumber = 1234567890,
        isAdminVerified = true,
        religion = "Hindu",
        accept = true,
        reject = false,
        isMatched = true,
        type = InterestTab.MATCH_FIXED,
        status = InterestStatus.DONE,
        isPaymentDone = true
    ),
    Interest(
        id = "9",
        name = "Lavanya",
        age = 25,
        height = "5'3\"",
        education = "B.Sc",
        profession = "Teacher",
        location = "Bangalore",
        imageUrl = null,
        mobileNumber = 1234567890,
        isAdminVerified = true,
        religion = "Muslim",
        accept = false,
        reject = true,
        isMatched = true,
        type = InterestTab.MATCH_FIXED,
        status = InterestStatus.DONE,
        isPaymentDone = true
    )

)
