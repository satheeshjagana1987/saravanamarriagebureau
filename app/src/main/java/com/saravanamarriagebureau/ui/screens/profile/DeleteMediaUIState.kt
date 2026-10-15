package com.saravanamarriagebureau.ui.screens.profile

import com.saravanamarriagebureau.data.model.DeleteMediaResponse

sealed class DeleteMediaUIState {

    data object Idle : DeleteMediaUIState()

    data object Loading : DeleteMediaUIState()

    data class Success(
        val data: DeleteMediaResponse,
        val message: String? = null
    ) : DeleteMediaUIState()

    data class Error(
        val message: String
    ) : DeleteMediaUIState()
}