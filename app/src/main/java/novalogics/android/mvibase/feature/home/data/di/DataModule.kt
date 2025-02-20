package novalogics.android.mvibase.feature.home.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import novalogics.android.mvibase.core.di.qualifier.QuoteRetrofit
import novalogics.android.mvibase.feature.home.data.remote.QuoteApiService
import novalogics.android.mvibase.feature.home.data.repository.QuoteRepositoryImpl
import novalogics.android.mvibase.feature.home.domain.repository.QuoteRepository
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Singleton
    @Provides
    fun provideQuoteRepository(
        apiService: QuoteApiService
    ): QuoteRepository = QuoteRepositoryImpl(
        apiService = apiService,
    )

    @Provides
    fun provideQuoteApiService(
        @QuoteRetrofit retrofit: Retrofit
    ): QuoteApiService{
        return retrofit.create(QuoteApiService::class.java)
    }
}
