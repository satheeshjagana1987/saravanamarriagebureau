package com.saravanamarriagebureau.ui.screens.profile

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.rememberAsyncImagePainter
import com.canhub.cropper.CropImageContract
import com.canhub.cropper.CropImageContractOptions
import com.canhub.cropper.CropImageOptions
import com.canhub.cropper.CropImageView
import com.saravanamarriagebureau.R
import com.saravanamarriagebureau.data.enums.ProfileTab
import com.saravanamarriagebureau.ui.components.CustomButton
import com.saravanamarriagebureau.ui.components.LabelName
import com.saravanamarriagebureau.ui.components.LoadingDialog
import com.saravanamarriagebureau.ui.components.LoadingView
import com.saravanamarriagebureau.ui.components.PrimaryButton
import com.saravanamarriagebureau.ui.components.ProfilePhoto
import com.saravanamarriagebureau.ui.screens.media.MediaUploadCompleteViewModel
import com.saravanamarriagebureau.ui.screens.media.MediaUploadInitiatedUIState
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
import kotlinx.coroutines.launch
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

    var selectedTab by remember {
        mutableStateOf(ProfileTab.PERSONAL)
    }

    var isSubmitVisible by rememberSaveable {
        mutableStateOf(true)
    }

    var selectedGender by remember {
        mutableStateOf("")
    }

    var selectedMaritalStatus by remember {
        mutableStateOf("")
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
    val videoState by imageViewModel.uploadVideoState.collectAsState()
    val mediaInitiatedUploadUiState by mediaInitiatedUploadViewModel.uiState.collectAsState()
    val mediaUploadCompleteUiState by mediaUploadCompleteViewModel.uiState.collectAsState()
    val context = LocalContext.current

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

    // 1. Crop launcher must be declared first
    val cropLauncher = rememberLauncherForActivityResult(
        contract = CropImageContract()
    ) { result ->
        if (result.isSuccessful) {
            result.uriContent?.let { croppedUri ->
                imageViewModel.setImage(croppedUri)
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
            imageViewModel.setImage(it)
        }
    }

    // Capture a full-size photo
    val cameraLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.TakePicture()
    ) { success ->
        if (success) {
            pendingCameraUri?.let {
                imageViewModel.setImage(it)
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
                        OutlinedTextField(
                            value = birthTime,
                            onValueChange = {
                                birthTime = it
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
                                    text = stringResource(id = R.string.birth_time),
                                    color = Color(0xFFB5B5BD),
                                    fontSize = 14.sp
                                )
                            }
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
                                    text = "Place of Profession",
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
//                            RadioButton(
//                                selected = selectedGender == stringResource(R.string.male).uppercase(),
//                                onClick = {
//                                    selectedGender = "MALE"
//                                }
//                            )
                            Spacer(Modifier.width(10.dp))
                            LabelName(
                                labelName = gender,
                                fontSize = 14.sp,
                                color = Black,
                                fontWeight = FontWeight.Normal
                            )
//                            Spacer(Modifier.width(10.dp))
//                            RadioButton(
//                                selected = selectedGender == stringResource(R.string.female).uppercase(),
//                                onClick = {
//                                    selectedGender = "FEMALE"
//                                }
//                            )
//
//                            LabelName(
//                                labelName = stringResource(id = R.string.female),
//                                fontSize = 14.sp,
//                                color = Black,
//                                fontWeight = FontWeight.Normal
//                            )
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
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(White)
                            .padding(16.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            state.imageUri?.let { uri ->
                                ProfilePhoto(uri, Modifier.size(100.dp))
                            } ?: run {
                                ProfilePhoto(
                                    defaultImage = R.drawable.profile_pic,
                                    Modifier.size(100.dp)
                                )
                            }
                            Spacer(Modifier.weight(1f))
//                            CustomButtonForUpload(
//                                text = stringResource(id = R.string.upload),
//                                onClick = {},
//                                modifier = Modifier.weight(1f),
//                            )
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(10.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(16.dp)
                            ) {
                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                                ) {
                                    Button(
                                        onClick = {
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

                                Button(
                                    onClick = {

                                        imageViewModel.uploadImage()
//                                        mediaInitiatedUploadViewModel.mediaUploadInitiated(
//                                            mediaType = PRIMARY_PHOTO,
//                                            fileSize = imageFileSize,
//                                            originalFileName = imageFileName
//                                        )
                                    },
                                    enabled = state.imageUri != null &&
                                            !state.isLoading && !state.success,
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Text(
                                        if (state.isLoading) "Uploading..."
                                        else "Upload Image"
                                    )
                                }

                                if (state.isLoading) {
                                    LoadingView()
                                }

                                if (state.success) {
                                    ToastUtils.success(context = context, message = state.message.toString())
//                                    mediaUploadCompleteViewModel.mediaUploadComplete()
                                }

                                state.error?.let {
                                    ToastUtils.error(context = context, message = it)
                                }

                                when (val state = mediaInitiatedUploadUiState) {

                                    MediaUploadInitiatedUIState.Idle -> {
                                        // Nothing
                                    }

                                    MediaUploadInitiatedUIState.Loading -> {
                                        LoadingView()
                                    }

                                    is MediaUploadInitiatedUIState.Success -> {
                                        Text(
                                            text = state.message ?: "Media initiated"
                                        )
                                    }

                                    is MediaUploadInitiatedUIState.Error -> {
                                        Text(
                                            text = state.message,
                                            color = Color.Red
                                        )
                                    }
                                }
                            }
                        }
                        Spacer(Modifier.height(20.dp))
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            LabelName(
                                labelName = stringResource(id = R.string.family_video),
                                fontSize = 14.sp,
                                color = Maroon,
                                fontWeight = FontWeight.Normal
                            )
                            Spacer(Modifier.weight(1f))
                            Column {
                                Button(
                                    onClick = {
                                        picker.launch(
                                            PickVisualMediaRequest(
                                                ActivityResultContracts.PickVisualMedia.VideoOnly
                                            )
                                        )
                                    }
                                ) {
                                    Text("Select Video")
                                }
                                Spacer(Modifier.height(10.dp))

                                Text(status)

                                Button(
                                    enabled = videoUri != null,
                                    onClick = {
                                        videoUri?.let { uri ->
                                            scope.launch {
                                                status = try {
                                                    imageViewModel.uploadVideo(
                                                        uri = uri,
                                                        context = context
                                                    )
                                                    "Upload completed"
                                                } catch (e: Exception) {
                                                    "Upload failed: ${e.message}"
                                                }
                                            }
                                        }
                                    }
                                ) {
                                    Text("Upload Video")
                                }

                                if (videoState.isLoading) {
                                    LoadingView()
                                }

                                if (videoState.success) {
                                    ToastUtils.success(context = context, message = state.message.toString())
//                                    mediaUploadCompleteViewModel.mediaUploadComplete()
                                }

                                videoState.error?.let {
                                    ToastUtils.error(context = context, message = it)
                                }
                            }

                        }

                        Spacer(Modifier.height(20.dp))
//                        Row(
//                            verticalAlignment = Alignment.CenterVertically,
//                            horizontalArrangement = Arrangement.Center
//                        ) {
//                            LabelName(
//                                labelName = stringResource(id = R.string.bride_groom_voice_record),
//                                fontSize = 14.sp,
//                                color = Maroon,
//                                fontWeight = FontWeight.Normal
//                            )
//                            Spacer(Modifier.weight(1f))
//                            CustomButtonForUpload(
//                                text = stringResource(id = R.string.upload),
//                                onClick = {},
//                                modifier = Modifier.weight(1f),
//                            )
//                        }

//                        Column {
//                            Button(
//                                onClick = {
//                                    if (!recording) {
//                                        val granted = ContextCompat.checkSelfPermission(
//                                            context,
//                                            Manifest.permission.RECORD_AUDIO
//                                        ) == PackageManager.PERMISSION_GRANTED
//
//                                        if (granted) {
//                                            try {
//                                                val voiceRecorder = VoiceRecorder(context)
//                                                voiceRecorder.start()
//                                                recorder = voiceRecorder
//                                                recording = true
//                                                status = "Recording..."
//                                            } catch (e: Exception) {
//                                                status = "Could not start recording"
//                                            }
//                                        } else {
//                                            permissionLauncher.launch(
//                                                Manifest.permission.RECORD_AUDIO
//                                            )
//                                        }
//                                    } else {
//                                        try {
//                                            val file = recorder!!.stop()
//                                            recorder = null
//                                            recording = false
//                                            status = "Voice note ready"
//
//                                            scope.launch {
//                                                status = try {
//                                                    imageViewModel.uploadVoiceGroomOrBrideRecord(
//                                                        file = file
//                                                    )
//                                                    "Voice note uploaded"
//                                                } catch (e: Exception) {
//                                                    "Upload failed: ${e.message}"
//                                                }
//                                            }
//                                        } catch (e: Exception) {
//                                            recorder?.cancel()
//                                            recorder = null
//                                            recording = false
//                                            status = "Could not finalize recording"
//                                        }
//                                    }
//                                }
//                            ) {
//                                Text(if (recording) "Stop & Upload" else "Record Voice Note")
//                            }
//
//                            if (recording) {
//                                TextButton(onClick = {
//                                    recorder?.cancel()
//                                    recorder = null
//                                    recording = false
//                                    status = "Recording cancelled"
//                                }) {
//                                    Text("Cancel")
//                                }
//                            }
//
//                            Text(status)
//                        }
                    }

//                    Spacer(Modifier.height(20.dp))
//                    Row(
//                        verticalAlignment = Alignment.CenterVertically,
//                        horizontalArrangement = Arrangement.Center
//                    ) {
//                        LabelName(
//                            labelName = stringResource(id = R.string.parent_voice_record),
//                            fontSize = 14.sp,
//                            color = Maroon,
//                            fontWeight = FontWeight.Normal
//                        )
//                        Spacer(Modifier.weight(1f))
//                        CustomButtonForUpload(
//                            text = stringResource(id = R.string.upload),
//                            onClick = {},
//                            modifier = Modifier.weight(1f),
//                        )
//                    }
//                    Spacer(Modifier.height(30.dp))
//                    LabelName(
//                        labelName = stringResource(id = R.string.video_voice_limit),
//                        fontSize = 12.sp,
//                        color = Red,
//                        fontWeight = FontWeight.Normal
//                    )

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
                        ToastUtils.error(context = context, message = placeOfProfessionError.toString())
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

@Composable
fun CustomButtonForUpload(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    CustomButton(
        text = text,
        onClick = onClick,
        height = 40.dp,
        width = 0.dp,
        modifier = modifier,
        cornerRadius = 5.dp,

        textColor = White,

        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp,

        backgroundColor = Maroon,

        borderColor = Maroon,

        borderStroke = 0.2.dp
    )
}

@Preview
@Composable
fun ProfileCreationScreenPreview() {
    ProfileCreationScreen(onSubmit = {}, onBack = {})
}