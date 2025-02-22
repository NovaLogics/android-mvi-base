package novalogics.android.mvibase.feature.home.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import novalogics.android.mvibase.core.data.remote.Response
import novalogics.android.mvibase.feature.home.data.local.dao.QuoteDao
import novalogics.android.mvibase.feature.home.data.mapper.toDomain
import novalogics.android.mvibase.feature.home.data.mapper.toEntity
import novalogics.android.mvibase.feature.home.data.remote.QuoteApiService
import novalogics.android.mvibase.feature.home.domain.model.Quote
import novalogics.android.mvibase.feature.home.domain.repository.QuoteRepository
import javax.inject.Inject

class QuoteRepositoryImpl @Inject constructor(
    private val apiService: QuoteApiService,
    private val quoteDao: QuoteDao,
) : QuoteRepository {

    override suspend fun fetchQuotesFromApi(limit: Int): Response<List<Quote>> {
        return try {
            val response = apiService.getQuotes(limit).map { it.toDomain() }
            Response.Success(response)
        } catch (e: Exception) {
            Response.Error(e)
        }
    }

    override suspend fun insertQuotes(quotes: List<Quote>?) {
        if (quotes == null) return
        quoteDao.insertQuotes(quotes.map { quote -> quote.toEntity() })
    }

    override suspend fun clearQuotes() {
        quoteDao.clearQuotes()
    }

    override suspend fun getQuoteById(quoteId: String): Quote? {
        return quoteDao.getQuoteById(quoteId = quoteId)?.toDomain()
    }

    override fun getAllQuotes(): Flow<List<Quote>> {
        return quoteDao.getAllQuotes().map { list ->
            list.map { quoteEntity -> quoteEntity.toDomain() }
        }
    }

    override fun getLastNQuotes(limit: Int): Flow<List<Quote>> {
        return quoteDao.getLastNQuotes(limit).map { list ->
            list.map { quoteEntity -> quoteEntity.toDomain() }
        }
    }

    override suspend fun getQuoteCount(): Int {
        return quoteDao.getQuoteCount()
    }
}
