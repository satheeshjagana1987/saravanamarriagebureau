package com.saravanamarriagebureau.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.saravanamarriagebureau.R
import com.saravanamarriagebureau.ui.components.BorderLineCustomButton
import com.saravanamarriagebureau.ui.components.CustomButton
import com.saravanamarriagebureau.ui.theme.Black
import com.saravanamarriagebureau.ui.theme.Maroon
import com.saravanamarriagebureau.ui.theme.White

@Composable
fun LogoutDialog(
    onLogout: () -> Unit
) {
    val showDialog = remember { mutableStateOf(false) }

    if (showDialog.value) {
        AlertDialog(
            onDismissRequest = { showDialog.value = false },
            title = {
                Text(text = stringResource(id = R.string.logout))
            },
            text = {
                Text(text = stringResource(id = R.string.logout_dialog_content))
            },
            confirmButton = {
                CustomButton(
                    text = stringResource(id = R.string.yes), onClick = {
                        // 2. Navigate after dialog is removed
                        showDialog.value = false
//                        onLogout()
                    },
                    modifier = Modifier.padding(
                        start = 16.dp,
                        top = 0.dp,
                        end = 16.dp,
                        bottom = 0.dp
                    ),
                    height = 40.dp, width = 200.dp, cornerRadius = 20.dp, textColor = White,
                    fontWeight = FontWeight.SemiBold, fontSize = 12.sp, backgroundColor = Maroon
                )
            },
            dismissButton = {
                BorderLineCustomButton(
                    text = stringResource(id = R.string.no),
                    onClick = {
                        showDialog.value = false
                    },
                    modifier = Modifier.padding(
                        start = 16.dp,
                        top = 0.dp,
                        end = 16.dp,
                        bottom = 5.dp
                    ),
                    height = 40.dp, width = 200.dp, cornerRadius = 20.dp, textColor = Black,
                    fontWeight = FontWeight.SemiBold, fontSize = 12.sp
                )

            }
        )
    }
}

@Preview
@Composable
fun LogoutDialogPreview() {
    LogoutDialog(
        onLogout = {},
    )
}