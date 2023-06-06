package com.js.apps.moviedbapp.di

import com.js.apps.moviedbapp.data.core.ConnectivityHelper
import com.js.apps.moviedbapp.data.dasource.LocalDatasourceInteractor
import com.js.apps.moviedbapp.domain.RemoteDatasource
import com.js.apps.moviedbapp.data.dasource.remotedatasource.services.MediaItemsService
import com.js.apps.moviedbapp.data.repository.implementation.MediaItemsRepositoryImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideRemoteDatasource(): RemoteDatasource {
        return MediaItemsService()
    }

    @Singleton
    @Provides
    fun provideMediaItemRepository(
        remoteDataSource: RemoteDatasource,
        localDataSource: LocalDatasourceInteractor,
        connectivityHelper: ConnectivityHelper
    ): MediaItemsRepositoryImp{ return  MediaItemsRepositoryImp(remoteDataSource,localDataSource,connectivityHelper ) }
}