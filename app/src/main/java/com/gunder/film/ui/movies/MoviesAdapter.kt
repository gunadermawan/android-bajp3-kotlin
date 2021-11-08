package com.gunder.film.ui.movies

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gunder.film.BuildConfig
import com.gunder.film.data.source.local.entity.ListEntity
import com.gunder.film.databinding.FilmItemBinding
import com.gunder.film.ui.detail.DetailActivity

class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.ContentViewHolder>() {
    private var listMovies = ArrayList<ListEntity>()

    fun setData(movies: List<ListEntity>) {
        listMovies.clear()
        listMovies.addAll(movies)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentViewHolder {
        val itemsMovies =
            FilmItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ContentViewHolder(itemsMovies)
    }

    override fun onBindViewHolder(holder: ContentViewHolder, position: Int) {
        holder.bind(listMovies[position])
    }

    override fun getItemCount(): Int = listMovies.size

    inner class ContentViewHolder(private val binding: FilmItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movies: ListEntity) {
            binding.tvTitle.text = movies.title
            Glide.with(itemView.context)
                .load(BuildConfig.IMAGES + "/${movies.images}")
                .into(binding.imageItem)

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_DATA, movies)
                intent.putExtra(DetailActivity.EXTRA_TYPE, "movies")
                itemView.context.startActivity(intent)
            }
        }
    }
}