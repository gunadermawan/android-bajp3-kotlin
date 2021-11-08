package com.gunder.film.ui.movies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.gunder.film.data.source.FilmRepository
import com.gunder.film.data.source.local.entity.ListEntity
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
class MoviesViewModelTest {
    private lateinit var viewModel: MoviesViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var filmRepository: FilmRepository

    @Mock
    private lateinit var observer: Observer<List<ListEntity>>

    @Before
    fun setup() {
        viewModel = MoviesViewModel(filmRepository)
    }

    @Test
    fun getMovies() {
        val dummyData = DataDummy.generateDummyMovies()
        val data = MutableLiveData<List<ListEntity>>()
        data.value = dummyData

        `when`(filmRepository.getAllMovies()).thenReturn(data)
        val moviesData = viewModel.getMovies().value
        verify(filmRepository).getAllMovies()
        assertNotNull(moviesData)
        assertEquals(2, moviesData?.size)
        viewModel.getMovies().observeForever(observer)
        verify(observer).onChanged(dummyData)
    }
}