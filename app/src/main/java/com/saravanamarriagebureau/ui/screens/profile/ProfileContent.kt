package com.saravanamarriagebureau.ui.screens.profile

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.visible
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import com.saravanamarriagebureau.R
import com.saravanamarriagebureau.data.enums.InterestTab
import com.saravanamarriagebureau.data.model.ProfileData
import com.saravanamarriagebureau.data.model.demoInterests
import com.saravanamarriagebureau.ui.components.DividerLine
import com.saravanamarriagebureau.ui.components.LabelName
import com.saravanamarriagebureau.ui.components.PrimaryButton
import com.saravanamarriagebureau.ui.components.ProfileInfoRow
import com.saravanamarriagebureau.ui.components.ScreenHeader
import com.saravanamarriagebureau.ui.components.calculateAge
import com.saravanamarriagebureau.ui.theme.Cream
import com.saravanamarriagebureau.ui.theme.Maroon
import com.saravanamarriagebureau.ui.theme.Red
import com.saravanamarriagebureau.ui.theme.Success
import com.saravanamarriagebureau.ui.theme.Warning
import com.saravanamarriagebureau.util.Constants.NA
import timber.log.Timber

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ProfileContent(
    profile: ProfileData,
    onBack: () -> Unit,
    onInterest: () -> Unit,
    profileType: String,
    onMatchFixed: () -> Unit
) {

    Column(
        Modifier
            .fillMaxSize()
            .background(Cream),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ScreenHeader("Profile", onBack)
        Column(
            Modifier
                .fillMaxWidth()
                .background(Cream),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFF4F4F4))
            ) {

                Column(
                    modifier = Modifier.fillMaxSize()
                ) {

                    // ---------------------------------------------------------
                    // Profile image section
                    // ---------------------------------------------------------
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                    ) {

                        NetworkImage(profile.profilePhoto ?: "")

                        // Dark transparent gradient at bottom
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    Color.Black.copy(alpha = 0.05f)
                                )
                        )

                        // Top-right circular icon
                        Box(
                            modifier = Modifier
                                .padding(top = 28.dp, end = 18.dp)
                                .size(40.dp)
                                .align(Alignment.TopEnd)
                                .clip(CircleShape)
                                .visible(false)
                                .background(Color.Black.copy(alpha = 0.20f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Star,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(20.dp)
                            )
                        }

                        // Play button
                        Box(
                            modifier = Modifier
                                .size(92.dp)
                                .align(Alignment.Center)
                                .clip(CircleShape)
                                .visible(false)
                                .background(Color.White),
                            contentAlignment = Alignment.Center
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(82.dp)
                                    .clip(CircleShape)
                                    .visible(false)
                                    .background(Color(0xFF142D42)),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.PlayArrow,
                                    contentDescription = "Play video",
                                    tint = Color.White,
                                    modifier = Modifier.size(45.dp)
                                )
                            }
                        }

                        // Family Video label
                        Box(
                            modifier = Modifier
                                .align(Alignment.BottomEnd)
                                .padding(end = 28.dp, bottom = 18.dp)
                                .clip(RoundedCornerShape(20.dp))
                                .visible(false)
                                .background(Color(0xFF22272D).copy(alpha = .92f))
                                .padding(horizontal = 14.dp, vertical = 8.dp)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Default.PlayArrow,
                                    contentDescription = null,
                                    tint = Color.White,
                                    modifier = Modifier.size(17.dp)
                                )

                                Spacer(modifier = Modifier.width(6.dp))

                                Text(
                                    text = "Family Video",
                                    color = Color.White,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                        }
                    }

                    // ---------------------------------------------------------
                    // Information card
                    // ---------------------------------------------------------
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .offset(y = (-25).dp)
                            .clip(
                                RoundedCornerShape(
                                    topStart = 20.dp,
                                    topEnd = 20.dp
                                )
                            )
                            .background(Color.White)
                            .padding(
                                start = 16.dp,
                                end = 16.dp,
                                top = 5.dp
                            )
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Text(
                                text = profile.fullName,
                                color = Maroon,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            Icon(
                                imageVector = Icons.Default.Favorite,
                                contentDescription = "Success",
                                tint = Maroon,
                                modifier = Modifier
                                    .size(30.dp)
                            )
                        }

                        if (profile.isAdminVerified) {
                            LabelName(
                                labelName = "● "+stringResource(id = R.string.verified),
                                fontSize = 12.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Success,
                                modifier = Modifier.padding(0.dp)
                            )
                        } else {
                            LabelName(
                                labelName = "● "+stringResource(id = R.string.profile_under_review),
                                fontSize = 12.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Warning
                            )
                        }

                        Spacer(modifier = Modifier.height(10.dp))
                        val age =  if(profile.dateOfBirth.isNullOrEmpty()) {
                            "NA"
                        } else {
                            calculateAge(profile.dateOfBirth).toString()
                        }
                        ProfileInfoRow(
                            label = stringResource(id = R.string.age),
                            value = "$age yrs",
                            secondLabel = stringResource(id = R.string.height),
                            secondValue = profile.height,
                            thirdLabel = stringResource(id = R.string.religion),
                            thirdValue = profile.religion
                        )

                        DividerLine()

                        ProfileInfoRow(
                            label = stringResource(id = R.string.education),
                            value = profile.education ?: NA
                        )

                        DividerLine()

                        ProfileInfoRow(
                            label =stringResource(id = R.string.profession),
                            value = profile.profession ?: NA
                        )

                        DividerLine()
                        if(profile.isAdminVerified) {
                            ProfileInfoRow(
                                label = stringResource(id = R.string.full_address),
                                value = profile.fullAddress ?: NA
                            )

                            DividerLine()

                            ProfileInfoRow(
                                label = stringResource(id = R.string.mobile_number),
                                value = profile.profession ?: NA
                            )

                            DividerLine()
                        }

                        DividerLine()

                        ProfileInfoRow(
                            label = stringResource(id = R.string.place_of_birth),
                            value = profile.placeOfBirth ?: NA
                        )

                        DividerLine()

                        ProfileInfoRow(
                            label = stringResource(id = R.string.birth_time),
                            value = profile.birthTime ?: NA
                        )

                        DividerLine()

                        ProfileInfoRow(
                            label = stringResource(id = R.string.star),
                            value = profile.birthStar ?: NA
                        )

                        DividerLine()

                        ProfileInfoRow(
                            label = stringResource(id = R.string.rasi),
                            value = profile.rasi ?: NA
                        )

                        DividerLine()

                        ProfileInfoRow(
                            label = stringResource(id = R.string.place_of_profession),
                            value = profile.workLocation ?: NA
                        )

                        DividerLine()

                        ProfileInfoRow(
                            label = stringResource(id = R.string.salary),
                            value = profile.income ?: NA
                        )

                        DividerLine()

                        ProfileInfoRow(
                            label = stringResource(id = R.string.native_place),
                            value = profile.nativePlace ?: NA
                        )

                        DividerLine()

                        ProfileInfoRow(
                            label = stringResource(id = R.string.family_details),
                            value = profile.familyDetails ?: NA
                        )

                        DividerLine()

                        ProfileInfoRow(
                            label = "Family Video",
                            value = "Download"
                        )

                        DividerLine()

                        ProfileInfoRow(
                            label = "Voice Record",
                            value = "Download"
                        )
                    }
                    Spacer(Modifier.weight(1f))
                    Timber.e("profile ==>  $profile")
//                    Timber.e("profile ==> id ${profile.id} name ${profile.userId} isMatched ${profile.isMatched} isAdminVerified ${profile.isAdminVerified}")
//                    if(profileType == InterestTab.MATCH_FIXED.name && !profile.isAdminVerified) {
//                        LabelName(
//                            labelName = "Contact info will be revealed after successful payment.",
//                            textAlign = TextAlign.Center,
//                            fontWeight = FontWeight.SemiBold,
//                            fontSize = 14.sp, color = Red,
//                            modifier = Modifier.fillMaxWidth(),
//                        )
//                        Spacer(Modifier.height(20.dp))
//                        PrimaryButton(
//                            text = stringResource(id = R.string.proceed_payment),
//                            modifier = Modifier.padding(start = 16.dp, top = 0.dp, end = 16.dp, bottom = 50.dp),
//                            onClick = onMatchFixed
//                        )
//                    }
                }
            }
        }
    }
}

@Composable
fun NetworkImage(url: String) {
    SubcomposeAsyncImage(
        model = url,
        contentDescription = "Network image",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
        loading = {
            CircularProgressIndicator()
        },
        error = {
            Text("Failed to load image")
        }
    )
}