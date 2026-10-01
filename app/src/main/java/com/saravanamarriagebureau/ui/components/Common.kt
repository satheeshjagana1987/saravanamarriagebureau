package com.saravanamarriagebureau.ui.components

import android.widget.GridLayout
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.saravanamarriagebureau.R
import com.saravanamarriagebureau.data.model.Interest
import com.saravanamarriagebureau.data.model.Profile
import com.saravanamarriagebureau.ui.theme.*

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
            Text("Marriage Bureau", color = Maroon, fontWeight = FontWeight.SemiBold, fontSize = 28.sp)
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
        modifier = modifier.fillMaxWidth().height(50.dp),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Maroon)
    ) {
        Text(text, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun SecondaryButton(text: String, onClick: () -> Unit, modifier: Modifier = Modifier,) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier.fillMaxWidth().height(50.dp),
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
        modifier = modifier.width(width).height(height),
        shape = RoundedCornerShape(size = cornerRadius),
        colors = ButtonDefaults.buttonColors(containerColor = backgroundColor),
        contentPadding = PaddingValues(0.dp),
        border = BorderStroke(borderStroke, borderColor)
    ) {
        Text(text, fontWeight = fontWeight, color = textColor,
            fontSize = fontSize, textAlign = TextAlign.Center)
    }
}

@Composable
fun BorderLineCustomButton(text: String, onClick: () -> Unit, modifier: Modifier = Modifier,
                height: Dp, width: Dp, cornerRadius: Dp, textColor: Color, fontWeight: FontWeight, fontSize: TextUnit) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier.width(width).height(height),
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
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 10.dp),
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
    Card(
        modifier = Modifier.fillMaxWidth()
            .clickable { onOpen() },
        shape = RoundedCornerShape(5.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = CardDefaults.outlinedCardBorder(),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(10.dp)) {
            ProfilePhoto("", Modifier.size(74.dp))
            Spacer(Modifier.width(10.dp))
            Column(Modifier.weight(1f)) {
                Text(profile.name, fontWeight = FontWeight.Bold, color = Black)
                Spacer(Modifier.height(5.dp))
                Text("${profile.age} yrs", color = TextSecondary, fontWeight = FontWeight.Normal)
                Spacer(Modifier.height(5.dp))
                Text(profile.education, color = TextSecondary, fontWeight = FontWeight.Normal)
                Spacer(Modifier.height(5.dp))
                if (profile.verified) {
                    Text("● Admin Verified", color = Success, style = MaterialTheme.typography.labelSmall)
                }
            }
            Icon(Icons.Default.FavoriteBorder,
                null, tint = Maroon,
                modifier = Modifier.size(30.dp).clickable{onInterest()},
            )
        }
    }
}

@Composable
fun InterestCard(
    interest: Interest,
    onOpen: () -> Unit,
    onAcceptRejectButton: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth()
            .clickable { onOpen() },
        shape = RoundedCornerShape(5.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = CardDefaults.outlinedCardBorder(),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(5.dp)) {
            ProfilePhoto("", Modifier.size(74.dp))
            Spacer(Modifier.width(10.dp))
            Column(Modifier.weight(1f)) {
                Text(interest.name, fontWeight = FontWeight.Bold, color = Black)
                Spacer(Modifier.height(5.dp))
                Text("${interest.age} yrs", color = TextSecondary, fontWeight = FontWeight.Normal)
                Spacer(Modifier.height(5.dp))
                Text(interest.education, color = TextSecondary, fontWeight = FontWeight.Normal)

            }
            Spacer(modifier = Modifier.size(20.dp))
            Column(modifier = Modifier.fillMaxHeight()){
                CustomButton(
                    text = "Accept", onClick = {},
                    modifier = Modifier.padding(start = 0.dp, top = 0.dp, end = 0.dp, bottom = 0.dp),
                    height = 40.dp, width = 90.dp, cornerRadius = 20.dp, textColor = White,
                    fontWeight = FontWeight.SemiBold, fontSize = 14.sp, backgroundColor = Success
                )
                Spacer(Modifier.height(10.dp))
                CustomButton(
                    text = "Reject", onClick = {},
                    modifier = Modifier.padding(start = 0.dp, top = 0.dp, end = 0.dp, bottom = 0.dp),
                    height = 40.dp, width = 90.dp, cornerRadius = 20.dp, textColor = White,
                    fontWeight = FontWeight.SemiBold, fontSize = 14.sp, backgroundColor = Red
                )


            }
        }
    }
}

@Composable
fun PlayButton() {
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
    thirdValue: String? = null
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
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
            Spacer(modifier = Modifier.size(20.dp))
            Text(
                text = secondValue,
                color = Black,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold
            )
        }

        if (thirdValue != null) {
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
fun ActionButton(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    text: String,
    modifier: Modifier,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier
            .width(100.dp)
            .clickable { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Icon(
            imageVector = icon,
            contentDescription = text,
            tint = Maroon,
            modifier = Modifier.size(40.dp)
        )

    }
}
