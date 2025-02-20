package novalogics.android.mvibase.feature.home.data.remote

import novalogics.android.mvibase.core.data.remote.ApiConfig.ENDPOINT_RANDOM_QUOTES
import novalogics.android.mvibase.feature.home.data.model.QuoteDto
import retrofit2.http.GET
import retrofit2.http.Query

interface QuoteApiService {
    @GET(ENDPOINT_RANDOM_QUOTES)
    suspend fun getQuotes(
        @Query("limit") limit: Int
    ): List<QuoteDto>

    @GET(ENDPOINT_RANDOM_QUOTES)
    suspend fun getRandomQuotes(
        @Query("limit") limit: Int,
        @Query("maxLength") maxLength: Int,
        @Query("minLength") minLength: Int,
        @Query("tags") tags: String,
    ): List<QuoteDto>
}
