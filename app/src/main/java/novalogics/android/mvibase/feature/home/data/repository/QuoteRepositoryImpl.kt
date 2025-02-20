package novalogics.android.mvibase.feature.home.data.repository

import novalogics.android.mvibase.core.data.remote.Response
import novalogics.android.mvibase.feature.home.data.mapper.toDomain
import novalogics.android.mvibase.feature.home.data.remote.QuoteApiService
import novalogics.android.mvibase.feature.home.domain.model.Quote
import novalogics.android.mvibase.feature.home.domain.repository.QuoteRepository
import javax.inject.Inject

class QuoteRepositoryImpl @Inject constructor(
    private val apiService: QuoteApiService
) : QuoteRepository {

    override suspend fun getQuotes(limit: Int): Response<List<Quote>> {
        return try {
            val response = apiService.getQuotes(limit).map { it.toDomain() }
            Response.Success(response)
        } catch (e: Exception) {
            Response.Error(e)
        }
    }
}
