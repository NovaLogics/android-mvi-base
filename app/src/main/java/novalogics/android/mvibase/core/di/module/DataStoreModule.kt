package novalogics.android.mvibase.core.di.module

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import novalogics.android.mvibase.BuildConfig
import novalogics.android.mvibase.core.data.local.datastore.DataStoreRepository
import novalogics.android.mvibase.core.data.local.datastore.DataStoreRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Provides
    @Singleton
    fun provideDataStore(
        @ApplicationContext context: Context
    ): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(
            produceFile = {
                context.preferencesDataStoreFile(
                    name = BuildConfig.APP_DATASTORE_FILE_NAME
                )
            }
        )
    }

    @Provides
    @Singleton
    fun provideDataStoreRepository(
        dataStore: DataStore<Preferences>
    ): DataStoreRepository {
        return DataStoreRepositoryImpl(dataStore)
    }
}
