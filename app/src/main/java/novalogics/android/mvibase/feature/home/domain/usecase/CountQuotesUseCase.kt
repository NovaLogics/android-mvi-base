package novalogics.android.mvibase.feature.home.domain.usecase

import kotlinx.coroutines.flow.Flow
import novalogics.android.mvibase.core.data.local.datastore.DataStoreKeys
import novalogics.android.mvibase.core.data.local.datastore.DataStoreRepository
import javax.inject.Inject

class CountQuotesUseCase @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
) {

    suspend fun saveTotalQuotesLoaded(totalQuotes: Int) {
        dataStoreRepository.putInt(DataStoreKeys.TOTAL_QUOTES_LOADED,totalQuotes)
    }

    fun getTotalQuotesLoaded(): Flow<Int> {
        return dataStoreRepository.getIntAsFlow(DataStoreKeys.TOTAL_QUOTES_LOADED)
    }
}
