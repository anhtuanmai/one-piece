package demo.at.ram.data.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import demo.at.ram.data.BuildConfig
import demo.at.ram.data.source.local.AppDatabase
import demo.at.ram.data.source.local.dao.CharacterDao
import demo.at.ram.data.source.local.dao.FruitDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "one_piece_database"
        )
            .fallbackToDestructiveMigrationOnDowngrade(false)
            .build()
    }

    @Provides
    fun provideCharacterDao(database: AppDatabase): CharacterDao {
        return database.characterDao()
    }

    @Provides
    fun provideFruitDao(database: AppDatabase): FruitDao {
        return database.fruitDao()
    }
}