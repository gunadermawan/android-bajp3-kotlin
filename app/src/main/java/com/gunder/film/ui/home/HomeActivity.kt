package com.gunder.film.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.StringRes
import com.google.android.material.tabs.TabLayoutMediator
import com.gunder.film.R
import com.gunder.film.databinding.ActivityHomeBinding
import com.synnapps.carouselview.ImageListener

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.movies,
            R.string.tv_show
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.carauselView.pageCount = carouselImg.size
        binding.carauselView.setImageListener(imgListener)

        val sectionsPagerAdapter = SectionsPagerAdapter(this)
        binding.viewPager.adapter = sectionsPagerAdapter
        TabLayoutMediator(binding.tabs, binding.viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()

        supportActionBar?.elevation = 0f
        supportActionBar?.title = ""
    }

    private val carouselImg = intArrayOf(
        R.drawable.poster_gotham,
        R.drawable.poster_flash,
        R.drawable.poster_bohemian,
        R.drawable.poster_cold_persuit,
        R.drawable.poster_flash,
        R.drawable.poster_arrow,
        R.drawable.poster_gotham,
        R.drawable.poster_alita,
        R.drawable.poster_doom_patrol,
        R.drawable.poster_cold_persuit
    )
    private val imgListener = ImageListener { position, imageView ->
        imageView.setImageResource(carouselImg[position])
    }
}