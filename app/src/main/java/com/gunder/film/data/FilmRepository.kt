package com.gunder.film.data

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.gunder.film.data.source.local.LocalDataSource
import com.gunder.film.data.source.local.entity.DetailEntity
import com.gunder.film.data.source.local.entity.ListEntity
import com.gunder.film.data.source.remote.ApiResponse
import com.gunder.film.data.source.remote.RemoteDataSource
import com.gunder.film.data.source.remote.response.DetailResponse
import com.gunder.film.data.source.remote.response.ListResponse
import com.gunder.film.utils.AppExecutors
import com.gunder.film.vo.Resource


class FilmRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) :
    FilmDataSource {
    companion object {
        @Volatile
        private var instance: FilmRepository? = null

        fun getInstance(
            remoteData: RemoteDataSource,
            localData: LocalDataSource,
            appExecutors: AppExecutors
        ): FilmRepository =
            instance ?: synchronized(this) {
                instance ?: FilmRepository(remoteData, localData, appExecutors).apply {
                    instance = this
                }
            }
    }

    override fun getAllMovies(): LiveData<Resource<PagedList<ListEntity>>> {
        return object :
            NetworkBoundResource<PagedList<ListEntity>, List<ListResponse>>(appExecutors) {
            public override fun loadFromDB(): LiveData<PagedList<ListEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()

                return LivePagedListBuilder(localDataSource.getMovies(), config).build()
            }

            override fun shouldFetch(data: PagedList<ListEntity>?): Boolean =
                data == null || data.isEmpty()

            public override fun createCall(): LiveData<ApiResponse<List<ListResponse>>> =
                remoteDataSource.getAllMovies()

            public override fun saveCallResult(data: List<ListResponse>) {
                val listItem = ArrayList<ListEntity>()
                for (response in data) {
                    val item = response.id?.let {
                        ListEntity(
                            it,
                            response.title,
                            null,
                            response.posterPath,
                            response.backdropPath,
                            response.overview,
                            false,
                            "movies"
                        )
                    }

                    item?.let { listItem.add(it) }
                }

                localDataSource.insertFilm(listItem)
            }
        }.asLiveData()
    }

    override fun getAllTvShow(): LiveData<Resource<PagedList<ListEntity>>> {
        return object :
            NetworkBoundResource<PagedList<ListEntity>, List<ListResponse>>(appExecutors) {
            public override fun loadFromDB(): LiveData<PagedList<ListEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()

                return LivePagedListBuilder(localDataSource.getTvShow(), config).build()
            }

            override fun shouldFetch(data: PagedList<ListEntity>?): Boolean =
                data == null || data.isEmpty()

            public override fun createCall(): LiveData<ApiResponse<List<ListResponse>>> =
                remoteDataSource.getAllTvShow()

            public override fun saveCallResult(data: List<ListResponse>) {
                val listItem = ArrayList<ListEntity>()
                for (response in data) {
                    val item = response.id?.let {
                        ListEntity(
                            it,
                            null,
                            response.name,
                            response.posterPath,
                            response.backdropPath,
                            response.overview,
                            false,
                            "tv"
                        )
                    }

                    item?.let { listItem.add(it) }
                }

                localDataSource.insertFilm(listItem)
            }
        }.asLiveData()
    }

    override fun getFavorited(): LiveData<PagedList<ListEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getFavorited(), config).build()
    }

    override fun getDetailMovies(id: Int): LiveData<Resource<DetailEntity>> {
        return object : NetworkBoundResource<DetailEntity, DetailResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<DetailEntity> =
                localDataSource.getDetailById(id)

            override fun shouldFetch(data: DetailEntity?): Boolean =
                data == null

            override fun createCall(): LiveData<ApiResponse<DetailResponse>> =
                remoteDataSource.getDetailMovies(id)

            override fun saveCallResult(data: DetailResponse) {
                val detailEntity = DetailEntity(
                    data.id,
                    data.backdropPath,
                    data.title,
                    data.name,
                    data.overview,
                    data.posterPath,
                    data.release_date
                )

                localDataSource.insertDetail(detailEntity)
            }

        }.asLiveData()
    }

    override fun getDetailTvShow(id: Int): LiveData<Resource<DetailEntity>> {
        return object : NetworkBoundResource<DetailEntity, DetailResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<DetailEntity> =
                localDataSource.getDetailById(id)

            override fun shouldFetch(data: DetailEntity?): Boolean =
                data == null

            override fun createCall(): LiveData<ApiResponse<DetailResponse>> =
                remoteDataSource.getDetailTvShow(id)

            override fun saveCallResult(data: DetailResponse) {
                val detailEntity = DetailEntity(
                    data.id,
                    data.backdropPath,
                    data.title,
                    data.name,
                    data.overview,
                    data.posterPath,
                    data.release_date
                )

                localDataSource.insertDetail(detailEntity)
            }

        }.asLiveData()
    }

    override fun setFilmFavorite(film: ListEntity, state: Boolean) =
        appExecutors.diskIO().execute { localDataSource.setFilmFavorite(film, state) }
}