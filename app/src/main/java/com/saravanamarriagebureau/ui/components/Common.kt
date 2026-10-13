package com.saravanamarriagebureau.ui.components

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.saravanamarriagebureau.R
import com.saravanamarriagebureau.data.enums.Gender
import com.saravanamarriagebureau.data.enums.InterestStatus
import com.saravanamarriagebureau.data.enums.InterestTab
import com.saravanamarriagebureau.data.model.Interest
import com.saravanamarriagebureau.data.model.Profile
import com.saravanamarriagebureau.data.model.UserInterestsData
import com.saravanamarriagebureau.ui.theme.Black
import com.saravanamarriagebureau.ui.theme.Gray
import com.saravanamarriagebureau.ui.theme.Maroon
import com.saravanamarriagebureau.ui.theme.Red
import com.saravanamarriagebureau.ui.theme.SoftRose
import com.saravanamarriagebureau.ui.theme.Success
import com.saravanamarriagebureau.ui.theme.SuccessSoft
import com.saravanamarriagebureau.ui.theme.TextPrimary
import com.saravanamarriagebureau.ui.theme.TextSecondary
import com.saravanamarriagebureau.ui.theme.Warning
import com.saravanamarriagebureau.ui.theme.White

@Composable
fun AppLogo(compact: Boolean = false) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .size(if (compact) 48.dp else 70.dp)
                .clip(CircleShape)
                .background(SoftRose),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                Icons.Default.Favorite,
                contentDescription = null,
                tint = Maroon,
                modifier = Modifier.size(if (compact) 30.dp else 48.dp)
            )
        }
        if (!compact) {
            Text("Saravana Bhava", color = Maroon, fontWeight = FontWeight.Bold, fontSize = 28.sp)
            Text(
                "Marriage Bureau",
                color = Maroon,
                fontWeight = FontWeight.SemiBold,
                fontSize = 28.sp
            )
        }
    }
}

@Composable
fun PrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Maroon)
    ) {
        Text(text, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun SecondaryButton(text: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Text(text, color = Black, fontWeight = FontWeight.SemiBold)
    }
}

@Composable
fun CustomButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    height: Dp,
    width: Dp,
    cornerRadius: Dp,
    textColor: Color,
    fontWeight: FontWeight,
    fontSize: TextUnit,
    backgroundColor: Color,
    borderStroke: Dp = 1.dp,
    borderColor: Color = Gray,

    ) {
    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier
            .width(width)
            .height(height),
        shape = RoundedCornerShape(size = cornerRadius),
        colors = ButtonDefaults.buttonColors(containerColor = backgroundColor),
        contentPadding = PaddingValues(0.dp),
        border = BorderStroke(borderStroke, borderColor)
    ) {
        Text(
            text, fontWeight = fontWeight, color = textColor,
            fontSize = fontSize, textAlign = TextAlign.Center
        )
    }
}

@Composable
fun BorderLineCustomButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    height: Dp,
    width: Dp,
    cornerRadius: Dp,
    textColor: Color,
    fontWeight: FontWeight,
    fontSize: TextUnit
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier
            .width(width)
            .height(height),
        shape = RoundedCornerShape(size = cornerRadius)
    ) {
        Text(text, color = textColor, fontWeight = fontWeight, fontSize = fontSize)
    }
}

@Composable
fun ScreenHeader(
    title: String,
    onBack: (() -> Unit)? = null,
    action: (@Composable () -> Unit)? = null
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (onBack != null) {
            IconButton(onClick = onBack) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back")
            }
        }
        Text(
            title,
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Normal
        )
        action?.invoke()
    }
}

@Composable
fun VerifiedBadge() {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(50))
            .background(SuccessSoft)
            .padding(horizontal = 8.dp, vertical = 5.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(Icons.Default.Verified, null, tint = Success, modifier = Modifier.size(18.dp))
        Spacer(Modifier.width(4.dp))
        Text("Admin Verified", color = Success, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun ProfilePhoto(profilePic: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            // 1. Maintain a 1:1 aspect ratio so it stays a perfect circle
            .aspectRatio(1f)
            // 2. Change the shape to CircleShape
            .clip(CircleShape)
            .background(
                Brush.verticalGradient(
                    listOf(Color(0xFFFFE8DC), Color(0xFFF6C3B0))
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(R.drawable.profile_pic),
            contentDescription = "profile pic",
            modifier = Modifier.fillMaxSize(),
            // 3. Use ContentScale.Crop so the profile pic fills the circle cleanly without stretching
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun ProfileCard(
    profile: Profile,
    onInterest: () -> Unit,
    onOpen: () -> Unit
) {
    val context = LocalContext.current
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                if (profile.isAdminVerified == 1) {
                    onOpen()
                } else {
                    Toast.makeText(context, "Profile is not verified by admin", Toast.LENGTH_SHORT)
                        .show()
                }
            },
        shape = RoundedCornerShape(5.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(10.dp)) {
            if (profile.isPaidUser) {
                ProfilePhoto("", Modifier.size(74.dp))
            } else {
                BlurredProfileImage(
                    R.drawable.profile_pic,
                    isBlurred = true,
                    modifier = Modifier.size(74.dp)
                )
            }
            Spacer(Modifier.width(10.dp))
            Column(Modifier.weight(1f)) {
                LabelName(
                    labelName = profile.name,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Maroon,
                    modifier = Modifier.padding(0.dp)
                )
                LabelName(
                    labelName = "${profile.age} yrs",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    color = TextSecondary,
                    modifier = Modifier.padding(0.dp)
                )
                LabelName(
                    labelName = profile.education,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    color = TextSecondary,
                    modifier = Modifier.padding(0.dp)
                )
                if (profile.isAdminVerified == 1) {
                    LabelName(
                        labelName = "● " + stringResource(id = R.string.verified),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Success,
                        modifier = Modifier.padding(0.dp)
                    )
                } else {
                    LabelName(
                        labelName = "● " + stringResource(id = R.string.profile_under_review),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Warning
                    )
                }
            }
            if (profile.isAdminVerified == 1) {
                Icon(
                    Icons.Default.FavoriteBorder,
                    null, tint = Maroon,
                    modifier = Modifier
                        .size(30.dp)
                        .clickable { onInterest() },
                )
            }

        }
    }
}

@Composable
fun AdminUserCard(
    profile: Profile,
    onOpen: () -> Unit
) {
    val context = LocalContext.current
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                if (profile.isAdminVerified == 1) {
                    onOpen()
                } else {
                    Toast.makeText(context, "Profile is not verified by admin", Toast.LENGTH_SHORT)
                        .show()
                }
            },
        shape = RoundedCornerShape(5.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(10.dp)) {
            ProfilePhoto("", Modifier.size(74.dp))
            Spacer(Modifier.width(10.dp))
            Column(Modifier.weight(1f)) {
                LabelName(
                    labelName = profile.name,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Maroon,
                    modifier = Modifier.padding(0.dp)
                )
                Spacer(Modifier.height(5.dp))
                LabelName(
                    labelName = "${profile.age} yrs",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    color = TextSecondary,
                    modifier = Modifier.padding(0.dp)
                )
                Spacer(Modifier.height(5.dp))
                LabelName(
                    labelName = profile.education,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    color = TextSecondary,
                    modifier = Modifier.padding(0.dp)
                )
            }
            Spacer(Modifier.weight(1f))
            AdminUserStatusContent(
                profile = profile
            )
        }
    }
}

@Composable
fun AdminUserStatusContent(
    profile: Profile
) {
    when (profile.isAdminVerified) {
        0 -> {
            AdminStatusText(
                text = stringResource(R.string.pending),
                color = Warning
            )
        }

        1 -> {

            AdminStatusText(
                text = stringResource(R.string.accepted),
                color = Success
            )
        }

        2 -> {

            AdminStatusText(
                text = stringResource(R.string.rejected),
                color = Red
            )
        }

    }
}

@Composable
fun InterestCard(
    interest: UserInterestsData,
    selectedTab: InterestTab,
    onOpen: () -> Unit,
    onAcceptRejectButton: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onOpen() },
        shape = RoundedCornerShape(5.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(5.dp)) {
            ProfilePhoto("", Modifier.size(74.dp))
            Spacer(Modifier.width(10.dp))
            Column(Modifier.weight(1f)) {
                LabelName(
                    labelName = interest.name,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Maroon,
                    modifier = Modifier.padding(0.dp)
                )
                LabelName(
                    labelName = "${interest.dob} yrs",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    color = TextSecondary,
                    modifier = Modifier.padding(0.dp)
                )
                LabelName(
                    labelName = interest.education,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    color = TextSecondary,
                    modifier = Modifier.padding(0.dp)
                )
                if (interest.isAdminVerified) {
                    LabelName(
                        labelName = "● " + stringResource(id = R.string.verified),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Success,
                        modifier = Modifier.padding(0.dp)
                    )
                } else {
                    LabelName(
                        labelName = "● " + stringResource(id = R.string.profile_under_review),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Warning
                    )
                }


            }
            Spacer(modifier = Modifier.size(20.dp))
            InterestStatusContent(
                selectedTab,
                interest = interest,
                onAcceptRejectButton = onAcceptRejectButton
            )
        }
    }
}

@Composable
fun InterestStatusContent(
    selectedTab: InterestTab,
    interest: UserInterestsData,
    onAcceptRejectButton: () -> Unit
) {
    when (interest.status) {
        "PENDING" -> {
            if (selectedTab == InterestTab.RECEIVED) {
                Column {
                    CustomButton(
                        text = stringResource(R.string.accept),
                        onClick = onAcceptRejectButton,
                        modifier = Modifier,
                        height = 40.dp,
                        width = 80.dp,
                        cornerRadius = 20.dp,
                        textColor = White,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp,
                        backgroundColor = Success
                    )

                    Spacer(
                        Modifier.height(10.dp)
                    )

                    CustomButton(
                        text = stringResource(R.string.reject),
                        onClick = onAcceptRejectButton,
                        modifier = Modifier,
                        height = 40.dp,
                        width = 85.dp,
                        cornerRadius = 20.dp,
                        textColor = White,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp,
                        backgroundColor = Red
                    )
                }

            } else {
                if (selectedTab == InterestTab.MATCH_FIXED) {
                    StatusText(
                        text = stringResource(id = R.string.payment_pending),
                        color = Warning
                    )
                } else {
                    // SENT + PENDING
                    StatusText(
                        text = stringResource(id = R.string.pending),
                        color = Warning
                    )
                }
            }
        }

        "ACCEPTED" -> {

            StatusText(
                text = stringResource(R.string.accepted),
                color = Success
            )
        }

        "REJECTED" -> {

            StatusText(
                text = stringResource(R.string.rejected),
                color = Red
            )
        }

        "DONE" -> {

            StatusText(
                text = stringResource(R.string.payment_completed),
                color = Success
            )
        }
    }
}

@Composable
fun AdminInterestCard(
    interest: Interest,
    onOpen: () -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onOpen() },
        shape = RoundedCornerShape(5.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(5.dp)) {
            ProfilePhoto("", Modifier.size(74.dp))
            Spacer(Modifier.width(10.dp))
            Column(Modifier.weight(1f)) {
                LabelName(
                    labelName = interest.name,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Maroon,
                    modifier = Modifier.padding(0.dp)
                )
                LabelName(
                    labelName = "${interest.age} yrs",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    color = TextSecondary,
                    modifier = Modifier.padding(0.dp)
                )
                LabelName(
                    labelName = interest.education,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    color = TextSecondary,
                    modifier = Modifier.padding(0.dp)
                )
                if (interest.isAdminVerified) {
                    LabelName(
                        labelName = "● " + stringResource(id = R.string.verified),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Success,
                        modifier = Modifier.padding(0.dp)
                    )
                } else {
                    LabelName(
                        labelName = "● " + stringResource(id = R.string.profile_under_review),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Warning
                    )
                }


            }
            Spacer(modifier = Modifier.size(20.dp))
            AdminInterestStatusContent(
                interest = interest
            )
        }
    }
}

@Composable
fun AdminInterestStatusContent(
    interest: Interest
) {
    when (interest.status) {
        InterestStatus.PENDING -> {
            if (interest.type == InterestTab.RECEIVED) {
                AdminStatusText(
                    text = stringResource(id = R.string.pending),
                    color = Warning
                )

            } else {
                if (interest.type == InterestTab.MATCH_FIXED) {
                    AdminStatusText(
                        text = stringResource(id = R.string.payment_pending),
                        color = Warning
                    )
                } else {
                    // SENT + PENDING
                    AdminStatusText(
                        text = stringResource(id = R.string.pending),
                        color = Warning
                    )
                }
            }
        }

        InterestStatus.ACCEPTED -> {

            AdminStatusText(
                text = stringResource(R.string.accepted),
                color = Success
            )
        }

        InterestStatus.REJECTED -> {

            AdminStatusText(
                text = stringResource(R.string.rejected),
                color = Red
            )
        }

        InterestStatus.DONE -> {

            AdminStatusText(
                text = stringResource(R.string.payment_completed),
                color = Success
            )
        }
    }
}

@Composable
fun StatusText(
    text: String,
    color: Color
) {
    LabelName(
        labelName = text,
        fontSize = 14.sp,
        fontWeight = FontWeight.Bold,
        color = color,
        modifier = Modifier.padding(end = 5.dp)
    )
}

@Composable
fun AdminStatusText(
    text: String,
    color: Color
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        LabelName(
            labelName = text,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = color,
            modifier = Modifier.padding(end = 5.dp)
        )
        Spacer(Modifier.width(5.dp))
        Icon(
            imageVector = Icons.Default.KeyboardArrowRight,
            contentDescription = null, tint = TextSecondary,
        )
    }

}

@Composable
fun PlayButton(
) {
    Box(
        modifier = Modifier
            .size(58.dp)
            .clip(CircleShape)
            .background(Color.Black.copy(alpha = 0.72f)),
        contentAlignment = Alignment.Center
    ) {
        Icon(Icons.Default.PlayArrow, null, tint = Color.White, modifier = Modifier.size(34.dp))
    }
}

@Composable
fun ProfileInfoRow(
    label: String,
    value: String,
    secondLabel: String? = null,
    secondValue: String? = null,
    thirdLabel: String? = null,
    thirdValue: String? = null
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = label,
            color = TextSecondary,
            fontSize = 14.sp,
        )
        Spacer(modifier = Modifier.size(10.dp))
        VerticalDivider()
        Spacer(modifier = Modifier.size(10.dp))
        Text(
            text = value,
            color = Black,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold
        )

        if (secondLabel != null && secondValue != null) {
            Spacer(modifier = Modifier.size(10.dp))
            Text(
                text = secondLabel,
                color = TextSecondary,
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.size(10.dp))
            VerticalDivider()
            Spacer(modifier = Modifier.size(10.dp))
            Text(
                text = secondValue,
                color = Black,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold
            )
        }

        if (thirdValue != null && thirdLabel != null) {
            Spacer(modifier = Modifier.size(10.dp))
            Text(
                text = thirdLabel,
                color = TextSecondary,
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.size(10.dp))
            VerticalDivider()
            Spacer(modifier = Modifier.size(10.dp))

            Text(
                text = thirdValue,
                color = TextPrimary,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Composable
fun DividerLine() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(color = Gray)
    )
}

@Composable
fun VerticalDivider() {
    Box(
        modifier = Modifier
            .width(1.dp)
            .height(20.dp)
            .background(color = Gray)
    )
}

@Composable
fun LabelName(
    modifier: Modifier = Modifier,
    labelName: String,
    fontSize: TextUnit = 14.sp,
    fontWeight: FontWeight = FontWeight.Normal,
    color: Color = Maroon,
    textDecoration: TextDecoration = TextDecoration.None,
    textAlign: TextAlign = TextAlign.Start,
) {
    Text(
        text = labelName,
        fontSize = fontSize,
        color = color,
        fontWeight = fontWeight,
        modifier = modifier,
        textDecoration = textDecoration,
        textAlign = textAlign
    )
}

@Composable
fun BlurredProfileImage(
    imageRes: Int,
    isBlurred: Boolean,
    modifier: Modifier = Modifier
) {
    // Determine blur radius based on the toggle state
    val blurRadius = if (isBlurred) 16.dp else 0.dp

    Image(
        painter = painterResource(id = imageRes),
        contentDescription = "Profile Image",
        contentScale = ContentScale.Crop, // Ensures the profile image fills the circle properly
        modifier = modifier
            .size(120.dp)
            .clip(CircleShape) // Standard circular profile shape
            .blur(
                radius = blurRadius,
                // Bounding the blur to the circle shape prevents it from leaking out as a square blur
                edgeTreatment = BlurredEdgeTreatment(CircleShape)
            )
    )
}

@Composable
fun ShowToast(
    context: Context,
    message: String,
    duration: Int = Toast.LENGTH_SHORT
) {
    Toast.makeText(context, message, duration).show()
}

@Composable
fun LoadingView() {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            CircularProgressIndicator()
            Spacer(Modifier.height(20.dp))
            Text(
                text = "Please wait...",
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

@Composable
fun LoadingDialog(
    message: String = "Loading, please wait...",
    isLoading: Boolean = true
) {
    if (isLoading) {
        Dialog(
            onDismissRequest = { },
            properties = DialogProperties(
                dismissOnBackPress = false,
                dismissOnClickOutside = false,
                usePlatformDefaultWidth = true
            )
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                shape = RoundedCornerShape(20.dp),
                color = Color.White,
                shadowElevation = 8.dp
            ) {
                Column(
                    modifier = Modifier.padding(
                        horizontal = 24.dp,
                        vertical = 28.dp
                    ),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(44.dp),
                        color = Color(0xFF6750A4),
                        strokeWidth = 4.dp
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        text = "Please wait",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF222222)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = message,
                        fontSize = 14.sp,
                        color = Color(0xFF777777),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

@Composable
fun ErrorView(
    message: String,
    onRetry: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = message
        )

        Button(
            onClick = onRetry,
            modifier = Modifier.padding(top = 16.dp)
        ) {

            Text(
                text = "Retry"
            )
        }
    }
}

@Composable
fun GenderSelection(
    selectedGender: Gender?,
    onGenderSelected: (Gender) -> Unit
) {
    Column {

        Text(
            text = "Gender",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        Gender.entries.forEach { gender ->

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .selectable(
                        selected = selectedGender == gender,
                        onClick = {
                            onGenderSelected(gender)
                        },
                        role = Role.RadioButton
                    )
                    .padding(vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                RadioButton(
                    selected = selectedGender == gender,
                    onClick = {
                        onGenderSelected(gender)
                    }
                )

                Spacer(
                    modifier = Modifier.width(8.dp)
                )

                Text(
                    text = gender.genderName,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}
