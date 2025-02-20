package novalogics.android.mvibase.feature.home.presentation.state

import novalogics.android.mvibase.core.arch.state.ViewEffect

sealed class HomeEffect : ViewEffect {
    data object NavigateToItemDetail : HomeEffect()
    data class ShowMessage(val message: String) : HomeEffect()
}
