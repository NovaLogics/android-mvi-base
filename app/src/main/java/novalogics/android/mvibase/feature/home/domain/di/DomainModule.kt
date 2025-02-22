package novalogics.android.mvibase.feature.home.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import novalogics.android.mvibase.core.data.local.datastore.DataStoreRepository
import novalogics.android.mvibase.feature.home.domain.repository.QuoteRepository
import novalogics.android.mvibase.feature.home.domain.usecase.CountQuotesUseCase
import novalogics.android.mvibase.feature.home.domain.usecase.GetQuotesFromDbUseCase
import novalogics.android.mvibase.feature.home.domain.usecase.GetQuotesUseCase
import novalogics.android.mvibase.feature.home.domain.usecase.InsertQuotesToDbUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Provides
    @Singleton
    fun provideGetQuotesUseCase(
        quoteRepository: QuoteRepository
    ): GetQuotesUseCase {
        return GetQuotesUseCase(
            quoteRepository = quoteRepository
        )
    }

    @Provides
    @Singleton
    fun provideGetQuotesFromDbUseCase(
        quoteRepository: QuoteRepository
    ): GetQuotesFromDbUseCase {
        return GetQuotesFromDbUseCase(
            quoteRepository = quoteRepository
        )
    }

    @Provides
    @Singleton
    fun provideInsertQuotesUseCase(
        quoteRepository: QuoteRepository
    ): InsertQuotesToDbUseCase {
        return InsertQuotesToDbUseCase(
            quoteRepository = quoteRepository
        )
    }

    @Provides
    @Singleton
    fun provideCountQuotesUseCase(
        dataStoreRepository: DataStoreRepository
    ): CountQuotesUseCase {
        return CountQuotesUseCase(
            dataStoreRepository = dataStoreRepository
        )
    }

}

