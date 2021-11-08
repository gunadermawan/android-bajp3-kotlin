package com.gunder.film.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.gunder.film.data.source.FilmRepository
import com.gunder.film.data.source.local.entity.ListEntity

class MoviesViewModel(private val filmRepository: FilmRepository) : ViewModel() {

    fun getMovies(): LiveData<List<ListEntity>> = filmRepository.getAllMovies()
}