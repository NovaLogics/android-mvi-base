package novalogics.android.mvibase.feature.home.domain.usecase

import kotlinx.coroutines.flow.Flow
import novalogics.android.mvibase.feature.home.domain.model.Quote
import novalogics.android.mvibase.feature.home.domain.repository.QuoteRepository
import javax.inject.Inject

class GetQuotesFromDbUseCase @Inject constructor(
    private val quoteRepository: QuoteRepository,
) {

    suspend fun execute(quoteLimit: Int): Flow<List<Quote>> {
        return quoteRepository.getLastNQuotes(limit = quoteLimit)
    }

    suspend fun getQuoteById(quoteId: String): Quote? {
        return quoteRepository.getQuoteById(quoteId)
    }
}
