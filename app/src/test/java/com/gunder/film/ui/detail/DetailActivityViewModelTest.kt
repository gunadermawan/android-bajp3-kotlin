package com.gunder.film.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.gunder.film.data.source.FilmRepository
import com.gunder.film.data.source.local.entity.DetailEntity
import com.gunder.film.utils.DataDummy
import com.nhaarman.mockitokotlin2.verify
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailActivityViewModelTest {
    private lateinit var viewModel: DetailActivityViewModel
    private val dummyMovies = DataDummy.generateDataDummyMovies()[0]
    private val dummyTvShow = DataDummy.generateDataDummyTvShow()[0]
    private val dummyMoviesId = dummyMovies.id
    private val dummyTvShowId = dummyTvShow.id

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var filmRepository: FilmRepository

    @Mock
    private lateinit var observer: Observer<DetailEntity>

    @Before
    fun setup() {
        viewModel = DetailActivityViewModel(filmRepository)
    }

    @Test
    fun loadMoviesDetail() {
        val movies = MutableLiveData<DetailEntity>()
        movies.value = dummyMovies
        if (dummyMoviesId != null) {
            viewModel.setSelectedFilm(dummyMoviesId)
        }
        `when`(filmRepository.getDetailMovies(dummyMoviesId!!)).thenReturn(movies)
        val result = viewModel.getMovies().value as DetailEntity

        verify(filmRepository).getDetailMovies(dummyMoviesId)
        assertNotNull(result)

        assertEquals(dummyMovies.id, result.id)
        assertEquals(dummyMovies.title, result.title)
        assertEquals(dummyMovies.name, result.name)
        assertEquals(dummyMovies.poster, result.poster)
        assertEquals(dummyMovies.posterItem, result.posterItem)
        assertEquals(dummyMovies.overview, result.overview)
        assertEquals(dummyMovies.release_date, result.release_date)

        viewModel.getMovies().observeForever(observer)
        verify(observer).onChanged(dummyMovies)
    }

    @Test
    fun loadTvShowDetail() {
        val tvShow = MutableLiveData<DetailEntity>()
        tvShow.value = dummyTvShow
        if (dummyTvShowId != null) {
            viewModel.setSelectedFilm(dummyTvShowId)
        }
        `when`(filmRepository.getDetailTvShow(dummyTvShowId!!)).thenReturn(tvShow)
        val result = viewModel.getTvShow().value as DetailEntity
        verify(filmRepository).getDetailTvShow(dummyTvShowId)
        assertNotNull(result)
        assertEquals(dummyTvShow.id, result.id)
        assertEquals(dummyTvShow.title, result.title)
        assertEquals(dummyTvShow.name, result.name)
        assertEquals(dummyTvShow.poster, result.poster)
        assertEquals(dummyTvShow.posterItem, result.posterItem)
        assertEquals(dummyTvShow.overview, result.overview)
        assertEquals(dummyTvShow.release_date, result.release_date)
        viewModel.getTvShow().observeForever(observer)
        verify(observer).onChanged(dummyTvShow)
    }
}