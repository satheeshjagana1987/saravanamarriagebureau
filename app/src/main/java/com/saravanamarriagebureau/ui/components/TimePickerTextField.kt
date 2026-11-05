package com.saravanamarriagebureau.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.saravanamarriagebureau.R
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimePickerTextField(
    selectedTime: String,
    onTimeSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var showTimePicker by remember { mutableStateOf(false) }

    val timePickerState = rememberTimePickerState(
        is24Hour = false
    )

    OutlinedTextField(
        value = selectedTime,
        onValueChange = {},
        readOnly = true,
        placeholder = {
            Text(
                text = stringResource(id = R.string.birth_time),
                color = Color(0xFFB5B5BD),
                fontSize = 14.sp
            )
        },
        trailingIcon = {
            IconButton(onClick = { showTimePicker = true }) {
                Icon(
                    imageVector = Icons.Default.AccessTime,
                    contentDescription = "Select time"
                )
            }
        },
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color(0xFFE2E2E8),
            unfocusedBorderColor = Color(0xFFE2E2E8),
            cursorColor = Color(0xFF555555),
            focusedTextColor = Color(0xFF333333),
            unfocusedTextColor = Color(0xFF333333)
        ),
        modifier = modifier
            .fillMaxWidth()
            .height(52.dp)
            .clickable {
                showTimePicker = true
            },
        shape = RoundedCornerShape(12.dp),
    )

    if (showTimePicker) {
        AlertDialog(
            onDismissRequest = {
                showTimePicker = false
            },
            title = {
                Text("Select Time")
            },
            text = {
                TimePicker(
                    state = timePickerState
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        val hour = timePickerState.hour
                        val minute = timePickerState.minute

                        val amPm = if (hour < 12) "AM" else "PM"
                        val hour12 = when (hour % 12) {
                            0 -> 12
                            else -> hour % 12
                        }

                        val formattedTime = String.format(
                            Locale.US,
                            "%02d:%02d %s",
                            hour12,
                            minute,
                            amPm
                        )

                        onTimeSelected(formattedTime)
                        showTimePicker = false
                    }
                ) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        showTimePicker = false
                    }
                ) {
                    Text("Cancel")
                }
            }
        )
    }
}
