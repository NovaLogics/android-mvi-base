package novalogics.android.mvibase.feature.home.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import novalogics.android.mvibase.feature.home.domain.repository.QuoteRepository
import novalogics.android.mvibase.feature.home.domain.usecase.GetQuotesUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Provides
    @Singleton
    fun provideGetQuotesUseCase(
        quoteRepository: QuoteRepository
    ): GetQuotesUseCase =
        GetQuotesUseCase(quoteRepository = quoteRepository)

}

