package com.movieland.presentation.myList.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.movieland.data.model.MyList
import com.movieland.databinding.ItemMyListBinding
import com.movieland.presentation.home.adapter.OnItemClickedListener

class MyListAdapter(
    private val listener: OnItemClickedListener<MyList>,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val dataDiffer =
        AsyncListDiffer(
            this,
            object : DiffUtil.ItemCallback<MyList>() {
                override fun areItemsTheSame(
                    oldItem: MyList,
                    newItem: MyList,
                ): Boolean {
                    return oldItem.movieId == newItem.movieId
                }

                override fun areContentsTheSame(
                    oldItem: MyList,
                    newItem: MyList,
                ): Boolean {
                    return oldItem.hashCode() == newItem.hashCode()
                }
            },
        )

    fun submitData(data: List<MyList>) {
        dataDiffer.submitList(data)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): RecyclerView.ViewHolder {
        return if (listener != null) {
            MyListViewHolder(
                ItemMyListBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false,
                ),
                listener,
            )
        } else {
            MyListViewHolder(
                ItemMyListBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false,
                ),
                listener,
            )
        }
    }

    override fun getItemCount(): Int = dataDiffer.currentList.size

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
    ) {
        (holder as ViewHolderBinder<MyList>).bind(dataDiffer.currentList[position])
    }
}

class MyListViewHolder(
    private val binding: ItemMyListBinding,
    private val listener: OnItemClickedListener<MyList>,
) : RecyclerView.ViewHolder(binding.root), ViewHolderBinder<MyList> {
    override fun bind(item: MyList) {
        setCartData(item)
        setClickListeners(item)
    }

    private fun setCartData(item: MyList) {
        with(binding) {
            binding.ivMovieImage.load(item.movieImage) {
                crossfade(true)
            }
        }
    }

    private fun setClickListeners(item: MyList) {
//        with(binding) {
//            ivRemoveFavorite.setOnClickListener { favoriteListener?.onRemoveFavoriteClicked(item) }
//        }
    }
}


interface ViewHolderBinder<T> {
    fun bind(item: T)
}