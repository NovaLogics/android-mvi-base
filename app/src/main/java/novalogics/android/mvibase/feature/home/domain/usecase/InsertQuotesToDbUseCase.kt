package novalogics.android.mvibase.feature.home.domain.usecase

import novalogics.android.mvibase.feature.home.domain.model.Quote
import novalogics.android.mvibase.feature.home.domain.repository.QuoteRepository
import javax.inject.Inject

class InsertQuotesToDbUseCase @Inject constructor(
    private val quoteRepository: QuoteRepository
) {

    suspend fun execute(quotes: List<Quote>) {
        quoteRepository.clearQuotes()
        quoteRepository.insertQuotes(quotes)
    }
}
