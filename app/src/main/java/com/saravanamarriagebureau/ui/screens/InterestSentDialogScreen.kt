package com.saravanamarriagebureau.ui.screens

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.saravanamarriagebureau.ui.components.BorderLineCustomButton
import com.saravanamarriagebureau.ui.components.CustomButton
import com.saravanamarriagebureau.ui.components.PrimaryButton
import com.saravanamarriagebureau.ui.theme.Black
import com.saravanamarriagebureau.ui.theme.Cream
import com.saravanamarriagebureau.ui.theme.Maroon
import com.saravanamarriagebureau.ui.theme.Success
import com.saravanamarriagebureau.ui.theme.White

@Composable
fun InterestSentDialogScreen(
    onMatches: () -> Unit,
    onHome: () -> Unit,
) {

    AlertDialog(
        // Leave this empty to disable clicking outside to close
        onDismissRequest = {},

        // 1. Customize Background & Shape
        containerColor = Cream, // Custom warm-white / cream background
        shape = RoundedCornerShape(24.dp),  // Softer rounded corners
        tonalElevation = 0.dp,              // Disables the default Material 3 color tinting overlay

        // 1. Add your icon using the dedicated icon slot
        icon = {
            val infiniteTransition = rememberInfiniteTransition(label = "pulse")
            val scale by infiniteTransition.animateFloat(
                initialValue = 0.8f,
                targetValue = 1.2f,
                animationSpec = infiniteRepeatable(
                    animation = tween(1000, easing = LinearOutSlowInEasing),
                    repeatMode = RepeatMode.Reverse
                ),
                label = "scale"
            )

            Icon(
                imageVector = Icons.Default.CheckCircle,
                contentDescription = "Success",
                tint = Success,
                modifier = Modifier
                    .size(48.dp)
                    .graphicsLayer(scaleX = scale, scaleY = scale) // Applies the pulsing effect
            )
        },

        // 3. Customize Body Text Style
        title = {
            Text(
                text = "Interest Sent",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Success, // Muted slate gray
                )
            )
        },

        // 3. Customize Body Text Style
        text = {
            Text(
                text = "Your interest has been sent. Our team will connect you if there is mutual interest.",
                style = TextStyle(
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    color = Success, // Muted slate gray
                )
            )
        },

        // 4. Customize Action Buttons
        confirmButton = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {

                CustomButton(
                    text = "View Matches", onClick = onMatches,
                    modifier = Modifier.padding(start = 16.dp, top = 0.dp, end = 16.dp, bottom = 0.dp),
                    height = 40.dp, width = 200.dp, cornerRadius = 20.dp, textColor = White,
                    fontWeight = FontWeight.SemiBold, fontSize = 12.sp, backgroundColor = Maroon
                )
                Spacer(Modifier.height(10.dp))
                BorderLineCustomButton(
                    text = "Go To Home",
                    onClick = onHome,
                    modifier = Modifier.padding(start = 16.dp, top = 0.dp, end = 16.dp, bottom = 5.dp),
                    height = 40.dp, width = 200.dp, cornerRadius = 20.dp, textColor = Black,
                    fontWeight = FontWeight.SemiBold, fontSize = 12.sp
                )

            }
        }
    )
//    Column(
//        Modifier.fillMaxSize()
//            .padding(24.dp)
//            .background(Cream),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
//    ) {
//        Icon(Icons.Default.Favorite, null, tint = Maroon, modifier = Modifier.size(100.dp))
//        Text("Interest Sent!", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
//        Text("Your interest has been sent. We will connect you if there is mutual interest.", textAlign = androidx.compose.ui.text.style.TextAlign.Center)
//        Spacer(Modifier.height(28.dp))
//        PrimaryButton(text = "View Matches", onClick = onMatches)
//        Spacer(Modifier.height(10.dp))
//        SecondaryButton(text = "Go to Home", onClick = onHome)
//    }
}

@Preview(
    name = "Custom Dialog Preview",
    showBackground = true, // Ensures a background is rendered behind the overlay
    widthDp = 360,
    heightDp = 640
)
@Composable
fun InterestSentDialogScreenPreview(){
    // 2. Wrap it inside your design theme context
    MaterialTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                // 3. Render the dialog with mock data and empty click handlers
                InterestSentDialogScreen(
                    onMatches = { /* Do nothing in preview */ },
                    onHome = { /* Do nothing in preview */ }
                )
            }
        }
    }
}
