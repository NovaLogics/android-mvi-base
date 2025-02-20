package novalogics.android.mvibase.feature.home.presentation.state

import novalogics.android.mvibase.core.arch.state.ViewIntent

sealed class HomeIntent: ViewIntent {
    data object FetchLiveQuotes : HomeIntent()
    data class OnItemClick(val itemId: String) : HomeIntent()
}
