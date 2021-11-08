package com.gunder.film.ui.detail

import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.gunder.film.BuildConfig
import com.gunder.film.R
import com.gunder.film.data.source.local.entity.DetailEntity
import com.gunder.film.data.source.local.entity.ListEntity
import com.gunder.film.databinding.ActivityDetailBinding
import com.gunder.film.viewmodel.ViewModelFactory

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var detailActivityViewModel: DetailActivityViewModel

    companion object {
        const val EXTRA_DATA = "extra_data"
        const val EXTRA_TYPE = "extra_type"
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.apply {
            elevation = 0f
            setDisplayHomeAsUpEnabled(true)
        }

        val type = intent.getStringExtra(EXTRA_TYPE)
        val factory = ViewModelFactory.getInstance()
        val film = intent.getParcelableExtra<ListEntity>(EXTRA_DATA) as ListEntity

        showLoading(true)
        detailActivityViewModel =
            ViewModelProvider(this, factory)[DetailActivityViewModel::class.java]
        film.id?.let { detailActivityViewModel.setSelectedFilm(it) }

        if (type == "movies") {
            setActionBarTitle(film.title.toString())
            detailActivityViewModel.getMovies().observe(this, {
                view(it)
                showLoading(false)
            })
        } else {
            setActionBarTitle(film.name.toString())
            detailActivityViewModel.getTvShow().observe(this, {
                view(it)
                showLoading(false)
            })
        }
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.progressBar.visibility = View.VISIBLE
            binding.imagesDetail.visibility = View.GONE
            binding.titleDetail.visibility = View.GONE
            binding.overview.visibility = View.GONE
            binding.text.visibility = View.GONE
        } else {
            binding.progressBar.visibility = View.GONE
            binding.imagesDetail.visibility = View.VISIBLE
            binding.titleDetail.visibility = View.VISIBLE
            binding.overview.visibility = View.VISIBLE
            binding.text.visibility = View.VISIBLE
        }
    }

    private fun setActionBarTitle(title: String) {
        if (supportActionBar != null) {
            this.title = title
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun view(movies: DetailEntity) {
        Glide.with(this)
            .load(BuildConfig.IMAGES + "/${movies.poster}")
            .into(binding.imagesDetail)
        Glide.with(this)
            .load(BuildConfig.IMAGES + "${movies.posterItem}")
            .into(binding.ivPhotoDetail)

        val type = intent.getStringExtra(EXTRA_TYPE)

        val text: String = if (type == "movies") {
            "${movies.title}"
        } else {
            "${movies.name}"
        }

        binding.titleDetail.text = text
        binding.overview.text = movies.overview
        binding.tvRelease.text = movies.release_date
    }
}