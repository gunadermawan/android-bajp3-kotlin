package com.gunder.film.data.source

import androidx.lifecycle.LiveData
import com.gunder.film.data.source.local.entity.DetailEntity
import com.gunder.film.data.source.local.entity.ListEntity

interface FilmDataSource {
    fun getAllMovies(): LiveData<List<ListEntity>>

    fun getAllTvShow(): LiveData<List<ListEntity>>

    fun getDetailMovies(id: Int): LiveData<DetailEntity>

    fun getDetailTvShow(id: Int): LiveData<DetailEntity>
}