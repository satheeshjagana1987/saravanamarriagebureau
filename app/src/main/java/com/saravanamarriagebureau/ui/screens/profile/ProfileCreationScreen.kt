package com.saravanamarriagebureau.ui.screens.profile

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.Height
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Mail
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.FileProvider
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.canhub.cropper.CropImageContract
import com.canhub.cropper.CropImageContractOptions
import com.canhub.cropper.CropImageOptions
import com.canhub.cropper.CropImageView
import com.saravanamarriagebureau.R
import com.saravanamarriagebureau.data.enums.ProfileTab
import com.saravanamarriagebureau.ui.components.CustomButton
import com.saravanamarriagebureau.ui.components.LabelName
import com.saravanamarriagebureau.ui.components.LoadingDialog
import com.saravanamarriagebureau.ui.components.NetworkImage
import com.saravanamarriagebureau.ui.components.PrimaryButton
import com.saravanamarriagebureau.ui.components.ProfilePhoto
import com.saravanamarriagebureau.ui.components.TimePickerTextField
import com.saravanamarriagebureau.ui.screens.media.MediaUploadCompleteViewModel
import com.saravanamarriagebureau.ui.screens.media.MediaUploadInitiatedViewModel
import com.saravanamarriagebureau.ui.screens.media.getFileInfo
import com.saravanamarriagebureau.ui.theme.Black
import com.saravanamarriagebureau.ui.theme.Cream
import com.saravanamarriagebureau.ui.theme.Maroon
import com.saravanamarriagebureau.ui.theme.Red
import com.saravanamarriagebureau.ui.theme.White
import com.saravanamarriagebureau.util.Constants.NA
import com.saravanamarriagebureau.util.ToastUtils
import com.saravanamarriagebureau.util.ValidationUtils
import com.saravanamarriagebureau.util.VoiceRecorder
import timber.log.Timber
import java.io.File

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileCreationScreen(
    onSubmit: () -> Unit,
    onBack: () -> Unit,
    viewModel: ProfileViewModel = hiltViewModel(),
    imageViewModel: ImageViewModel = hiltViewModel(),
    mediaInitiatedUploadViewModel: MediaUploadInitiatedViewModel = hiltViewModel(),
    mediaUploadCompleteViewModel: MediaUploadCompleteViewModel = hiltViewModel()
) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var dob by remember { mutableStateOf("") }
    var city by remember { mutableStateOf("") }
    var district by remember { mutableStateOf("") }
    var fullAddress by remember { mutableStateOf("") }
    var height by remember { mutableStateOf("") }
    var education by remember { mutableStateOf("") }
    var profession by remember { mutableStateOf("") }
    var religion by remember { mutableStateOf("") }
    var mobileNumber by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }
    var placeOfBirth by remember { mutableStateOf("") }
    var birthTime by remember { mutableStateOf("") }
    var nativePlace by remember { mutableStateOf("") }
    var star by remember { mutableStateOf("") }
    var rasi by remember { mutableStateOf("") }
    var income by remember { mutableStateOf("") }
    var placeOfProfession by remember { mutableStateOf("") }
    var familyDetails by remember { mutableStateOf("") }
    var profile by remember { mutableStateOf("") }
    var rejectionReason by remember { mutableStateOf("") }

    // Location dropdown
    val options = listOf("Amaravathi, Guntur, Vizag")
    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf(options[0]) }

    val maritalStatusOptions = listOf(
        "Never Married",
        "Others"
    )

    var isLoading by remember {
        mutableStateOf(false)
    }
    var emailError by remember { mutableStateOf<String?>(null) }
    var cityError by remember { mutableStateOf<String?>(null) }
    var districtError by remember { mutableStateOf<String?>(null) }
    var heightError by remember { mutableStateOf<String?>(null) }
    var educationError by remember { mutableStateOf<String?>(null) }
    var professionError by remember { mutableStateOf<String?>(null) }
    var religionError by remember { mutableStateOf<String?>(null) }
    var placeOfBirthError by remember { mutableStateOf<String?>(null) }
    var birthTimeError by remember { mutableStateOf<String?>(null) }
    var nativePlaceError by remember { mutableStateOf<String?>(null) }
    var starError by remember { mutableStateOf<String?>(null) }
    var rasiError by remember { mutableStateOf<String?>(null) }
    var incomeError by remember { mutableStateOf<String?>(null) }
    var placeOfProfessionError by remember { mutableStateOf<String?>(null) }
    var familyDetailsError by remember { mutableStateOf<String?>(null) }

    var profilePhotos by remember {
        mutableStateOf<List<String?>>(emptyList())
    }

    var selectedTab by remember {
        mutableStateOf(ProfileTab.PERSONAL)
    }

    var isSubmitVisible by rememberSaveable {
        mutableStateOf(true)
    }

    var selectedMaritalStatus by remember {
        mutableStateOf("")
    }

    var selectedIndex by remember {
        mutableStateOf(0)
    }

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val uiStateProfileUpdate by viewModel.uiStateProfileUpdate.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.loadProfile()
    }

    var imageFileSize: Long = 0
    var imageFileName = ""

    // Video
    var videoUri by remember { mutableStateOf<Uri?>(null) }

    // Image
    val state by imageViewModel.uiState.collectAsState()
    val uiStateDeleteImage by imageViewModel.uiStateDeleteImage.collectAsState()
    val videoState by imageViewModel.uploadVideoState.collectAsState()
    val mediaInitiatedUploadUiState by mediaInitiatedUploadViewModel.uiState.collectAsState()
    val mediaUploadCompleteUiState by mediaUploadCompleteViewModel.uiState.collectAsState()
    val context = LocalContext.current
    val imageStates by imageViewModel.imageStates.collectAsStateWithLifecycle()

    var pendingCameraUri by remember {
        mutableStateOf<Uri?>(null)
    }

    // Voice Record

    val scope = rememberCoroutineScope()

    var recorder by remember {
        mutableStateOf<VoiceRecorder?>(null)
    }
    var recording by remember { mutableStateOf(false) }
    var status by remember { mutableStateOf("Ready") }

    val permissionLauncher =
        rememberLauncherForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { granted ->
            if (granted) {
                try {
                    val voiceRecorder = VoiceRecorder(context)
                    voiceRecorder.start()
                    recorder = voiceRecorder
                    recording = true
                    status = "Recording..."
                } catch (e: Exception) {
                    status = "Could not start recording"
                }
            } else {
                status = "Microphone permission denied"
            }
        }


    LaunchedEffect(uiState) {

        when (val state = uiState) {

            is ProfileUIState.Loading -> {
                isLoading = true
            }

            is ProfileUIState.Success -> {
                isLoading = false

                val profile = state.data

                name = profile.fullName
                email = profile.email
                mobileNumber = profile.mobile
                dob = profile.dateOfBirth
                city = profile.city.orEmpty()
                district = profile.district.orEmpty()

                height = profile.height.orEmpty()
                education = profile.education.orEmpty()
                profession = profile.profession.orEmpty()
                religion = profile.religion.orEmpty()
                gender = profile.gender.orEmpty()
                selectedMaritalStatus = profile.maritalStatus.orEmpty()
                placeOfBirth = profile.placeOfBirth.orEmpty()
                birthTime = profile.birthTime.orEmpty()
                star = profile.birthStar.orEmpty()
                rasi = profile.rasi.orEmpty()
                nativePlace = profile.nativePlace.orEmpty()
                placeOfProfession = profile.workLocation.orEmpty()
                income = profile.income.orEmpty()
                familyDetails = profile.familyDetails.orEmpty()
                rejectionReason = profile.rejectionReason ?: ""
                profilePhotos = profile.profilePhotos

                ToastUtils.success(context = context, message = "Profile data fetched successfully")
            }

            is ProfileUIState.Error -> {
                isLoading = false
                ToastUtils.error(context, state.message)
            }

            else -> {
                isLoading = false
            }
        }
    }

    LaunchedEffect(uiStateDeleteImage) {

        when (val state = uiStateDeleteImage) {

            is DeleteMediaUIState.Loading -> {
                isLoading = true
            }

            is DeleteMediaUIState.Success -> {
                isLoading = false
                ToastUtils.success(context = context, message = "Image deleted successfully")
            }

            is DeleteMediaUIState.Error -> {
                isLoading = false
                ToastUtils.error(context, state.message)
                profilePhotos = profilePhotos.toMutableList().also {
                    it[selectedIndex] = null
                }
            }

            else -> {
                isLoading = false
            }
        }
    }

    // 1. Crop launcher must be declared first
    val cropLauncher = rememberLauncherForActivityResult(
        contract = CropImageContract()
    ) { result ->
        if (result.isSuccessful) {
            result.uriContent?.let { croppedUri ->
//                imageViewModel.setImage(croppedUri)
            }
        } else {
            result.error?.let { error ->
                // Handle crop error
                Log.e("ImageCrop", "Crop failed", error)
            }
        }
    }

// 2. Create a reusable crop callback
    val launchCrop: (Uri) -> Unit = { uri ->
        cropLauncher.launch(
            CropImageContractOptions(
                uri = uri,
                cropImageOptions = CropImageOptions(
                    guidelines = CropImageView.Guidelines.ON
                )
            )
        )
    }

    // Open gallery
    val galleryLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.PickVisualMedia()
    ) { uri ->
        uri?.let {
            val info = it.getFileInfo(context)
            imageFileName = info.name ?: NA
            imageFileSize = info.size ?: 0
            Timber.e("sizegalleryimageFileSize318====>$imageFileSize")
            Timber.e("sizegalleryimageFileName319====>$imageFileName")
            val imageSizeInMB = imageFileSize / (1024.0 * 1024.0)

            Timber.e("sizegalleryimageSizeInMB320====>$imageSizeInMB")
            val maxSize = 5L * 1024 * 1024
            if (imageSizeInMB > maxSize) {
                ToastUtils.error(context = context, message = "Image size should not exceed 5 MB")
                return@let
            }

            imageViewModel.setImage(index = selectedIndex, uri = it)
        }
    }

    // Capture a full-size photo
    val cameraLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.TakePicture()
    ) { success ->
        if (success) {
            pendingCameraUri?.let {
                val info = it.getFileInfo(context)
                imageFileName = info.name ?: NA
                imageFileSize = info.size ?: 0L

                Timber.e("sizecameraimageFileSize341====>$imageFileSize")
                Timber.e("sizecameraimageFileName342====>$imageFileName")
                val imageSizeInMB = imageFileSize / (1024.0 * 1024.0)

                Timber.e("sizecameraimageSizeInMB343====>$imageSizeInMB")
                val maxSize = 5L * 1024 * 1024
                if (imageSizeInMB > maxSize) {
                    ToastUtils.error(context = context, message = "Image size should not exceed 5 MB")
                    return@let
                }
                imageViewModel.setImage(index = selectedIndex, uri = it)
            }
        }
    }

    // Video result
    val picker = rememberLauncherForActivityResult(
        ActivityResultContracts.PickVisualMedia()
    ) { uri ->
        videoUri = uri
        status = if (uri != null) "Video selected" else "No video selected"
    }

    // Modal loading dialog shown while the profile is being fetched.
    // It overlays the whole screen and blocks interaction until load completes.
    LoadingDialog(isLoading = isLoading)

    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(Cream),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(Modifier.padding(horizontal = 16.dp)) {
            Row(
                Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                ProfileTabButton(
                    text = stringResource(id = R.string.personal),
                    selected = selectedTab == ProfileTab.PERSONAL,
                    onClick = {
                        selectedTab = ProfileTab.PERSONAL
                        isSubmitVisible = true
                    },
                    modifier = Modifier.weight(1f)
                )

                ProfileTabButton(
                    text = stringResource(id = R.string.professional),
                    selected = selectedTab == ProfileTab.PROFESSIONAL,
                    onClick = {
                        selectedTab = ProfileTab.PROFESSIONAL
                        isSubmitVisible = true

                    },
                    modifier = Modifier.weight(1f)
                )

                ProfileTabButton(
                    text = stringResource(id = R.string.photo),
                    selected = selectedTab == ProfileTab.PHOTO,
                    onClick = {
                        selectedTab = ProfileTab.PHOTO
                        isSubmitVisible = false
                    },
                    modifier = Modifier.weight(1f)
                )

            }
            if (rejectionReason.isNotEmpty()) {
                LabelName(
                    labelName = rejectionReason,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Red,
                    modifier = Modifier.padding(top = 10.dp, bottom = 10.dp)
                )
            }

            Spacer(Modifier.height(20.dp))

            Card(
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                if (selectedTab == ProfileTab.PERSONAL) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(White)
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(0.dp)
                    ) {

                        LabelName(
                            labelName = stringResource(id = R.string.full_name),
                            fontSize = 14.sp,
                            color = Maroon,
                            fontWeight = FontWeight.Normal
                        )
                        Spacer(Modifier.height(5.dp))
                        OutlinedTextField(
                            value = name,
                            onValueChange = {
                                name = it
                            },
                            readOnly = true,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(52.dp),
                            singleLine = true,
                            shape = RoundedCornerShape(12.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Color(0xFFE2E2E8),
                                unfocusedBorderColor = Color(0xFFE2E2E8),
                                cursorColor = Color(0xFF555555),
                                focusedTextColor = Color(0xFF333333),
                                unfocusedTextColor = Color(0xFF333333)
                            ),
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Outlined.AccountBox,
                                    contentDescription = null,
                                    tint = Color(0xFF9A9AA5),
                                    modifier = Modifier.size(19.dp)
                                )
                            },

                            placeholder = {
                                Text(
                                    text = "Enter Full Name",
                                    color = Color(0xFFB5B5BD),
                                    fontSize = 14.sp
                                )
                            }
                        )

                        Spacer(Modifier.height(10.dp))

                        LabelName(
                            labelName = stringResource(id = R.string.mobile_number),
                            fontSize = 14.sp,
                            color = Maroon,
                            fontWeight = FontWeight.Normal
                        )
                        Spacer(Modifier.height(5.dp))
                        OutlinedTextField(
                            value = mobileNumber,
                            onValueChange = {
                                if (it.length <= 10 && it.all(Char::isDigit)) {
                                    mobileNumber = it
                                }
                            },
                            readOnly = true,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(52.dp),
                            singleLine = true,
                            shape = RoundedCornerShape(12.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Color(0xFFE2E2E8),
                                unfocusedBorderColor = Color(0xFFE2E2E8),
                                cursorColor = Color(0xFF555555),
                                focusedTextColor = Color(0xFF333333),
                                unfocusedTextColor = Color(0xFF333333)
                            ),
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Outlined.Phone,
                                    contentDescription = null,
                                    tint = Color(0xFF9A9AA5),
                                    modifier = Modifier.size(19.dp)
                                )
                            },
                            placeholder = {
                                Text(
                                    text = "Enter Mobile Number",
                                    color = Color(0xFFB5B5BD),
                                    fontSize = 14.sp
                                )
                            }
                        )

                        Spacer(Modifier.height(10.dp))
                        LabelName(
                            labelName = stringResource(id = R.string.email),
                            fontSize = 14.sp,
                            color = Maroon,
                            fontWeight = FontWeight.Normal
                        )
                        Spacer(Modifier.height(5.dp))
                        OutlinedTextField(
                            value = email,
                            onValueChange = {
                                email = it
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(52.dp),
                            singleLine = true,
                            shape = RoundedCornerShape(12.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Color(0xFFE2E2E8),
                                unfocusedBorderColor = Color(0xFFE2E2E8),
                                cursorColor = Color(0xFF555555),
                                focusedTextColor = Color(0xFF333333),
                                unfocusedTextColor = Color(0xFF333333)
                            ),
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Outlined.Mail,
                                    contentDescription = null,
                                    tint = Color(0xFF9A9AA5),
                                    modifier = Modifier.size(19.dp)
                                )
                            },
                            placeholder = {
                                Text(
                                    text = "Enter Email",
                                    color = Color(0xFFB5B5BD),
                                    fontSize = 14.sp
                                )
                            }
                        )

                        Spacer(Modifier.height(10.dp))
                        // DOB

                        LabelName(
                            labelName = stringResource(id = R.string.date_of_birth),
                            fontSize = 14.sp,
                            color = Maroon,
                            fontWeight = FontWeight.Normal
                        )
                        Spacer(Modifier.height(5.dp))
                        OutlinedTextField(
                            value = dob,
                            onValueChange = {
                                dob = it
                            },
                            readOnly = true,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(52.dp),
                            singleLine = true,
                            shape = RoundedCornerShape(12.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Color(0xFFE2E2E8),
                                unfocusedBorderColor = Color(0xFFE2E2E8),
                                cursorColor = Color(0xFF555555),
                                focusedTextColor = Color(0xFF333333),
                                unfocusedTextColor = Color(0xFF333333)
                            ),
                            trailingIcon = {
                                Icon(
                                    imageVector = Icons.Outlined.CalendarMonth,
                                    contentDescription = null,
                                    tint = Color(0xFF9A9AA5),
                                    modifier = Modifier.size(19.dp)
                                )
                            },
                            placeholder = {
                                Text(
                                    text = "YYYY-MM-DD",
                                    color = Color(0xFFB5B5BD),
                                    fontSize = 14.sp
                                )
                            }
                        )
                        Spacer(Modifier.height(10.dp))

                        LabelName(
                            labelName = stringResource(id = R.string.city),
                            fontSize = 14.sp,
                            color = Maroon,
                            fontWeight = FontWeight.Normal
                        )
                        Spacer(Modifier.height(5.dp))
                        OutlinedTextField(
                            value = city,
                            onValueChange = {
                                city = it
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(52.dp),
                            singleLine = true,
                            shape = RoundedCornerShape(12.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Color(0xFFE2E2E8),
                                unfocusedBorderColor = Color(0xFFE2E2E8),
                                cursorColor = Color(0xFF555555),
                                focusedTextColor = Color(0xFF333333),
                                unfocusedTextColor = Color(0xFF333333)
                            ),
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Outlined.LocationOn,
                                    contentDescription = null,
                                    tint = Color(0xFF9A9AA5),
                                    modifier = Modifier.size(19.dp)
                                )
                            },
                            placeholder = {
                                Text(
                                    text = "Enter City",
                                    color = Color(0xFFB5B5BD),
                                    fontSize = 14.sp
                                )
                            }
                        )

                        Spacer(Modifier.height(10.dp))

                        LabelName(
                            labelName = stringResource(id = R.string.district),
                            fontSize = 14.sp,
                            color = Maroon,
                            fontWeight = FontWeight.Normal
                        )
                        Spacer(Modifier.height(5.dp))
                        OutlinedTextField(
                            value = district,
                            onValueChange = {
                                district = it
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(52.dp),
                            singleLine = true,
                            shape = RoundedCornerShape(12.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Color(0xFFE2E2E8),
                                unfocusedBorderColor = Color(0xFFE2E2E8),
                                cursorColor = Color(0xFF555555),
                                focusedTextColor = Color(0xFF333333),
                                unfocusedTextColor = Color(0xFF333333)
                            ),
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Outlined.LocationOn,
                                    contentDescription = null,
                                    tint = Color(0xFF9A9AA5),
                                    modifier = Modifier.size(19.dp)
                                )
                            },
                            placeholder = {
                                Text(
                                    text = "Enter District",
                                    color = Color(0xFFB5B5BD),
                                    fontSize = 14.sp
                                )
                            }
                        )

                        Spacer(Modifier.height(15.dp))
                        LabelName(
                            labelName = stringResource(id = R.string.place_of_birth),
                            fontSize = 14.sp,
                            color = Maroon,
                            fontWeight = FontWeight.Normal
                        )
                        Spacer(Modifier.height(5.dp))
                        OutlinedTextField(
                            value = placeOfBirth,
                            onValueChange = {
                                placeOfBirth = it
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(52.dp),
                            singleLine = true,
                            shape = RoundedCornerShape(12.dp),

                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Color(0xFFE2E2E8),
                                unfocusedBorderColor = Color(0xFFE2E2E8),
                                cursorColor = Color(0xFF555555),
                                focusedTextColor = Color(0xFF333333),
                                unfocusedTextColor = Color(0xFF333333)
                            ),

                            placeholder = {
                                Text(
                                    text = stringResource(id = R.string.place_of_birth),
                                    color = Color(0xFFB5B5BD),
                                    fontSize = 14.sp
                                )
                            }
                        )

                        Spacer(Modifier.height(15.dp))
                        LabelName(
                            labelName = stringResource(id = R.string.birth_time),
                            fontSize = 14.sp,
                            color = Maroon,
                            fontWeight = FontWeight.Normal
                        )
                        Spacer(Modifier.height(5.dp))
                        TimePickerTextField(
                            selectedTime = birthTime,
                            onTimeSelected = { birthTime = it }
                        )
                        Spacer(Modifier.height(15.dp))
                        LabelName(
                            labelName = stringResource(id = R.string.native_place),
                            fontSize = 14.sp,
                            color = Maroon,
                            fontWeight = FontWeight.Normal
                        )
                        Spacer(Modifier.height(5.dp))
                        OutlinedTextField(
                            value = nativePlace,
                            onValueChange = {
                                nativePlace = it
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(52.dp),
                            singleLine = true,
                            shape = RoundedCornerShape(12.dp),

                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Color(0xFFE2E2E8),
                                unfocusedBorderColor = Color(0xFFE2E2E8),
                                cursorColor = Color(0xFF555555),
                                focusedTextColor = Color(0xFF333333),
                                unfocusedTextColor = Color(0xFF333333)
                            ),

                            placeholder = {
                                Text(
                                    text = stringResource(id = R.string.native_place),
                                    color = Color(0xFFB5B5BD),
                                    fontSize = 14.sp
                                )
                            }
                        )

                    }
                }
                if (selectedTab == ProfileTab.PROFESSIONAL) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(White)
                            .padding(16.dp),
                    ) {
                        LabelName(
                            labelName = stringResource(id = R.string.education),
                            fontSize = 14.sp,
                            color = Maroon,
                            fontWeight = FontWeight.Normal
                        )
                        Spacer(Modifier.height(5.dp))
                        OutlinedTextField(
                            value = education,
                            onValueChange = {
                                education = it
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(52.dp),
                            singleLine = true,
                            shape = RoundedCornerShape(12.dp),

                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Color(0xFFE2E2E8),
                                unfocusedBorderColor = Color(0xFFE2E2E8),
                                cursorColor = Color(0xFF555555),
                                focusedTextColor = Color(0xFF333333),
                                unfocusedTextColor = Color(0xFF333333)
                            ),

                            placeholder = {
                                Text(
                                    text = "Enter Education",
                                    color = Color(0xFFB5B5BD),
                                    fontSize = 14.sp
                                )
                            }
                        )
                        Spacer(Modifier.height(15.dp))
                        LabelName(
                            labelName = stringResource(id = R.string.height),
                            fontSize = 14.sp,
                            color = Maroon,
                            fontWeight = FontWeight.Normal
                        )
                        Spacer(Modifier.height(5.dp))
                        OutlinedTextField(
                            value = height,
                            onValueChange = {
                                height = it
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(52.dp),
                            singleLine = true,
                            shape = RoundedCornerShape(12.dp),

                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Color(0xFFE2E2E8),
                                unfocusedBorderColor = Color(0xFFE2E2E8),
                                cursorColor = Color(0xFF555555),
                                focusedTextColor = Color(0xFF333333),
                                unfocusedTextColor = Color(0xFF333333)
                            ),
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Outlined.Height,
                                    contentDescription = null,
                                    tint = Color(0xFF9A9AA5),
                                    modifier = Modifier.size(19.dp)
                                )
                            },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Decimal
                            ),
                            placeholder = {
                                Text(
                                    text = "Enter Height",
                                    color = Color(0xFFB5B5BD),
                                    fontSize = 14.sp
                                )
                            }
                        )
                        Spacer(Modifier.height(15.dp))
                        LabelName(
                            labelName = stringResource(id = R.string.profession),
                            fontSize = 14.sp,
                            color = Maroon,
                            fontWeight = FontWeight.Normal
                        )
                        Spacer(Modifier.height(5.dp))
                        OutlinedTextField(
                            value = profession,
                            onValueChange = {
                                profession = it
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(52.dp),
                            singleLine = true,
                            shape = RoundedCornerShape(12.dp),

                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Color(0xFFE2E2E8),
                                unfocusedBorderColor = Color(0xFFE2E2E8),
                                cursorColor = Color(0xFF555555),
                                focusedTextColor = Color(0xFF333333),
                                unfocusedTextColor = Color(0xFF333333)
                            ),

                            placeholder = {
                                Text(
                                    text = "Enter Profession",
                                    color = Color(0xFFB5B5BD),
                                    fontSize = 14.sp
                                )
                            }
                        )
                        Spacer(Modifier.height(15.dp))
                        LabelName(
                            labelName = stringResource(id = R.string.place_of_profession),
                            fontSize = 14.sp,
                            color = Maroon,
                            fontWeight = FontWeight.Normal
                        )
                        Spacer(Modifier.height(5.dp))
                        OutlinedTextField(
                            value = placeOfProfession,
                            onValueChange = {
                                placeOfProfession = it
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(52.dp),
                            singleLine = true,
                            shape = RoundedCornerShape(12.dp),

                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Color(0xFFE2E2E8),
                                unfocusedBorderColor = Color(0xFFE2E2E8),
                                cursorColor = Color(0xFF555555),
                                focusedTextColor = Color(0xFF333333),
                                unfocusedTextColor = Color(0xFF333333)
                            ),

                            placeholder = {
                                Text(
                                    text = "Place of work",
                                    color = Color(0xFFB5B5BD),
                                    fontSize = 14.sp
                                )
                            }
                        )

                        Spacer(Modifier.height(15.dp))
                        LabelName(
                            labelName = stringResource(id = R.string.salary),
                            fontSize = 14.sp,
                            color = Maroon,
                            fontWeight = FontWeight.Normal
                        )
                        Spacer(Modifier.height(5.dp))
                        OutlinedTextField(
                            value = income,
                            onValueChange = {
                                income = it
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(52.dp),
                            singleLine = true,
                            shape = RoundedCornerShape(12.dp),

                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Color(0xFFE2E2E8),
                                unfocusedBorderColor = Color(0xFFE2E2E8),
                                cursorColor = Color(0xFF555555),
                                focusedTextColor = Color(0xFF333333),
                                unfocusedTextColor = Color(0xFF333333)
                            ),

                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number
                            ),

                            placeholder = {
                                Text(
                                    text = stringResource(id = R.string.salary),
                                    color = Color(0xFFB5B5BD),
                                    fontSize = 14.sp
                                )
                            }
                        )

                        Spacer(Modifier.height(15.dp))
                        LabelName(
                            labelName = stringResource(id = R.string.family_details),
                            fontSize = 14.sp,
                            color = Maroon,
                            fontWeight = FontWeight.Normal
                        )
                        Spacer(Modifier.height(5.dp))
                        OutlinedTextField(
                            value = familyDetails,
                            onValueChange = {
                                familyDetails = it
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(52.dp),
                            singleLine = true,
                            shape = RoundedCornerShape(12.dp),

                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Color(0xFFE2E2E8),
                                unfocusedBorderColor = Color(0xFFE2E2E8),
                                cursorColor = Color(0xFF555555),
                                focusedTextColor = Color(0xFF333333),
                                unfocusedTextColor = Color(0xFF333333)
                            ),

                            placeholder = {
                                Text(
                                    text = stringResource(id = R.string.family_details),
                                    color = Color(0xFFB5B5BD),
                                    fontSize = 14.sp
                                )
                            }
                        )

                        Spacer(Modifier.height(15.dp))
                        LabelName(
                            labelName = stringResource(id = R.string.star),
                            fontSize = 14.sp,
                            color = Maroon,
                            fontWeight = FontWeight.Normal
                        )
                        Spacer(Modifier.height(5.dp))
                        OutlinedTextField(
                            value = star,
                            onValueChange = {
                                star = it
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(52.dp),
                            singleLine = true,
                            shape = RoundedCornerShape(12.dp),

                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Color(0xFFE2E2E8),
                                unfocusedBorderColor = Color(0xFFE2E2E8),
                                cursorColor = Color(0xFF555555),
                                focusedTextColor = Color(0xFF333333),
                                unfocusedTextColor = Color(0xFF333333)
                            ),

                            placeholder = {
                                Text(
                                    text = stringResource(id = R.string.star),
                                    color = Color(0xFFB5B5BD),
                                    fontSize = 14.sp
                                )
                            }
                        )

                        Spacer(Modifier.height(15.dp))
                        LabelName(
                            labelName = stringResource(id = R.string.rasi),
                            fontSize = 14.sp,
                            color = Maroon,
                            fontWeight = FontWeight.Normal
                        )
                        Spacer(Modifier.height(5.dp))
                        OutlinedTextField(
                            value = rasi,
                            onValueChange = {
                                rasi = it
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(52.dp),
                            singleLine = true,
                            shape = RoundedCornerShape(12.dp),

                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Color(0xFFE2E2E8),
                                unfocusedBorderColor = Color(0xFFE2E2E8),
                                cursorColor = Color(0xFF555555),
                                focusedTextColor = Color(0xFF333333),
                                unfocusedTextColor = Color(0xFF333333)
                            ),

                            placeholder = {
                                Text(
                                    text = stringResource(id = R.string.rasi),
                                    color = Color(0xFFB5B5BD),
                                    fontSize = 14.sp
                                )
                            }
                        )

                        Spacer(Modifier.height(10.dp))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            LabelName(
                                labelName = stringResource(id = R.string.gender),
                                fontSize = 14.sp,
                                color = Maroon,
                                fontWeight = FontWeight.Normal
                            )

                            Spacer(Modifier.width(10.dp))
                            LabelName(
                                labelName = gender,
                                fontSize = 14.sp,
                                color = Black,
                                fontWeight = FontWeight.Normal
                            )
                        }

                        Spacer(Modifier.height(5.dp))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            LabelName(
                                labelName = stringResource(id = R.string.marital_status),
                                fontSize = 14.sp,
                                color = Maroon,
                                fontWeight = FontWeight.Normal
                            )
                            Spacer(Modifier.height(5.dp))
                            maritalStatusOptions.forEach { status ->
                                Row(
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    RadioButton(
                                        selected = selectedMaritalStatus == status,
                                        onClick = {
                                            selectedMaritalStatus = status
                                        }
                                    )

                                    Text(
                                        text = status,
                                        fontSize = 14.sp,
                                        color = Black
                                    )
                                }
                            }
                        }

                        Spacer(Modifier.height(10.dp))
                        LabelName(
                            labelName = stringResource(id = R.string.religion),
                            fontSize = 14.sp,
                            color = Maroon,
                            fontWeight = FontWeight.Normal
                        )
                        Spacer(Modifier.height(5.dp))
                        OutlinedTextField(
                            value = religion,
                            onValueChange = {
                                religion = it
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(52.dp),
                            singleLine = true,
                            shape = RoundedCornerShape(12.dp),

                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Color(0xFFE2E2E8),
                                unfocusedBorderColor = Color(0xFFE2E2E8),
                                cursorColor = Color(0xFF555555),
                                focusedTextColor = Color(0xFF333333),
                                unfocusedTextColor = Color(0xFF333333)
                            ),

                            placeholder = {
                                Text(
                                    text = "Enter Religion",
                                    color = Color(0xFFB5B5BD),
                                    fontSize = 14.sp
                                )
                            }
                        )

                    }
                }
                if (selectedTab == ProfileTab.PHOTO) {
                    repeat(3) { index ->

                        val imageState = imageStates[index] ?: ImageUiState()

                        val selectedUri = imageState.imageUri

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.White)
                                .padding(10.dp)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.spacedBy(8.dp)
                                ) {

                                    if (selectedUri != null) {
                                        ProfilePhoto(
                                            selectedUri,
                                            Modifier.size(110.dp)
                                        )
                                    } else {
                                        NetworkImage(
                                            url = profilePhotos.getOrNull(index).orEmpty(),
                                            modifier = Modifier.size(110.dp)
                                        )
                                    }

                                    // Show delete icon only if an uploaded image exists
                                    val uploadedImageUrl = profilePhotos.getOrNull(index)

                                    if (!uploadedImageUrl.isNullOrBlank()) {

                                        IconButton(
                                            onClick = {
                                                val mediaId = getMediaIdFromUrl(uploadedImageUrl)

                                                Timber.e("mediaId ===> $mediaId")

                                                if (mediaId != null) {
                                                    imageViewModel.deleteImage(mediaId = mediaId)
                                                } else {
                                                    ToastUtils.error(
                                                        context = context,
                                                        message = "Invalid image URL"
                                                    )
                                                }
                                            },
                                            modifier = Modifier.size(40.dp),
                                            colors = IconButtonDefaults.iconButtonColors(
                                                containerColor = Red,
                                                contentColor = White
                                            )
                                        ) {
                                            Icon(
                                                imageVector = Icons.Default.Delete,
                                                contentDescription = "Delete image"
                                            )
                                        }
                                    }
                                }

                                Column(
                                    modifier = Modifier.weight(1f),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.spacedBy(10.dp)
                                ) {

                                    Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                                        Button(
                                            onClick = {
                                                selectedIndex = index

                                                galleryLauncher.launch(
                                                    PickVisualMediaRequest(
                                                        ActivityResultContracts.PickVisualMedia.ImageOnly
                                                    )
                                                )
                                            }
                                        ) {
                                            Text("Gallery")
                                        }
                                        Button(
                                            onClick = {
                                                selectedIndex = index

                                                val file = File.createTempFile(
                                                    "camera_",
                                                    ".jpg",
                                                    context.cacheDir
                                                )

                                                pendingCameraUri = FileProvider.getUriForFile(
                                                    context,
                                                    "${context.packageName}.fileprovider",
                                                    file
                                                )

                                                cameraLauncher.launch(pendingCameraUri!!)
                                            }
                                        ) {
                                            Text("Camera")
                                        }
                                    }

                                    Spacer(Modifier.height(15.dp))
                                    Button(
                                        onClick = {
                                            imageViewModel.uploadImage(index)
                                        },
                                        enabled = selectedUri != null &&
                                                !imageState.isLoading &&
                                                !imageState.success
                                    ) {
                                        Text(
                                            if (imageState.isLoading) "Uploading..."
                                            else "Upload Image"
                                        )
                                    }

                                }
                            }

                            imageState.error?.let {
                                ToastUtils.error(context = context, message = "Maximum of 3 profile photos reached")
                            }
                        }
                    }
                }
            }
        }

        when (val state = uiStateProfileUpdate) {

            ProfileUpdateUIState.Idle -> {
                // Nothing
            }

            ProfileUpdateUIState.Loading -> {
                isLoading = true
            }

            is ProfileUpdateUIState.Success -> {
                isLoading = false
                ToastUtils.success(context = context, message = "Profile updated successfully")
                onSubmit()
            }

            is ProfileUpdateUIState.Error -> {
                isLoading = false
                Text(
                    text = state.message,
                    color = Color.Red
                )
            }
        }

        if (isSubmitVisible) {
            Spacer(Modifier.height(20.dp))
            PrimaryButton(
                text = stringResource(id = R.string.submit),
                modifier = Modifier.padding(16.dp),
                onClick = {

                    emailError = ValidationUtils.validateEmail(email = email.trim())
                    heightError =
                        ValidationUtils.validateField(field = height.trim(), fieldName = "Height")
                    educationError = ValidationUtils.validateField(
                        field = education.trim(),
                        fieldName = "Education"
                    )
                    cityError =
                        ValidationUtils.validateField(field = city.trim(), fieldName = "City")
                    districtError = ValidationUtils.validateField(
                        field = district.trim(),
                        fieldName = "District"
                    )
                    professionError = ValidationUtils.validateField(
                        field = profession.trim(),
                        fieldName = "Profession"
                    )
                    religionError = ValidationUtils.validateField(
                        field = religion.trim(),
                        fieldName = "Religion"
                    )

                    placeOfBirthError = ValidationUtils.validateField(
                        field = placeOfBirth.trim(),
                        fieldName = "Place of Birth"
                    )
                    birthTimeError = ValidationUtils.validateField(
                        field = birthTime.trim(),
                        fieldName = "Birth Time"
                    )

                    nativePlaceError = ValidationUtils.validateField(
                        field = nativePlace.trim(),
                        fieldName = "Native Place"
                    )
                    starError = ValidationUtils.validateField(
                        field = star.trim(),
                        fieldName = "Star"
                    )
                    rasiError = ValidationUtils.validateField(
                        field = rasi.trim(),
                        fieldName = "Rasi"
                    )
                    incomeError = ValidationUtils.validateField(
                        field = income.trim(),
                        fieldName = "Income/Salary"
                    )
                    placeOfProfessionError = ValidationUtils.validateField(
                        field = placeOfProfession.trim(),
                        fieldName = "Place Of Profession"
                    )
                    familyDetailsError = ValidationUtils.validateField(
                        field = familyDetails.trim(),
                        fieldName = "Family Details"
                    )

                    if (emailError != null) {
                        ToastUtils.error(context = context, message = emailError.toString())
                        return@PrimaryButton
                    }
                    if (heightError != null) {
                        ToastUtils.error(context = context, message = heightError.toString())
                        return@PrimaryButton
                    }
                    if (educationError != null) {
                        ToastUtils.error(context = context, message = educationError.toString())
                        return@PrimaryButton
                    }
                    if (cityError != null) {
                        ToastUtils.error(context = context, message = cityError.toString())
                        return@PrimaryButton
                    }
                    if (districtError != null) {
                        ToastUtils.error(context = context, message = districtError.toString())
                        return@PrimaryButton
                    }
                    if (professionError != null) {
                        ToastUtils.error(context = context, message = professionError.toString())
                        return@PrimaryButton
                    }
                    if (religionError != null) {
                        ToastUtils.error(context = context, message = religionError.toString())
                        return@PrimaryButton
                    }

                    if (placeOfBirthError != null) {
                        ToastUtils.error(context = context, message = placeOfBirthError.toString())
                        return@PrimaryButton
                    }
                    if (birthTimeError != null) {
                        ToastUtils.error(context = context, message = birthTimeError.toString())
                        return@PrimaryButton
                    }
                    if (nativePlaceError != null) {
                        ToastUtils.error(context = context, message = nativePlaceError.toString())
                        return@PrimaryButton
                    }
                    if (starError != null) {
                        ToastUtils.error(context = context, message = starError.toString())
                        return@PrimaryButton
                    }
                    if (rasiError != null) {
                        ToastUtils.error(context = context, message = rasiError.toString())
                        return@PrimaryButton
                    }
                    if (incomeError != null) {
                        ToastUtils.error(context = context, message = incomeError.toString())
                        return@PrimaryButton
                    }
                    if (placeOfProfessionError != null) {
                        ToastUtils.error(
                            context = context,
                            message = placeOfProfessionError.toString()
                        )
                        return@PrimaryButton
                    }
                    if (familyDetailsError != null) {
                        ToastUtils.error(context = context, message = familyDetailsError.toString())
                        return@PrimaryButton
                    }

                    viewModel.updateProfile(
                        height = height.trim(),
                        education = education.trim(),
                        profession = profession.trim(),
                        religion = religion.trim(),
                        fullAddress = "",
                        email = email.trim(),
                        city = city.trim(),
                        district = district.trim(),
                        maritalStatus = selectedMaritalStatus,
                        placeOfBirth = placeOfBirth.trim(),
                        birthTime = birthTime.trim(),
                        birthStar = star.trim(),
                        rasi = rasi.trim(),
                        workLocation = placeOfProfession.trim(),
                        nativePlace = nativePlace.trim(),
                        familyDetails = familyDetails.trim(),
                        income = income.trim()
                    )
                }
            )
        }
    }
}

fun getMediaIdFromUrl(url: String): String? {
    return try {
        val uri = Uri.parse(url)
        val segments = uri.pathSegments

        val mediaIndex = segments.indexOf("media")

        if (mediaIndex != -1 && mediaIndex + 1 < segments.size) {
            segments[mediaIndex + 1]
        } else {
            null
        }
    } catch (e: Exception) {
        null
    }
}


@Composable
fun ProfileTabButton(
    text: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    CustomButton(
        text = text,
        onClick = onClick,
        modifier = modifier,
        height = 40.dp,
        width = 0.dp,
        cornerRadius = 5.dp,

        textColor = if (selected) White else Black,

        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp,

        backgroundColor = if (selected) Maroon else White,

        borderColor = if (selected) Maroon else Black,

        borderStroke = 0.2.dp
    )
}

@Preview
@Composable
fun ProfileCreationScreenPreview() {
    ProfileCreationScreen(onSubmit = {}, onBack = {})
}