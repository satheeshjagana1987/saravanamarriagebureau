package com.saravanamarriagebureau.ui.screens.admin.users

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
import com.saravanamarriagebureau.R
import com.saravanamarriagebureau.data.enums.InterestTab
import com.saravanamarriagebureau.data.model.AdminUserDetailsResponse
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

@Composable
fun AdminUserContent(
    profile: AdminUserDetailsResponse,
    onBack: () -> Unit,
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

                        Image(
                            painter = painterResource(id = R.drawable.profile_pic),
                            contentDescription = "Profile photo",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxSize()

                        )

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

                        DividerLine()

                        ProfileInfoRow(
                            label = stringResource(id = R.string.full_name),
                            value = profile.data.fullName
                        )

                        DividerLine()

                        ProfileInfoRow(
                            label = stringResource(id = R.string.mobile_number),
                            value = profile.data.mobile
                        )

                        DividerLine()

                        ProfileInfoRow(
                            label = stringResource(id = R.string.email),
                            value = profile.data.email ?: NA
                        )

                        DividerLine()

                        ProfileInfoRow(
                            label = stringResource(id = R.string.date_of_birth),
                            value = profile.data.dateOfBirth ?: NA
                        )

                        DividerLine()

                        ProfileInfoRow(
                            label = stringResource(id = R.string.native_place),
                            value = profile.data.profile.nativePlace ?: NA
                        )

                        DividerLine()

                        ProfileInfoRow(
                            label = stringResource(id = R.string.city),
                            value = profile.data.profile.city ?: NA
                        )

                        DividerLine()

                        ProfileInfoRow(
                            label = stringResource(id = R.string.district),
                            value = profile.data.profile.district ?: NA
                        )

                        DividerLine()

                        ProfileInfoRow(
                            label = stringResource(id = R.string.education),
                            value = profile.data.profile.education ?: NA
                        )

                        DividerLine()

                        ProfileInfoRow(
                            label = stringResource(id = R.string.profession),
                            value = profile.data.profile.profession ?: NA
                        )

                        DividerLine()

                        ProfileInfoRow(
                            label = stringResource(id = R.string.star),
                            value = profile.data.profile.birthStar ?: NA
                        )

                        DividerLine()

                        ProfileInfoRow(
                            label = stringResource(id = R.string.rasi),
                            value = profile.data.profile.rasi ?: NA
                        )

                        DividerLine()

                        ProfileInfoRow(
                            label = stringResource(id = R.string.place_of_profession),
                            value = profile.data.profile.workLocation ?: NA
                        )

                        DividerLine()

                        ProfileInfoRow(
                            label = stringResource(id = R.string.salary),
                            value = profile.data.profile.income ?: NA
                        )

                        DividerLine()

                        ProfileInfoRow(
                            label = stringResource(id = R.string.family_details),
                            value = profile.data.profile.familyDetails ?: NA
                        )

                    }


                }



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
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                PrimaryButton(
                    text = stringResource(id = R.string.approve),
                    modifier = Modifier.padding(
                        start = 16.dp,
                        top = 0.dp,
                        end = 16.dp,
                        bottom = 50.dp
                    ),
                    onClick = {}
                )

                PrimaryButton(
                    text = stringResource(id = R.string.reject),
                    modifier = Modifier.padding(
                        start = 16.dp,
                        top = 0.dp,
                        end = 16.dp,
                        bottom = 50.dp
                    ),
                    onClick = {}
                )

            }
        }
    }
}
