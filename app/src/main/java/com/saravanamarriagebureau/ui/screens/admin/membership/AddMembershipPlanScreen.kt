package com.saravanamarriagebureau.ui.screens.admin.membership

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.saravanamarriagebureau.R
import com.saravanamarriagebureau.ui.components.LabelName
import com.saravanamarriagebureau.ui.components.LoadingDialog
import com.saravanamarriagebureau.ui.components.PrimaryButton
import com.saravanamarriagebureau.ui.theme.Cream
import com.saravanamarriagebureau.ui.theme.Maroon
import com.saravanamarriagebureau.util.ToastUtils
import com.saravanamarriagebureau.util.ValidationUtils

@Composable
fun AddMembershipPlanScreen(
    onBack: () -> Unit,
    viewModel: AdminMembershipViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    var isLoading by remember {
        mutableStateOf(false)
    }

    var planCode by remember { mutableStateOf("") }
    var planName by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var duration by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }

    var planCodeError by remember { mutableStateOf<String?>(null) }
    var planNameError by remember { mutableStateOf<String?>(null) }
    var descriptionError by remember { mutableStateOf<String?>(null) }
    var durationError by remember { mutableStateOf<String?>(null) }
    var priceError by remember { mutableStateOf<String?>(null) }


    val uiStateAddAdminMembershipPlan by viewModel.uiStateAddAdminMembershipPlan.collectAsStateWithLifecycle()
    LoadingDialog(isLoading = isLoading)

    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(Cream),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(Modifier.padding(horizontal = 16.dp)) {
            Spacer(Modifier.height(20.dp))

            Card(
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                LabelName(
                    labelName = stringResource(id = R.string.plan_code),
                    fontSize = 14.sp,
                    color = Maroon,
                    fontWeight = FontWeight.Normal
                )
                Spacer(Modifier.height(5.dp))
                OutlinedTextField(
                    value = planCode,
                    onValueChange = {
                        planCode = it
                    },
                    readOnly = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp),
                    singleLine = true,
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFFE2E2E8),
                        unfocusedBorderColor = Color(0xFFE2E2E8),
                        cursorColor = Color(0xFF555555),
                        focusedTextColor = Color(0xFF333333),
                        unfocusedTextColor = Color(0xFF333333)
                    ),

                    placeholder = {
                        Text(
                            text = "Enter Plan Code",
                            color = Color(0xFFB5B5BD),
                            fontSize = 14.sp
                        )
                    }
                )

                Spacer(Modifier.height(10.dp))

                LabelName(
                    labelName = stringResource(id = R.string.plan_name),
                    fontSize = 14.sp,
                    color = Maroon,
                    fontWeight = FontWeight.Normal
                )
                Spacer(Modifier.height(5.dp))
                OutlinedTextField(
                    value = planName,
                    onValueChange = {
                        planName = it
                    },
                    readOnly = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp),
                    singleLine = true,
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFFE2E2E8),
                        unfocusedBorderColor = Color(0xFFE2E2E8),
                        cursorColor = Color(0xFF555555),
                        focusedTextColor = Color(0xFF333333),
                        unfocusedTextColor = Color(0xFF333333)
                    ),

                    placeholder = {
                        Text(
                            text = "Enter Plan Code",
                            color = Color(0xFFB5B5BD),
                            fontSize = 14.sp
                        )
                    }
                )

                Spacer(Modifier.height(10.dp))

                LabelName(
                    labelName = stringResource(id = R.string.description),
                    fontSize = 14.sp,
                    color = Maroon,
                    fontWeight = FontWeight.Normal
                )
                Spacer(Modifier.height(5.dp))
                OutlinedTextField(
                    value = description,
                    onValueChange = {
                        description = it
                    },
                    readOnly = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp),
                    singleLine = true,
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFFE2E2E8),
                        unfocusedBorderColor = Color(0xFFE2E2E8),
                        cursorColor = Color(0xFF555555),
                        focusedTextColor = Color(0xFF333333),
                        unfocusedTextColor = Color(0xFF333333)
                    ),

                    placeholder = {
                        Text(
                            text = "Enter Plan Code",
                            color = Color(0xFFB5B5BD),
                            fontSize = 14.sp
                        )
                    }
                )

                Spacer(Modifier.height(10.dp))

                LabelName(
                    labelName = stringResource(id = R.string.duration),
                    fontSize = 14.sp,
                    color = Maroon,
                    fontWeight = FontWeight.Normal
                )
                Spacer(Modifier.height(5.dp))
                OutlinedTextField(
                    value = duration,
                    onValueChange = {
                        duration = it
                    },
                    readOnly = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp),
                    singleLine = true,
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFFE2E2E8),
                        unfocusedBorderColor = Color(0xFFE2E2E8),
                        cursorColor = Color(0xFF555555),
                        focusedTextColor = Color(0xFF333333),
                        unfocusedTextColor = Color(0xFF333333)
                    ),

                    placeholder = {
                        Text(
                            text = "Enter Plan Code",
                            color = Color(0xFFB5B5BD),
                            fontSize = 14.sp
                        )
                    }
                )

                Spacer(Modifier.height(10.dp))

                LabelName(
                    labelName = stringResource(id = R.string.price),
                    fontSize = 14.sp,
                    color = Maroon,
                    fontWeight = FontWeight.Normal
                )
                Spacer(Modifier.height(5.dp))
                OutlinedTextField(
                    value = price,
                    onValueChange = {
                        price = it
                    },
                    readOnly = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp),
                    singleLine = true,
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFFE2E2E8),
                        unfocusedBorderColor = Color(0xFFE2E2E8),
                        cursorColor = Color(0xFF555555),
                        focusedTextColor = Color(0xFF333333),
                        unfocusedTextColor = Color(0xFF333333)
                    ),

                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    ),

                    placeholder = {
                        Text(
                            text = "Enter Price",
                            color = Color(0xFFB5B5BD),
                            fontSize = 14.sp
                        )
                    }
                )

                Spacer(Modifier.height(10.dp))
            }
        }

        Spacer(Modifier.weight(1f))

        PrimaryButton(
            text = stringResource(id = R.string.submit),
            modifier = Modifier.padding(16.dp),
            onClick = {


                planCodeError =
                    ValidationUtils.validateField(field = planCode.trim(), fieldName = "Plan Code")
                planNameError = ValidationUtils.validateField(
                    field = planName.trim(),
                    fieldName = "Plan Name"
                )
                descriptionError = ValidationUtils.validateField(
                    field = description.trim(),
                    fieldName = "Description"
                )
                durationError = ValidationUtils.validateField(
                    field = duration.trim(),
                    fieldName = "Duration"
                )

                priceError = ValidationUtils.validateField(
                    field = price.trim(),
                    fieldName = "Price"
                )

                if (planCodeError != null) {
                    ToastUtils.error(context = context, message = planCodeError.toString())
                    return@PrimaryButton
                }
                if (planNameError != null) {
                    ToastUtils.error(context = context, message = planNameError.toString())
                    return@PrimaryButton
                }
                if (descriptionError != null) {
                    ToastUtils.error(context = context, message = descriptionError.toString())
                    return@PrimaryButton
                }
                if (durationError != null) {
                    ToastUtils.error(context = context, message = durationError.toString())
                    return@PrimaryButton
                }
                if (priceError != null) {
                    ToastUtils.error(context = context, message = priceError.toString())
                    return@PrimaryButton
                }

                viewModel.addAdminMemberShipPlan(
                    code = planCode.trim(),
                    name = planName.trim(),
                    description = description.trim(),
                    durationDays = duration.toInt(),
                    priceMinorUnits = price.toInt(),
                )

            }
        )

        when (val state = uiStateAddAdminMembershipPlan) {

            AddAdminMembershipPlanUIState.Loading -> {
                isLoading = true
            }

            is AddAdminMembershipPlanUIState.Success -> {
                isLoading = false
            }

            is AddAdminMembershipPlanUIState.Error -> {
                isLoading = false
                ToastUtils.error(context = context, message = state.message)
            }

            else -> {
                isLoading = false
            }

        }
    }
}