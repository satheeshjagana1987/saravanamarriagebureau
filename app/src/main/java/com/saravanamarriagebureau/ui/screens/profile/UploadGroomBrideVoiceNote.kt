package com.saravanamarriagebureau.ui.screens.profile

import com.saravanamarriagebureau.data.model.UploadData

sealed interface UploadGroomBrideVoiceNote {
    data object Idle : UploadGroomBrideVoiceNote
    data object Loading : UploadGroomBrideVoiceNote
    data class Success(val message: String) : UploadGroomBrideVoiceNote
    data class Error(val message: String) : UploadGroomBrideVoiceNote
}