package com.gunder.film.di

import android.content.Context
import com.gunder.film.data.FilmRepository
import com.gunder.film.data.source.local.LocalDataSource
import com.gunder.film.data.source.local.room.FilmDatabase
import com.gunder.film.data.source.remote.RemoteDataSource
import com.gunder.film.utils.AppExecutors

object Injection {
    fun provideRepository(context: Context): FilmRepository {

        val database = FilmDatabase.getInstance(context)
        val remoteDataSource = RemoteDataSource.getInstance()
        val localDataSource = LocalDataSource.getInstance(database.filmDao())
        val appExecutors = AppExecutors()

        return FilmRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}