package com.gunder.film.di

import com.gunder.film.data.source.FilmRepository
import com.gunder.film.data.source.remote.RemoteDataSource

object Injection {
    fun provideRepository(): FilmRepository {

        val remoteDataSource = RemoteDataSource.getInstance()

        return FilmRepository.getInstance(remoteDataSource)
    }
}