package com.saravanamarriagebureau.ui.screens.userdashboard.myenquiries

import androidx.lifecycle.ViewModel
import com.saravanamarriagebureau.data.repository.MyEnquiriesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MyEnquiriesViewModel @Inject constructor(
    private val myEnquiriesRepository: MyEnquiriesRepository
) : ViewModel() {
}