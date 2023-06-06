package com.js.apps.moviedbapp.data.repository.implementation

import com.js.apps.moviedbapp.data.core.ConnectivityHelper
import com.js.apps.moviedbapp.domain.RemoteDatasource
import com.js.apps.moviedbapp.data.dasource.LocalDatasourceInteractor
import com.js.apps.moviedbapp.domain.media.MovieModel
import com.js.apps.moviedbapp.domain.media.SerieModel
import com.js.apps.moviedbapp.domain.MediaItemsRepository
import com.js.apps.moviedbapp.domain.MediaTypes
import com.js.apps.moviedbapp.ui.core.CardItem
import javax.inject.Inject

class MediaItemsRepositoryImp@Inject constructor(
    private val service : RemoteDatasource,
    private val localDatasource: LocalDatasourceInteractor,
    private val connectivityHelper: ConnectivityHelper
): MediaItemsRepository {

    suspend fun discoverContents( type: MediaTypes):List<CardItem>{
       return when(type){
           MediaTypes.SERIE->discoverSeries()
           MediaTypes.MOVIE->discoverMovies()
        }
    }
    override suspend fun discoverMovies():List<MovieModel>{
        if (connectivityHelper.isOnline()){
           val response =  service.discoverMovies()
            if (response.isNotEmpty()){
                 localDatasource.moviesLDS.cleanAndSave(response)
            }
        }
        return localDatasource.moviesLDS.getMoviesFromDBSusp()

//          if (connectivityHelper.isOnline()){
//              try{
//                  val response = service.discoverMovies()
//                  Log.i("here response","${response.code()}")
//                  when(response.code()){
//                      APIStatusCode.SUCCES_CODE.code->{
//                          //save in database
//                          val items = response.body()?.results ?: emptyList()
//                          if(items.isNotEmpty()){
//                              moviesDao.deleteAll()
//                              for( item in items){
//                                  moviesDao.insert(item)
//                              }
//                          }
//                      }
//                  }
//                  return response.body()?.results ?: emptyList()
//              }catch (e:Exception){
//                  e.printStackTrace()
//                  return emptyList()
//              }
//          }else{
//            return getMoviesFromDBSusp()
//          }
      }
      override suspend fun discoverSeries():List<SerieModel>{
          if (connectivityHelper.isOnline()){
              val response =  service.discoverSeries()
              if (response.isNotEmpty()){
                  localDatasource.seriesLDS.cleanAndSave(response)
              }
          }
          return localDatasource.seriesLDS.getSeriesFromDBSusp()
//            if (connectivityHelper.isOnline()){
//                try{
//                    val response = service.discoverSeries()
//                    Log.i("here response","${response.code()}")
//                    when(response.code()){
//                        APIStatusCode.SUCCES_CODE.code->{
//                            //save in database
//                            val items = response.body()?.results ?: emptyList()
//
//                            if(items.isNotEmpty()){
//                                for( item in items){
//                                    seriesDao.insert(item)
//                                }
//                            }
//                        }
//                    }
//                    return response.body()?.results ?: emptyList()
//                }catch (e:Exception){
//                    e.printStackTrace()
//                    return emptyList()
//                }
//            }else{
//                return getSeriesFromDBSusp()
//            }
      }

//    suspend fun mostPopular( type: com.js.apps.moviedbapp.domain.MediaTypes){
//        return when(type){
//            com.js.apps.moviedbapp.domain.MediaTypes.SERIE->getMostPopularSeries()
//            com.js.apps.moviedbapp.domain.MediaTypes.MOVIE->getMostPopularMovies()
//        }
//    }
    override suspend fun getMostPopularMovies():List<MovieModel>{
        if(connectivityHelper.isOnline()){
            val response = service.getMostPopularMovies()
            localDatasource.moviesLDS.cleanAndSave(response)
        }
        return localDatasource.moviesLDS.getMoviesFromDBSusp()
//            try{
//                val response = service.getMostPopularMovies()
//                if( response.isNotEmpty()){
//                    moviesDao.deleteAll()
//                    for(item in response){
//                        moviesDao.insert(item)
//                    }
//                }
//            }catch (e:Exception){
//                e.printStackTrace()
//            }
    }

    override suspend fun getMostPopularSeries():List<SerieModel>{
        if(connectivityHelper.isOnline()){
            val response = service.getMostPopularSeries()
        }
        return localDatasource.seriesLDS.getSeriesFromDBSusp()
//        try{
//            val response = service.getMostPopularSeries()
//            if( response.isNotEmpty()){
//                seriesDao.deleteAll()
//                for(item in response){
//                    seriesDao.insert(item)
//                }
//            }
//        }catch (e:Exception){
//            e.printStackTrace()
//        }
    }

     override suspend fun getNowPlayingMovies():List<MovieModel>{
         if(connectivityHelper.isOnline()){
             val response = service.getMostPopularMovies()
         }
         return localDatasource.moviesLDS.getMoviesFromDBSusp()
//        try{
//            val response = service.getNowPlayingMovies()
//            if( response.isNotEmpty()){
//                moviesDao.deleteAll()
//                for(item in response){
//                    moviesDao.insert(item)
//                }
//            }
//        }catch (e:Exception){
//            e.printStackTrace()
//        }
    }

//    fun getMediaItemsFromDB( ):LiveData<List<Movie>>{
//        return moviesDao.getAll()
//    }

//    fun getSeriesFromDB( ):LiveData<List<Serie>>{
//        return seriesDao.getAll()
//    }
//    suspend fun getMoviesFromDBSusp( ):List<Movie>{
//        return moviesDao.getAllSusp()
//    }
//    suspend fun getSeriesFromDBSusp( ):List<Serie>{
//        return seriesDao.getAllSusp()
//    }


}