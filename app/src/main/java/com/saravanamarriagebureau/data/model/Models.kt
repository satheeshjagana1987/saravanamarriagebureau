package com.saravanamarriagebureau.data.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ContactSupport
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.ContactSupport
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Interests
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.QueryStats
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
    val isPaidUser: Boolean,
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
    val fullName: String?,
    val mobile: String?,
    val dateOfBirth: String?,
    val email: String?,
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
    val profilePhoto: String?,
    val rejectionReason: String?,
    val familyVideo: String?,
    val profilePhotos: List<String?> = emptyList(),
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
    val party: InterestProfile,
    val status: String,
    val matchRejectRemarks: String,
    val isMatchFixed: Boolean,
    val createdAt: String,
    val updatedAt: String,
)

data class InterestProfile(
    val fullName: String?,
    val isAdminVerified: Boolean,
    val isPaidUser: Boolean,
    val profession: String?,
    val city: String?,
    val dateOfBirth: String?,
    val profilePhotos: List<String?> = emptyList(),
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
    val isPaidUser: Boolean,

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

    val profilePhotos: List<String?> = emptyList(),
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

// Admin
data class PendingApprovalResponse(
    val success: Boolean,
    val data: List<PendingApprovalUser>,
    val pagination: Pagination,
    val requestId: String,
    val timestamp: String
)

data class PendingApprovalUser(
    val id: String,
    val fullName: String,
    val mobile: String,
    val gender: String,
    val city: String?,
    val status: String,
    val createdAt: String,
    val profilePhotos: List<String?> = emptyList(),
)

data class AdminUserDetailsResponse(
    val success: Boolean,
    val data: AdminUserDetails,
    val requestId: String,
    val timestamp: String
)

data class AdminUserDetails(
    val id: String,
    val fullName: String,
    val mobile: String,
    val alternateMobile: String?,
    val email: String?,
    val gender: String,
    val dateOfBirth: String?,
    val registeringFor: String?,
    val status: String?,
    val isAdminVerified: Boolean,
    val approvedAt: String?,
    val rejectionReason: String?,
    val blockReason: String?,
    val blockedAt: String?,
    val createdAt: String,
    val profilePhotos: List<String?> = emptyList(),
    val profile: AdminUserProfile
)

data class AdminUserProfile(
    val height: String?,
    val education: String?,
    val profession: String?,
    val income: String?,
    val city: String?,
    val district: String?,
    val fullAddress: String?,
    val maritalStatus: String?,
    val religion: String?,
    val community: String?,
    val fatherName: String?,
    val motherName: String?,
    val siblings: String?,
    val placeOfBirth: String?,
    val birthTime: String?,
    val birthStar: String?,
    val rasi: String?,
    val workLocation: String?,
    val nativePlace: String?,
    val familyDetails: String?,
    val horoscope: String?,
    val registeringFor: String?
)

data class AcceptRequest(
    val action: String
)

data class RejectRequest(
    val action: String,
    val reason: String
)

data class BlockedUserRequest(
    val reason: String
)

data class BlockedUserResponseData(
    val userId: String,
    val status: String,
    val action: String,
    val idempotent: Boolean,
    val blockReason: String,
    val blockedAt: String,
)
data class AdminApproveOrRejectMatchRequest(
    val isMatchFixed: Boolean,
    val matchRejectRemarks: String
)


data class RegistrationApprovalResponse(

    val success: Boolean,


    val data: RegistrationApprovalData?,


    val requestId: String?,


    val timestamp: String?
)

data class RegistrationApprovalData(

    val id: String,


    val mobile: String,


    val status: String,


    val approvedAt: String?,


    val message: String
)

data class RegistrationRejectionResponse(

    val success: Boolean,


    val data: RegistrationRejectionData?,


    val requestId: String?,


    val timestamp: String?
)

data class RegistrationRejectionData(

    val id: String,


    val mobile: String,


    val status: String,


    val rejectionReason: String?,


    val message: String
)


//Admin Interests
data class AdminInterestsResponse(
    val success: Boolean,
    val data: List<AdminInterestsData>,
    val pagination: Pagination,
    val requestId: String,
    val timestamp: String
)

data class AdminInterestsData(
    val id: String,
    val status: String,
    val createdAt: String,
    val updatedAt: String,
    val isMatchFixed: Boolean,
    val sender: AdminInterestsDetails,
    val receiver: AdminInterestsDetails
)

data class AdminInterestsDetails(
    val userId: String,
    val profileId: String,
    val fullName: String,
    val gender: String,
    val dateOfBirth: String?,
    val profession: String?,
    val city: String?,
    val maritalStatus: String?,
    val education: String?,
    val isAdminVerified: Boolean,
    val isPaidUser: Boolean,
    val profilePhotos: List<String?> = emptyList(),
    val matchRejectRemarks: String?,
    val isMatchFixed: Boolean,
    val mobile: String?,
    val fullAddress: String?,
)

//Membership Plans
data class MembershipPlansResponse(
    val success: Boolean,
    val data: List<PlanData>,
    val pagination: Pagination,
    val requestId: String,
    val timestamp: String
)

data class PlanData(
    val id: String,
    val code: String,
    val name: String,
    val description: String,
    val durationDays: Int,
    val priceMinorUnits: Int,
    val currency: String
)

data class MembershipPlanPurchaseRequest(
    val planId: String
)

data class MembershipPlanPurchaseResponse(
    val success: Boolean,
    val data: MembershipPlanPurchaseData,
    val requestId: String,
    val timestamp: String
)

data class MembershipPlanPurchaseData(
    val invoiceId: String,
    val invoiceNumber: String,
    val invoiceType: String,
    val amountMinorUnits: Long,
    val currency: String,
    val status: String,
    val payment: PaymentProvider,
)

data class PaymentProvider(
    val provider: String,
    val orderId: String,
    val keyId: String
)

data class MembershipDetails(
    val id: String,
    val planId: String,
    val planName: String?,
    val status: String,
    val startsAt: String,
    val expiresAt: String,
    val currency: String
)

data class MembershipPlanPurchasePaymentCompleteRequest(
    val providerPaymentId: String,
    val providerSignature: String,
    val providerOrderId: String,
)

data class MembershipPlanPurchasePaymentCompleteResponse(
    val success: Boolean,
    val data: MembershipPlanPurchasePaymentCompleteData,
    val requestId: String,
    val timestamp: String
)

data class MembershipPlanPurchasePaymentCompleteData(
    val invoiceId: String,
    val invoiceNumber: String,
    val invoiceType: String,
    val invoiceStatus: String,
    val transactionStatus: String,
    val unlockId: String,
    val changed: Boolean,
    val membershipId: String
)

data class AddMembershipPlanRequest(
    val code: String,
    val name: String,
    val description: String,
    val durationDays: Int,
    val priceMinorUnits: Int,
    val currency: String,
    val isActive: Boolean
)

data class AddMembershipPlanData(
    val id: String,
    val code: String,
    val name: String,
    val description: String,
    val durationDays: Int,
    val priceMinorUnits: Int,
    val currency: String,
    val isActive: Boolean
)

data class DeleteMembershipPlanData(
    val id: String,
    val deactivated: Boolean
)

data class EnquiryItem(

    val id: String,
    val fullName: String?,
    val mobileNumber: String?,
    val emailAddress: String?,
    val subject: String?,
    val message: String?,
    val status: String, // Can also be mapped to an Enum (NEW, RESOLVED)
    val submittedAt: String,
    val updatedAt: String,

    // Nullable fields based on your JSON structure
    val adminReply: String?,
    val adminRepliedAt: String?,
    val adminRepliedBy: String?,
    val resolvedAt: String?
)

data class AdminEnquiryStatusUpdateRequest(
    val status: String,
    val comment: String
)

data class SubmitEnquiryRequest(
    val fullName: String,
    val mobileNumber: String,
    val emailAddress: String,
    val subject: String,
    val message: String,
)

data class SubmitEnquiryResponseData(
    val status: String,
    val submittedAt: String,
    val id: String,
    val confirmationMessage: String,
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
        DrawerScreen(Routes.ChangeLanguage, "Change Language", Icons.Default.Settings)

    object ChangePassword :
        DrawerScreen(Routes.ChangePassword, "Change Password", Icons.Default.Password)

    object MyEnquiries :
        DrawerScreen(Routes.MyEnquiries, "My Enquiries", Icons.Default.QueryStats)

    object ContactUs :
        DrawerScreen(Routes.ContactUs, "Contact Us", Icons.AutoMirrored.Filled.ContactSupport)

    object Logout : DrawerScreen(Routes.Logout, "Logout", Icons.AutoMirrored.Filled.ExitToApp)
}
