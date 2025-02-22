package novalogics.android.mvibase.feature.home.presentation.state

import novalogics.android.mvibase.core.arch.state.ViewEffect

sealed class HomeEffect : ViewEffect {
    data class NavigateToItemDetail(val itemId: String) : HomeEffect()
    data class ShowMessage(val message: String) : HomeEffect()
}
