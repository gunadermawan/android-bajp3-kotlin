package com.gunder.film.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.gunder.film.data.FilmRepository
import com.gunder.film.data.source.local.entity.DetailEntity
import com.gunder.film.data.source.local.entity.ListEntity
import com.gunder.film.utils.DataDummy
import com.gunder.film.vo.Resource
import junit.framework.TestCase.assertEquals
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailActivityViewModelTest {
    private lateinit var viewModel: DetailActivityViewModel
    private val dummyDataMovies = DataDummy.generateDataDummyMovies()[0]
    private val dummyDataTvShow = DataDummy.generateDataDummyTvShow()[0]
    private val dummyMoviesId = dummyDataMovies.id
    private val dummyTvId = dummyDataTvShow.id

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var filmRepository: FilmRepository

    @Mock
    private lateinit var observer: Observer<Resource<DetailEntity>>

    @Before
    fun setup() {
        viewModel = DetailActivityViewModel(filmRepository)
    }


    @Test
    fun loadMoviesDetail() {
        val dataDummy = Resource.success(DataDummy.generateDataDummyMovies()[0])
        val movies = MutableLiveData<Resource<DetailEntity>>()
        movies.value = dataDummy

        dummyMoviesId?.let { viewModel.setSelectedFilm(it) }

        `when`(dummyMoviesId?.let { filmRepository.getDetailMovies(it) }).thenReturn(movies)
        val result = viewModel.getMovies().value?.data

        dummyMoviesId?.let { Mockito.verify(filmRepository).getDetailMovies(it) }
        Assert.assertNotNull(result)

        assertEquals(dummyDataMovies.id, result?.id)
        assertEquals(dummyDataMovies.title, result?.title)
        assertEquals(dummyDataMovies.name, result?.name)
        assertEquals(dummyDataMovies.images, result?.images)
        assertEquals(dummyDataMovies.poster, result?.poster)
        assertEquals(dummyDataMovies.overview, result?.overview)
        assertEquals(dummyDataMovies.release_date, result?.release_date)

        viewModel.getMovies().observeForever(observer)
        Mockito.verify(observer).onChanged(dataDummy)
    }

    @Test
    fun loadTvShowDetail() {
        val dataDummy = Resource.success(DataDummy.generateDataDummyTvShow()[0])
        val tvShow = MutableLiveData<Resource<DetailEntity>>()
        tvShow.value = dataDummy

        dummyTvId?.let { viewModel.setSelectedFilm(it) }
        `when`(dummyTvId?.let { filmRepository.getDetailTvShow(it) }).thenReturn(tvShow)
        val result = viewModel.getTvShow().value?.data
        dummyTvId?.let { Mockito.verify(filmRepository).getDetailTvShow(it) }

        Assert.assertNotNull(result)
        assertEquals(dummyDataTvShow.id, result?.id)
        assertEquals(dummyDataTvShow.title, result?.title)
        assertEquals(dummyDataTvShow.name, result?.name)
        assertEquals(dummyDataTvShow.poster, result?.poster)
        assertEquals(dummyDataMovies.images, result?.images)
        assertEquals(dummyDataTvShow.overview, result?.overview)
        assertEquals(dummyDataMovies.release_date, result?.release_date)

        viewModel.getTvShow().observeForever(observer)
        Mockito.verify(observer).onChanged(dataDummy)
    }

    @Test
    fun setFavorite() {
        val dummyData = Resource.success(DataDummy.generateDummyMovies()[0].copy(favorited = false))
        val dataItem = MutableLiveData<Resource<ListEntity>>()
        dataItem.value = dummyData
        dummyData.data?.let { Mockito.doNothing().`when`(filmRepository).setFilmFavorite(it, true) }
        dataItem.value!!.data?.let { viewModel.setFavorite(it, true) }
        Mockito.verify(filmRepository).setFilmFavorite(dataItem.value?.data as ListEntity, true)
    }

    @Test
    fun deleteFavorite() {
        val dummyData = Resource.success(DataDummy.generateDummyMovies()[0].copy(favorited = true))
        val dataItem = MutableLiveData<Resource<ListEntity>>()
        dataItem.value = dummyData
        dummyData.data?.let {
            Mockito.doNothing().`when`(filmRepository).setFilmFavorite(it, false)
        }
        dataItem.value!!.data?.let { viewModel.setFavorite(it, false) }
        Mockito.verify(filmRepository).setFilmFavorite(dataItem.value?.data as ListEntity, false)
    }
}