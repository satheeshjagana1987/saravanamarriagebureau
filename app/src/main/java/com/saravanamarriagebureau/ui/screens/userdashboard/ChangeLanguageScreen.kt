package com.saravanamarriagebureau.ui.screens.userdashboard

import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.os.LocaleListCompat
import com.saravanamarriagebureau.R
import com.saravanamarriagebureau.data.model.LanguageOption
import com.saravanamarriagebureau.ui.components.ScreenHeader
import com.saravanamarriagebureau.ui.theme.Cream
import com.saravanamarriagebureau.ui.theme.Maroon
import com.saravanamarriagebureau.util.LanguageManager

@Composable
fun ChangeLanguageScreen(
    onBack: () -> Unit,
    onNext: () -> Unit
) {

    val currentLanguage ="en"



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
                .padding(20.dp)
        ) {

            Text(
                text = stringResource(R.string.select_language),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                RadioButton(
                    selected = currentLanguage == "en",
                    onClick = {
                        onNext()
                    }
                )

                Text(
                    text = stringResource(R.string.english),
                    modifier = Modifier.clickable {
                    }
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                RadioButton(
                    selected = currentLanguage == "te",
                    onClick = {
                        onNext()
                    }
                )

                Text(
                    text = stringResource(R.string.telugu),
                    modifier = Modifier.clickable {
                    }
                )
            }
        }

    }
}

@Preview
@Composable
fun ChangeLanguageScreenPreview() {
    ChangeLanguageScreen(onBack = {}, onNext = {})
}