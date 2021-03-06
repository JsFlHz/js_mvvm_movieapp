package com.js.apps.moviedbapp.model.repository.database.room

import android.content.Context
import androidx.room.Room
import com.js.apps.moviedbapp.model.repository.database.dao.MoviesDao
import com.js.apps.moviedbapp.model.repository.database.dao.SeriesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
//Application scope
@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    @Singleton
    @Provides
    fun provideRoomInstance( @ApplicationContext context:Context) =
         Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "js_movie_catalog_db"
        ).build()

    @Singleton
    @Provides
    fun provideMediaItemsDao(db:AppDatabase):MoviesDao{
        return db.moviesDao()
    }
    @Singleton
    @Provides
    fun provideSeriesDao(db:AppDatabase):SeriesDao{
        return db.seriesDao()
    }
}