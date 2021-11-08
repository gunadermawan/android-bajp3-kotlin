package com.gunder.film.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gunder.film.data.source.local.entity.DetailEntity
import com.gunder.film.data.source.local.entity.ListEntity
import com.gunder.film.data.source.remote.RemoteDataSource
import com.gunder.film.data.source.remote.response.DetailResponse
import com.gunder.film.data.source.remote.response.ListResponse

class FakeFilmRepository(private val remoteDataSource: RemoteDataSource) : FilmDataSource {
    override fun getAllMovies(): LiveData<List<ListEntity>> {
        val movieResult = MutableLiveData<List<ListEntity>>()
        remoteDataSource.getAllMovies(object : RemoteDataSource.LoadAllMoviesCallback {
            override fun onAllMoviesReceived(moviesResponses: List<ListResponse>) {
                val listItem = ArrayList<ListEntity>()
                for (response in moviesResponses) {
                    val item = ListEntity(
                        response.id,
                        response.title,
                        response.name,
                        response.backdropPath,
                        response.posterPath,
                        response.overview,
                        response.release_date
                    )
                    listItem.add(item)
                }
                movieResult.postValue(listItem)
            }

        })
        return movieResult
    }

    override fun getAllTvShow(): LiveData<List<ListEntity>> {
        val tvShowResult = MutableLiveData<List<ListEntity>>()
        remoteDataSource.getAllTvShow(object : RemoteDataSource.LoadAllTvCallback {
            override fun onAllTvShowReceived(tvShowReponse: List<ListResponse>) {
                val listItem = ArrayList<ListEntity>()
                for (response in tvShowReponse) {
                    val item = ListEntity(
                        response.id,
                        response.title,
                        response.name,
                        response.backdropPath,
                        response.posterPath,
                        response.overview,
                        response.release_date
                    )
                    listItem.add(item)
                }
                tvShowResult.postValue(listItem)
            }
        })
        return tvShowResult
    }

    override fun getDetailMovies(id: Int): LiveData<DetailEntity> {
        val detalMovieResult = MutableLiveData<DetailEntity>()
        remoteDataSource.getDetailMovies(id, object : RemoteDataSource.LoadDetailMovieCallback {
            override fun onDetailMovieReceived(movieDetailResponse: DetailResponse) {
                val detailEntity = DetailEntity(
                    movieDetailResponse.id,
                    movieDetailResponse.title,
                    movieDetailResponse.name,
                    movieDetailResponse.backdropPath,
                    movieDetailResponse.posterPath,
                    movieDetailResponse.overview,
                    movieDetailResponse.release_date
                )
                detalMovieResult.postValue(detailEntity)
            }

        })
        return detalMovieResult
    }

    override fun getDetailTvShow(id: Int): LiveData<DetailEntity> {
        val detailResponse = MutableLiveData<DetailEntity>()
        remoteDataSource.getDetailTvShow(id, object : RemoteDataSource.LoadDetailTvShowCallback {
            override fun onDetailTvShowReceived(tvShowDetailResponse: DetailResponse) {
                val detailEntity = DetailEntity(
                    tvShowDetailResponse.id,
                    tvShowDetailResponse.title,
                    tvShowDetailResponse.name,
                    tvShowDetailResponse.backdropPath,
                    tvShowDetailResponse.posterPath,
                    tvShowDetailResponse.overview,
                    tvShowDetailResponse.release_date
                )
                detailResponse.postValue(detailEntity)
            }

        })
        return detailResponse
    }
}