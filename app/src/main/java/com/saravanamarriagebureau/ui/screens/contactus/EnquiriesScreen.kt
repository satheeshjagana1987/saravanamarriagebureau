package com.saravanamarriagebureau.ui.screens.contactus

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.saravanamarriagebureau.data.enums.EnquiryStatus
import com.saravanamarriagebureau.data.model.EnquiryItem
import com.saravanamarriagebureau.ui.components.CustomButton
import com.saravanamarriagebureau.ui.components.EnquiryCard
import com.saravanamarriagebureau.ui.components.LoadingDialog
import com.saravanamarriagebureau.ui.screens.admin.enquiries.AdminEnquiriesUIState
import com.saravanamarriagebureau.ui.screens.admin.enquiries.EnquiryTabButton
import com.saravanamarriagebureau.ui.theme.Black
import com.saravanamarriagebureau.ui.theme.Cream
import com.saravanamarriagebureau.ui.theme.Maroon
import com.saravanamarriagebureau.ui.theme.White
import com.saravanamarriagebureau.util.ToastUtils

@Composable
fun EnquiriesScreen(
    onEnquiryItemClicked: (EnquiryItem) -> Unit,
    viewModel: ContactUsViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    var isLoading by remember {
        mutableStateOf(false)
    }

    val uiStateEnquiries by viewModel.uiStateEnquiries.collectAsStateWithLifecycle()

    val newEnquiriesList by viewModel.newEnquiries.collectAsStateWithLifecycle()

    val resolvedEnquiriesList by viewModel.resolvedEnquiries.collectAsStateWithLifecycle()


    var selectedTab by remember {
        mutableStateOf(EnquiryStatus.NEW)
    }

    val displayedEnquiries = when (selectedTab) {
        EnquiryStatus.NEW -> newEnquiriesList
        EnquiryStatus.RESOLVED -> resolvedEnquiriesList
    }

    LaunchedEffect(Unit) {
        viewModel.getEnquiries()
    }

    LoadingDialog(isLoading = isLoading)

    LaunchedEffect(uiStateEnquiries) {

        when (val state = uiStateEnquiries) {

            is AdminEnquiriesUIState.Loading -> {
                isLoading = true
            }

            is AdminEnquiriesUIState.Success -> {
                isLoading = false
            }

            is AdminEnquiriesUIState.Error -> {
                isLoading = false
                ToastUtils.error(context, state.message)
            }

            else -> {
                isLoading = false
            }
        }
    }

    Column(
        Modifier
            .fillMaxSize()
            .background(Cream),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(Modifier.padding(horizontal = 16.dp)) {
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                EnquiryTabButton(
                    text = "NEW",
                    selected = selectedTab == EnquiryStatus.NEW,
                    onClick = {
                        if (selectedTab == EnquiryStatus.NEW) return@EnquiryTabButton
                        selectedTab = EnquiryStatus.NEW
                        viewModel.getEnquiries()
                    },
                    modifier = Modifier.weight(1f)
                )

                EnquiryTabButton(
                    text = "RESOLVED",
                    selected = selectedTab == EnquiryStatus.RESOLVED,
                    onClick = {
                        if (selectedTab == EnquiryStatus.RESOLVED) return@EnquiryTabButton
                        selectedTab = EnquiryStatus.RESOLVED
                        viewModel.getEnquiries()
                    },
                    modifier = Modifier.weight(1f)
                )


            }
            LazyColumn(
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (displayedEnquiries.isEmpty()) {
                    item {
                        Text(
                            text = "No enquiries found",
                            modifier = Modifier.padding(16.dp),
                            color = Black,
                            textAlign = TextAlign.Center
                        )
                    }
                } else {
                    items(
                        items = displayedEnquiries,
                        key = { enquiry -> enquiry.id }
                    ) { enquiry ->
                        EnquiryCard(
                            enquiry = enquiry,
                            onOpen = {
                                onEnquiryItemClicked(enquiry)
                            },
                        )
                    }
                }

            }
        }
    }
}

@Composable
fun EnquiryTabButton(
    text: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    CustomButton(
        text = text,
        onClick = onClick,
        modifier = modifier,
        height = 40.dp,
        width = 0.dp,
        cornerRadius = 5.dp,

        textColor = if (selected) White else Black,

        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp,

        backgroundColor = if (selected) Maroon else White,

        borderColor = if (selected) Maroon else Black,

        borderStroke = 0.2.dp
    )
}