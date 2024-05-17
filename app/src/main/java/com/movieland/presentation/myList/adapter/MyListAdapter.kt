package com.movieland.presentation.myList.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.catnip.kokomputer.core.ViewHolderBinder
import com.movieland.data.model.Movie
import com.movieland.data.model.MyList
import com.movieland.databinding.ItemMyListBinding
import com.movieland.databinding.LayoutMovieBinding

class MyListAdapter(
    private val itemClick: (Movie) -> Unit
) : RecyclerView.Adapter<MyListAdapter.MyListViewHolder>() {
    private val dataDiffer =
        AsyncListDiffer(
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
        dataDiffer.submitList(data)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): MyListViewHolder {
        val binding =
            LayoutMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            )
        return MyListViewHolder(binding, itemClick)

    }


    override fun getItemCount(): Int = dataDiffer.currentList.size

    override fun onBindViewHolder(holder: MyListViewHolder, position: Int) {
        holder.bind(dataDiffer.currentList[position])
    }


    class MyListViewHolder(
        private val binding: LayoutMovieBinding,
        val itemClick: (Movie) -> Unit,
    ) : RecyclerView.ViewHolder(binding.root), ViewHolderBinder<Movie> {
        override fun bind(item: Movie) {
            setMyListData(item)
        }

        private fun setMyListData(item: Movie) {
            with(binding) {
                binding.ivMovieImage.load("https://image.tmdb.org/t/p/w500/${item.image}") {
                    crossfade(true)
                }
                itemView.setOnClickListener { itemClick(item) }
            }
        }

    }

    interface MyListListener {
        fun onRemoveMyListClicked(item: MyList)
    }
}