package novalogics.android.mvibase.feature.home.presentation.state

import novalogics.android.mvibase.core.arch.state.ViewUiState
import novalogics.android.mvibase.core.util.emptyString
import novalogics.android.mvibase.feature.home.domain.model.Quote

data class HomeUiState(
    val isLoading: Boolean = false,
    val quotes: List<Quote> = emptyList(),
    val error: String = emptyString(),
) : ViewUiState
