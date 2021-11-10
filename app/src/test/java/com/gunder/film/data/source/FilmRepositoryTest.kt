package com.gunder.film.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.gunder.film.data.source.local.LocalDataSource
import com.gunder.film.data.source.local.entity.DetailEntity
import com.gunder.film.data.source.local.entity.ListEntity
import com.gunder.film.data.source.remote.RemoteDataSource
import com.gunder.film.utils.AppExecutors
import com.gunder.film.utils.DataDummy
import com.gunder.film.utils.LiveDataTestUtil
import com.gunder.film.vo.Resource
import com.nhaarman.mockitokotlin2.verify
import com.utils.PagedListUtil
import junit.framework.TestCase.assertEquals
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock

class FilmRepositoryTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocalDataSource::class.java)
    private val appExecutors = mock(AppExecutors::class.java)
    private val filmRepository = FakeFilmRepository(remote, local, appExecutors)
    private val moviesResponse = DataDummy.generateRemoteDummyMovies()
    private val tvShowResponse = DataDummy.generateRemoteDummyTvShow()
    private val dataMovie = DataDummy.generateDataDummyMovies()[0]
    private val dataTvShow = DataDummy.generateDataDummyTvShow()[0]

    @Test
    fun getAllMovies() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, ListEntity>
        Mockito.`when`(local.getMovies()).thenReturn(dataSourceFactory)
        filmRepository.getAllMovies()

        val courseEntities =
            Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyMovies()))
        verify(local).getMovies()

        Assert.assertNotNull(courseEntities)
        assertEquals(moviesResponse.size.toLong(), courseEntities.data?.size?.toLong())
    }

    @Test
    fun getAllTvShow() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, ListEntity>
        Mockito.`when`(local.getTvShow()).thenReturn(dataSourceFactory)
        filmRepository.getAllTvShow()
        val courseEntities =
            Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyTvShow()))
        verify(local).getTvShow()
        Assert.assertNotNull(courseEntities)
        assertEquals(tvShowResponse.size.toLong(), courseEntities.data?.size?.toLong())
    }

    @Test
    fun getFavoriteMovies() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, ListEntity>
        Mockito.`when`(local.getFavorited()).thenReturn(dataSourceFactory)
        filmRepository.getFavorited()

        val courseEntities =
            Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyMovies()))
        verify(local).getFavorited()

        Assert.assertNotNull(courseEntities)
        assertEquals(moviesResponse.size.toLong(), courseEntities.data?.size?.toLong())
    }

    @Test
    fun getFavoriteTvShow() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, ListEntity>
        Mockito.`when`(local.getFavorited()).thenReturn(dataSourceFactory)
        filmRepository.getFavorited()

        val courseEntities =
            Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyTvShow()))
        verify(local).getFavorited()

        Assert.assertNotNull(courseEntities)
        assertEquals(moviesResponse.size.toLong(), courseEntities.data?.size?.toLong())
    }

    @Test
    fun getDetailMovies() {
        val dummyMovie = MutableLiveData<DetailEntity>()
        dummyMovie.value = dataMovie
        Mockito.`when`(dataMovie.id?.let { local.getDetailById(it) }).thenReturn(dummyMovie)

        val moviesEntities =
            LiveDataTestUtil.getValue(filmRepository.getDetailMovies(dataMovie.id!!))
        verify(local).getDetailById(dataMovie.id!!)

        Assert.assertNotNull(moviesEntities)
        assertEquals(dataMovie.id, moviesEntities.data?.id)
    }

    @Test
    fun getDetailTvShow() {
        val dummyTvShow = MutableLiveData<DetailEntity>()
        dummyTvShow.value = dataTvShow
        Mockito.`when`(dataTvShow.id?.let { local.getDetailById(it) }).thenReturn(dummyTvShow)

        val tvEntities = LiveDataTestUtil.getValue(filmRepository.getDetailTvShow(dataTvShow.id!!))
        verify(local).getDetailById(dataTvShow.id!!)

        Assert.assertNotNull(tvEntities)
        assertEquals(dataTvShow.id, tvEntities.data?.id)
    }

    @Test
    fun setFavoriteMovies() {
        val dataDummy = DataDummy.generateDummyMovies()[0].copy(favorited = false)
        Mockito.doNothing().`when`(local).setFilmFavorite(dataDummy, true)
        filmRepository.setFilmFavorite(dataDummy, true)

        verify(local, Mockito.times(1)).setFilmFavorite(dataDummy, true)
    }

    @Test
    fun detFavoriteMovies() {
        val dataDummy = DataDummy.generateDummyMovies()[0].copy(favorited = true)
        Mockito.doNothing().`when`(local).setFilmFavorite(dataDummy, false)
        filmRepository.setFilmFavorite(dataDummy, false)

        verify(local, Mockito.times(1)).setFilmFavorite(dataDummy, false)
    }

    @Test
    fun setFavoriteTvShow() {
        val dataDummy = DataDummy.generateDummyTvShow()[0].copy(favorited = false)
        Mockito.doNothing().`when`(local).setFilmFavorite(dataDummy, true)
        filmRepository.setFilmFavorite(dataDummy, true)

        verify(local, Mockito.times(1)).setFilmFavorite(dataDummy, true)
    }

    @Test
    fun delFavoriteTvShow() {
        val dataDummy = DataDummy.generateDummyTvShow()[0].copy(favorited = true)
        Mockito.doNothing().`when`(local).setFilmFavorite(dataDummy, false)
        filmRepository.setFilmFavorite(dataDummy, false)

        verify(local, Mockito.times(1)).setFilmFavorite(dataDummy, false)
    }
}