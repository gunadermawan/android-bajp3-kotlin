package com.gunder.film.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gunder.film.data.FilmRepository
import com.gunder.film.di.Injection
import com.gunder.film.ui.detail.DetailActivityViewModel
import com.gunder.film.ui.home.HomeViewModel
import com.gunder.film.ui.movies.MoviesViewModel
import com.gunder.film.ui.tvshow.TvShowViewModel

class ViewModelFactory private constructor(private val mFilmsRepository: FilmRepository) :
    ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository(context)).apply {
                    instance = this
                }
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(mFilmsRepository) as T
            }
            modelClass.isAssignableFrom(FavoriteViewModel::class.java) -> {
                FavoriteViewModel(mFilmsRepository) as T
            }
            modelClass.isAssignableFrom(DetailActivityViewModel::class.java) -> {
                DetailActivityViewModel(mFilmsRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }
}