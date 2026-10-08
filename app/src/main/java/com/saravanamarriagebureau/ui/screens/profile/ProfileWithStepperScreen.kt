package com.saravanamarriagebureau.ui.screens.profile

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.saravanamarriagebureau.ui.components.PrimaryButton
import com.saravanamarriagebureau.ui.components.ScreenHeader
import com.saravanamarriagebureau.ui.theme.CardBorder
import com.saravanamarriagebureau.ui.theme.Maroon
import com.saravanamarriagebureau.ui.theme.TextSecondary
import com.saravanamarriagebureau.ui.theme.White

@Composable
fun ProfileWithStepperScreen(onNext: () -> Unit, onBack: () -> Unit) {
    Column(
        Modifier.fillMaxSize().padding(16.dp).background(White),
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

@Preview
@Composable
fun ProfileWithStepperScreenPreview(){
    ProfileWithStepperScreen(onNext = {}, onBack = {})
}


