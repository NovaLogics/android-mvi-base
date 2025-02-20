package novalogics.android.mvibase.core.arch

/** Represents the state of the UI */
sealed class UiState {
    data object Idle : UiState()
    data object Loading : UiState()
    data class Success(val data: Any) : UiState()
    data class Error(val message: String) : UiState()
}
