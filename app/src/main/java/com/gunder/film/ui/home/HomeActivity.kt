package com.gunder.film.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.gunder.film.R
import com.gunder.film.databinding.ActivityHomeBinding
import com.gunder.film.ui.favorite.FavoriteActivity
import com.gunder.film.ui.home.fragment.SectionPageAdapter
import com.synnapps.carouselview.ImageListener

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.elevation = 0f

        val sectionsPagerAdapter = SectionPageAdapter(this)
        val viewPager = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter
        val tabs = binding.tabs
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
        binding.carauselView.pageCount = carouselImg.size
        binding.carauselView.setImageListener(imgListener)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        setMode(item.itemId)
        return super.onOptionsItemSelected(item)
    }

    private fun setMode(selectedMode: Int) {
        when (selectedMode) {
            R.id.favorited -> {
                Intent(this, FavoriteActivity::class.java).also {
                    startActivity(it)
                }
            }
        }
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
    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.movies,
            R.string.tv_show
        )
    }
}