package com.gunder.film.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import com.gunder.film.data.source.local.entity.ListEntity

interface Dao {
    @androidx.room.Query("SELECT * FROM listentities WHERE type = 'movies'")
    fun getMovies(): DataSource.Factory<Int, ListEntity>

    @androidx.room.Query("SELECT * FROM listentities WHERE type = 'tv'")
    fun getTvShow(): DataSource.Factory<Int, ListEntity>

    @androidx.room.Query("SELECT * FROM listentities WHERE favorited = 1")
    fun getFavorite(): DataSource.Factory<Int, ListEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFilm(films: List<ListEntity>)

    @Update
    fun updateFilm(films: ListEntity)

    @androidx.room.Query("SELECT * FROM detailentities WHERE id = :id")
    fun getDetailById(id: Int): LiveData<DetailEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDetailFilm(films: DetailEntity)
}