package com.gunder.film.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.gunder.film.data.FilmRepository
import com.gunder.film.data.source.local.entity.ListEntity
import com.gunder.film.vo.Resource

class HomeViewModel(private val filmRepository: FilmRepository) : ViewModel() {

    fun getMovies(): LiveData<Resource<PagedList<ListEntity>>> = filmRepository.getAllMovies()

    fun getTvShow(): LiveData<Resource<PagedList<ListEntity>>> = filmRepository.getAllTvShow()
}