package com.gunder.film.ui.tvshow

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gunder.film.BuildConfig
import com.gunder.film.data.source.local.entity.ListEntity
import com.gunder.film.databinding.FilmItemBinding
import com.gunder.film.ui.detail.DetailActivity

class TvShowAdapter : RecyclerView.Adapter<TvShowAdapter.ContentViewHolder>() {
    private val listTvShow = ArrayList<ListEntity>()

    fun setTvShow(tvShow: List<ListEntity>) {
        listTvShow.clear()
        listTvShow.addAll(tvShow)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentViewHolder {
        val itemsTvShow =
            FilmItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ContentViewHolder(itemsTvShow)
    }

    override fun onBindViewHolder(holder: ContentViewHolder, position: Int) {
        holder.bind(listTvShow[position])
    }

    override fun getItemCount(): Int = listTvShow.size

    inner class ContentViewHolder(private val binding: FilmItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShow: ListEntity) {
            binding.tvTitle.text = tvShow.name
            Glide.with(itemView.context)
                .load(BuildConfig.IMAGES + "/${tvShow.images}")
                .into(binding.imageItem)

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_DATA, tvShow)
                intent.putExtra(DetailActivity.EXTRA_TYPE, "tv show")
                itemView.context.startActivity(intent)
            }
        }
    }
}