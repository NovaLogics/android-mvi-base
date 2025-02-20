package novalogics.android.mvibase.feature.home.domain.repository

import novalogics.android.mvibase.core.data.remote.Response
import novalogics.android.mvibase.feature.home.domain.model.Quote

interface QuoteRepository {
    suspend fun getQuotes(limit: Int): Response<List<Quote>>
}
