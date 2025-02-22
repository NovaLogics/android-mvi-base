package novalogics.android.mvibase.feature.home.presentation

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import novalogics.android.mvibase.core.data.remote.Response
import novalogics.android.mvibase.core.presentation.base.BaseViewModel
import novalogics.android.mvibase.core.util.getErrorMessage
import novalogics.android.mvibase.feature.home.domain.usecase.CountQuotesUseCase
import novalogics.android.mvibase.feature.home.domain.usecase.GetQuotesFromApiUseCase
import novalogics.android.mvibase.feature.home.domain.usecase.InsertQuotesToDbUseCase
import novalogics.android.mvibase.feature.home.presentation.state.HomeEffect
import novalogics.android.mvibase.feature.home.presentation.state.HomeIntent
import novalogics.android.mvibase.feature.home.presentation.state.HomeUiState

import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getQuotesFromApiUseCase: GetQuotesFromApiUseCase,
    private val countQuotesUseCase: CountQuotesUseCase,
    private val insertQuotesToDbUseCase: InsertQuotesToDbUseCase,
) : BaseViewModel<HomeIntent, HomeUiState, HomeEffect>(HomeUiState()) {

    init {
        handleIntent(HomeIntent.FetchLiveQuotes)
        observeTotalQuotes()
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
            updateState { copy(isLoading = true) }
            try {
                when (val response = getQuotesFromApiUseCase.invoke()) {
                    is Response.Success -> {
                        updateState { copy(isLoading = false, quotes = response.data) }
                        insertQuotesToDbUseCase.execute(response.data)

                        val totalQuotesLoaded = uiState.value.totalQuotesLoaded + response.data.count()
                        countQuotesUseCase.saveTotalQuotesLoaded(totalQuotesLoaded)
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

    private fun observeTotalQuotes() {
        viewModelScope.launch {
            countQuotesUseCase.getTotalQuotesLoaded().collect { count ->
                updateState { copy(totalQuotesLoaded = count) }
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
            sendEffect { HomeEffect.NavigateToItemDetail(itemId) }
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
