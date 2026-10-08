package com.saravanamarriagebureau.ui.screens.userdashboard

import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.os.LocaleListCompat
import com.saravanamarriagebureau.R
import com.saravanamarriagebureau.data.model.LanguageOption
import com.saravanamarriagebureau.ui.components.ScreenHeader
import com.saravanamarriagebureau.ui.theme.Cream
import com.saravanamarriagebureau.ui.theme.Maroon

@Composable
fun ChangeLanguageScreen(
    onBack: () -> Unit,
) {
    val languages = listOf(
        LanguageOption(name = "English", code = "en"),
        LanguageOption("తెలుగు", "te"),
    )

    // 1. Get the currently active app language code
    val currentLocaleCode = remember {
        AppCompatDelegate.getApplicationLocales()[0]?.language ?: "en"
    }

    // 2. Track the selected language option in Compose state
    var selectedLanguage by remember {
        mutableStateOf(languages.firstOrNull { it.code == currentLocaleCode } ?: languages[0])
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Cream)
            .selectableGroup() // Crucial for screen-reader accessibility
    ) {
        ScreenHeader(title = stringResource(id = R.string.change_language), onBack)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .background(Cream)
                .selectableGroup() // Crucial for screen-reader accessibility
        ) {
            Text(
                text = "Select Language",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            languages.forEach { language ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        // Make the whole row clickable for better touch target accessibility
                        .selectable(
                            selected = (language == selectedLanguage),
                            onClick = {
                                selectedLanguage = language

                                // 3. Update the application locale instantly
                                val localeList = LocaleListCompat.forLanguageTags(language.code)
                                AppCompatDelegate.setApplicationLocales(localeList)
                            },
                            role = Role.RadioButton
                        )
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = (language == selectedLanguage),
                        onClick = null // Handled cleanly by the Row's modifier for accessibility
                    )

                    Text(
                        text = language.name,
                        fontSize = 14.sp,
                        color = Maroon,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(start = 16.dp)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun ChangeLanguageScreenPreview() {
    ChangeLanguageScreen(onBack = {})
}