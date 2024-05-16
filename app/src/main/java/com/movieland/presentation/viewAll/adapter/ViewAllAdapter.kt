package com.movieland.presentation.viewAll.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.movieland.data.model.Movie
import com.movieland.databinding.LayoutMovieBinding

class ViewAllAdapter(private val itemClick: (Movie) -> Unit) :
    PagingDataAdapter<Movie, ViewAllAdapter.ItemMovieViewHolder>(MOVIE_COMPARATOR) {
    companion object {
        private val MOVIE_COMPARATOR =
            object : DiffUtil.ItemCallback<Movie>() {
                override fun areItemsTheSame(
                    oldItem: Movie,
                    newItem: Movie,
                ): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(
                    oldItem: Movie,
                    newItem: Movie,
                ): Boolean {
                    return oldItem.hashCode() == newItem.hashCode()
                }
            }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ItemMovieViewHolder {
        val binding =
            LayoutMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            )
        return ItemMovieViewHolder(binding, itemClick)
    }

    override fun onBindViewHolder(
        holder: ItemMovieViewHolder,
        position: Int,
    ) {
        val movie = getItem(position)
        if (movie != null) {
            holder.bindView(movie)
        }
    }

    class ItemMovieViewHolder(
        private val binding: LayoutMovieBinding,
        val itemClick: (Movie) -> Unit,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindView(item: Movie) {
            with(item) {
                binding.ivMovieImage.load("https://image.tmdb.org/t/p/w500${item.image}") {
                    crossfade(true)
                }
                itemView.setOnClickListener { itemClick(this) }
            }
        }
    }
}