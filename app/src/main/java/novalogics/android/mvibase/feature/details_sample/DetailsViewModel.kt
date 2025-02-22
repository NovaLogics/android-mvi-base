package novalogics.android.mvibase.feature.details_sample

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import novalogics.android.mvibase.feature.home.domain.model.Quote
import novalogics.android.mvibase.feature.home.domain.repository.QuoteRepository
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val quoteRepository: QuoteRepository
) : ViewModel() {

    private val _quote = MutableStateFlow<Quote?>(null)
    val quote: StateFlow<Quote?> = _quote.asStateFlow()

    fun loadQuote(quoteId: String) {
        viewModelScope.launch {
            _quote.value = quoteRepository.getQuoteById(quoteId)
        }
    }
}
