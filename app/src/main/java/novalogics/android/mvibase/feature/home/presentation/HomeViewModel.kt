package novalogics.android.mvibase.feature.home.presentation

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import novalogics.android.mvibase.core.data.remote.Response
import novalogics.android.mvibase.core.presentation.base.BaseViewModel
import novalogics.android.mvibase.core.util.getErrorMessage
import novalogics.android.mvibase.feature.home.domain.usecase.GetQuotesUseCase
import novalogics.android.mvibase.feature.home.presentation.state.HomeEffect
import novalogics.android.mvibase.feature.home.presentation.state.HomeIntent
import novalogics.android.mvibase.feature.home.presentation.state.HomeUiState

import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getQuotesUseCase: GetQuotesUseCase,
) : BaseViewModel<HomeIntent, HomeUiState, HomeEffect>(HomeUiState()) {

    init {
        handleIntent(HomeIntent.FetchLiveQuotes)
    }

    override fun handleIntent(intent: HomeIntent) {
        when (intent) {
            is HomeIntent.FetchLiveQuotes -> fetchLiveQuotes()
            is HomeIntent.OnItemClick -> handleItemClick(intent.itemId)
        }
    }

    /**
     * Fetches quotes from the API.
     */
    private fun fetchLiveQuotes() {
        viewModelScope.launch {
            updateState { copy(isLoading = false) }
            try {
                when (val response = getQuotesUseCase.invoke()) {
                    is Response.Success -> {
                        updateState { copy(isLoading = false, quotes = response.data) }
                    }
                    is Response.Error -> {
                        updateState { copy(isLoading = false) }
                        sendEffect { HomeEffect.ShowMessage(response.exception.getErrorMessage()) }
                    }
                    Response.Loading -> Unit
                }
            } catch (exception: Exception) {
                handleException(exception)
            }
        }
    }

    /**
     * Handles item click events.
     * @param itemId The ID of the clicked item.
     */
    private fun handleItemClick(itemId: String) {
        viewModelScope.launch {
            sendEffect { HomeEffect.ShowMessage("Quote Id: $itemId") }
            sendEffect { HomeEffect.NavigateToItemDetail }
        }
    }

    /**
     * Handles exceptions by updating UI state and sending effects.
     * @param exception The thrown exception.
     */
    private fun handleException(exception: Exception) {
        val errorMessage = exception.getErrorMessage()
        updateState { copy(isLoading = false, error = errorMessage) }
        sendEffect { HomeEffect.ShowMessage(errorMessage) }
    }
}
