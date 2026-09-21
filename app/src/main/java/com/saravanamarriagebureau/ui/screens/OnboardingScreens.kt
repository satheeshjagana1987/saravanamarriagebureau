package com.saravanamarriagebureau.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.saravanamarriagebureau.ui.components.PlayButton
import com.saravanamarriagebureau.ui.components.PrimaryButton
import com.saravanamarriagebureau.ui.components.ScreenHeader
import com.saravanamarriagebureau.ui.theme.CardBorder
import com.saravanamarriagebureau.ui.theme.Cream
import com.saravanamarriagebureau.ui.theme.Maroon
import com.saravanamarriagebureau.ui.theme.Success
import com.saravanamarriagebureau.ui.theme.TextSecondary

@Composable
fun OfferScreen(onContinue: () -> Unit) {
    Column(Modifier.fillMaxSize().background(Cream).padding(16.dp)) {
        ScreenHeader("Anubandham", action = { Text("🔔") })
        Card(
            Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFFFE9B5))
        ) {
            Column(
                Modifier.fillMaxWidth().padding(18.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Dasara Offer", color = Color(0xFFB25C00), fontWeight = FontWeight.Bold)
                Text("Free Registration", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
                Text("₹0/-", style = MaterialTheme.typography.displaySmall, color = Maroon, fontWeight = FontWeight.Black)
                Text("Oct 20 - Oct 27")
                Spacer(Modifier.height(12.dp))
                Button(onClick = onContinue) { Text("FREE REGISTRATION") }
            }
        }
        Spacer(Modifier.height(16.dp))
        Card(Modifier.fillMaxWidth()) {
            Column(Modifier.padding(20.dp)) {
                Text("After 7 days, Annual Membership", color = TextSecondary)
                Text("₹299 / Year", style = MaterialTheme.typography.headlineMedium, color = Maroon, fontWeight = FontWeight.Bold)
                Text("Only for Profile Viewing")
                Spacer(Modifier.height(12.dp))
                PrimaryButton("View Plans", onContinue)
            }
        }
        Spacer(Modifier.weight(1f))
        BottomBarPreview()
    }
}

@Composable
fun MembershipScreen(onContinue: () -> Unit, onBack: () -> Unit) {
    Column(Modifier.fillMaxSize().padding(16.dp)) {
        ScreenHeader("Choose Your Plan", onBack)
        PlanCard("Dasara Offer", "Free Registration", "₹0", selected = true)
        PlanCard("Yearly Membership", "For profile viewing only", "₹299 / Year", selected = false)
        Spacer(Modifier.weight(1f))
        PrimaryButton("Continue", onContinue)
    }
}

@Composable
private fun PlanCard(title: String, subtitle: String, price: String, selected: Boolean) {
    Card(
        Modifier.fillMaxWidth().padding(vertical = 8.dp),
        shape = RoundedCornerShape(12.dp),
        border = CardDefaults.outlinedCardBorder()
    ) {
        Row(Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Column(Modifier.weight(1f)) {
                Text(title, color = Maroon, fontWeight = FontWeight.Bold)
                Text(price, style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
                Text(subtitle, color = TextSecondary)
            }
            Icon(
                Icons.Default.CheckCircle,
                null,
                tint = if (selected) Success else CardBorder
            )
        }
    }
}



@Composable
fun VoiceBioScreen(onNext: () -> Unit, onBack: () -> Unit) {
    Column(
        Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ScreenHeader("Voice Bio", onBack)
        Stepper(current = 1)
        Spacer(Modifier.height(34.dp))
        Box(
            Modifier.size(110.dp).background(Maroon, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(Icons.Default.Mic, null, tint = Color.White, modifier = Modifier.size(52.dp))
        }
        Spacer(Modifier.height(18.dp))
        Text("Record Your Voice Bio", fontWeight = FontWeight.Bold)
        Text("(30 seconds)", color = TextSecondary)
        Text("Tell us about yourself\n(e.g. Education, Profession, Hobbies, Values...)", textAlign = TextAlign.Center)
        Spacer(Modifier.height(25.dp))
        Text("▂▃▅▇▆▃▅▇▃▂▅▆▃▇", color = Maroon, style = MaterialTheme.typography.headlineSmall)
        Text("00:30", color = TextSecondary)
        Spacer(Modifier.weight(1f))
        PrimaryButton("Start Recording", onNext)
    }
}

@Composable
fun ParentsTalkScreen(onNext: () -> Unit, onBack: () -> Unit) {
    Column(
        Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ScreenHeader("Parents Talk", onBack)
        Stepper(current = 2)
        Spacer(Modifier.height(20.dp))
        Card(Modifier.fillMaxWidth()) {
            Column(Modifier.padding(20.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                Text("👨‍👩‍👧", style = MaterialTheme.typography.displayMedium)
                Text("Parents Talk", fontWeight = FontWeight.Bold)
                Text("(20 seconds)")
                Text("\"మా అబ్బాయి/అమ్మాయి గురించి...\"", color = Maroon, fontWeight = FontWeight.SemiBold)
                Spacer(Modifier.height(18.dp))
                Text("▂▅▇▃▆▇▃▅▂", color = Maroon, style = MaterialTheme.typography.headlineSmall)
                Text("00:20")
            }
        }
        Spacer(Modifier.weight(1f))
        PrimaryButton("Start Recording", onNext)
    }
}

@Composable
private fun Stepper(current: Int) {
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        listOf("Video", "Voice Bio", "Parents Talk", "Details").forEachIndexed { index, label ->
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Box(
                    Modifier.size(24.dp).background(
                        if (index <= current) Maroon else CardBorder,
                        CircleShape
                    ),
                    contentAlignment = Alignment.Center
                ) { Text("${index + 1}", color = Color.White, style = MaterialTheme.typography.labelSmall) }
                Text(label, style = MaterialTheme.typography.labelSmall)
            }
        }
    }
}

@Composable
private fun BottomBarPreview() {
    Row(
        Modifier.fillMaxWidth().padding(top = 20.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Text("⌂\nHome", color = Maroon, textAlign = TextAlign.Center)
        Text("♡\nMatches", textAlign = TextAlign.Center)
        Text("▣\nMessages", textAlign = TextAlign.Center)
        Text("◉\nProfile", textAlign = TextAlign.Center)
    }
}
