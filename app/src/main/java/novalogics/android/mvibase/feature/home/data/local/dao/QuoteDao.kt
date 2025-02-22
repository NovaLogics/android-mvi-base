package novalogics.android.mvibase.feature.home.data.local.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import novalogics.android.mvibase.feature.home.data.local.entity.QuoteEntity

@Dao
interface QuoteDao {
    @Query("SELECT * FROM quotes")
    fun getAllQuotes(): Flow<List<QuoteEntity>>

    @Query("SELECT * FROM quotes WHERE id = :quoteId")
    suspend fun getQuoteById(quoteId: String): QuoteEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuotes(quotes: List<QuoteEntity>)

    @Delete
    suspend fun deleteQuote(quote: QuoteEntity)

    @Query("DELETE FROM quotes")
    suspend fun clearQuotes()

    @Query("SELECT * FROM quotes ORDER BY date_added DESC LIMIT :limit")
    fun getLastNQuotes(limit: Int): Flow<List<QuoteEntity>>

    @Query("SELECT COUNT(*) FROM quotes")
    suspend fun getQuoteCount(): Int
}
