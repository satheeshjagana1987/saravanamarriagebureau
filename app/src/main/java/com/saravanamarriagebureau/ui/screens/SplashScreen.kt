package com.saravanamarriagebureau.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.saravanamarriagebureau.R
import com.saravanamarriagebureau.ui.components.AppLogo
import com.saravanamarriagebureau.ui.navigation.Routes
import com.saravanamarriagebureau.ui.theme.Black
import com.saravanamarriagebureau.ui.theme.Maroon
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {

    LaunchedEffect(Unit) {
        // Simulate a 3-second loading/animation delay
        delay(5000L)

        // Navigate to the next screen (e.g., "home") and clear the splash screen from history
        navController.navigate(Routes.Welcome) {
            popUpTo(Routes.Splash) { inclusive = true }
        }
    }
    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        Image(
            painter = painterResource(R.drawable.splash_bg),
            contentDescription = "splash background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    start = 24.dp,
                    end = 24.dp,
                    top = 80.dp,
                    bottom = 50.dp
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {

            AppLogo()

            Spacer(Modifier.height(20.dp))

            val normalStyle = SpanStyle(color = Black)
            val dotStyle = SpanStyle(color = Maroon) // Change to your preferred dot color

            Text(
                text = buildAnnotatedString {
                    withStyle(style = normalStyle) { append("Real Families") }
                    withStyle(style = dotStyle) { append("  •  ") }
                    withStyle(style = normalStyle) { append("Genuine Matches") }
                    withStyle(style = dotStyle) { append("  •  ") }
                    withStyle(style = normalStyle) { append("Forever") }
                },
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold
            )



            Spacer(Modifier.weight(1f))

            Text(
                text = "More than a match...",
                color = Maroon,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            )

            Text(
                text = "It's a relationship of families",
                color = Maroon,
                fontSize = 15.sp
            )
        }
    }
}

@Preview
@Composable
fun SplashScreenPreview() {
    val dummyNavController = rememberNavController()
    SplashScreen(navController = dummyNavController) // Passing a dummy NavController for preview
}

