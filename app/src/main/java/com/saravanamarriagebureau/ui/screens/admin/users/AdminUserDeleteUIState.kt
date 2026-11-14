import com.saravanamarriagebureau.data.model.DeleteUserData

sealed class AdminUserDeleteUIState {

    data object Idle : AdminUserDeleteUIState()

    data object Loading : AdminUserDeleteUIState()

    data class Success(
        val data: DeleteUserData,
        val message: String? = null
    ) : AdminUserDeleteUIState()

    data class Error(
        val message: String
    ) : AdminUserDeleteUIState()
}