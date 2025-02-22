package novalogics.android.mvibase.feature.home.domain.repository

import kotlinx.coroutines.flow.Flow
import novalogics.android.mvibase.core.data.remote.Response
import novalogics.android.mvibase.feature.home.domain.model.Quote

interface QuoteRepository {
    suspend fun fetchQuotesFromApi(limit: Int): Response<List<Quote>>
    suspend fun insertQuotes(quotes: List<Quote>?)
    suspend fun clearQuotes()
    suspend fun getQuoteById(quoteId: String): Quote?
    suspend fun getQuoteCount(): Int
    fun getAllQuotes(): Flow<List<Quote>>
    fun getLastNQuotes(limit: Int): Flow<List<Quote>>
}
