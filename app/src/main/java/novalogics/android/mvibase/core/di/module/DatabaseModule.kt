package novalogics.android.mvibase.core.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import novalogics.android.mvibase.core.data.local.database.AppDatabase
import novalogics.android.mvibase.feature.home.data.local.dao.QuoteDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(context: Context): AppDatabase {
        return AppDatabase.getDatabase(context = context)
    }

    @Provides
    fun provideQuoteDao(database: AppDatabase): QuoteDao {
        return database.quoteDao()
    }
}
