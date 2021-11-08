package com.gunder.film.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.gunder.film.data.FilmRepository
import com.gunder.film.data.source.local.entity.ListEntity


class FavoriteViewModel(private val filmRepository: FilmRepository) : ViewModel() {

    fun getFavorite(): LiveData<PagedList<ListEntity>> = filmRepository.getFavorited()

    fun setFavorited(filmsEntity: ListEntity) {
        val newState = !filmsEntity.favorited
        filmRepository.setFilmFavorite(filmsEntity, newState)
    }
}