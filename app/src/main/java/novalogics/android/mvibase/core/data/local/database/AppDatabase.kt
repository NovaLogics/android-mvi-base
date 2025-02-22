package novalogics.android.mvibase.core.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import novalogics.android.mvibase.BuildConfig
import novalogics.android.mvibase.feature.home.data.local.converter.ListConverter
import novalogics.android.mvibase.feature.home.data.local.dao.QuoteDao
import novalogics.android.mvibase.feature.home.data.local.entity.QuoteEntity

@Database(
    entities = [QuoteEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(
    ListConverter::class
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun quoteDao(): QuoteDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    BuildConfig.APP_DATABASE_NAME
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
