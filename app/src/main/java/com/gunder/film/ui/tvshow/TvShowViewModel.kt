package com.gunder.film.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.gunder.film.data.source.FilmRepository
import com.gunder.film.data.source.local.entity.ListEntity


class TvShowViewModel(private val filmRepository: FilmRepository) : ViewModel() {
    fun getTvShow(): LiveData<List<ListEntity>> = filmRepository.getAllTvShow()
}