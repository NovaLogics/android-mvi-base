package novalogics.android.mvibase.core.arch

import novalogics.android.mvibase.core.arch.state.ViewUiState

/** Represents the state of the UI */
sealed class UiState : ViewUiState {
    data object Idle : UiState()
    data object Loading : UiState()
    data class Success(val data: Any) : UiState()
    data class Error(val message: String) : UiState()
}
