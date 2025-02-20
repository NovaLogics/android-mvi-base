package novalogics.android.mvibase.feature.home.domain.usecase

import novalogics.android.mvibase.core.data.remote.Response
import novalogics.android.mvibase.core.util.QUOTE_LIMIT
import novalogics.android.mvibase.feature.home.domain.model.Quote
import novalogics.android.mvibase.feature.home.domain.repository.QuoteRepository
import javax.inject.Inject

class GetQuotesUseCase @Inject constructor(
    private val quoteRepository: QuoteRepository
) {
    suspend operator fun invoke(quoteLimit: Int = QUOTE_LIMIT): Response<List<Quote>> {
        return quoteRepository.getQuotes(quoteLimit)
    }
}
