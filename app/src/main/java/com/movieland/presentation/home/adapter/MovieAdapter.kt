package com.movieland.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.movieland.data.model.Movie
import com.movieland.databinding.LayoutMovieBinding

class MovieAdapter(
    private val listener: OnItemClickedListener<Movie>,
) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    private val data = mutableListOf<Movie>()

    private val asyncDataDiffer =
        AsyncListDiffer<Movie>(
            this,
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
            },
        )

    fun submitData(data: List<Movie>) {
        asyncDataDiffer.submitList(data)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): MovieViewHolder {
        return MovieViewHolder(
            LayoutMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            ),
            listener,
        )
    }

    override fun getItemCount(): Int = asyncDataDiffer.currentList.size

    override fun onBindViewHolder(
        holder: MovieViewHolder,
        position: Int,
    ) {
        holder.bind(asyncDataDiffer.currentList[position])
    }

    class MovieViewHolder(
        private val binding: LayoutMovieBinding,
        private val listener: OnItemClickedListener<Movie>,
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Movie) {
            binding.ivMovieImage.load("https://image.tmdb.org/t/p/w500/${item.image}") {
                crossfade(true)
            }
            itemView.setOnClickListener {
                listener.onItemClicked(item)
            }
        }
    }

}

interface OnItemClickedListener<T> {
    fun onItemClicked(item: T)
}