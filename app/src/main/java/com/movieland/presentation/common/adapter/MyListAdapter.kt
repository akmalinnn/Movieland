package com.movieland.presentation.common.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.catnip.kokomputer.core.ViewHolderBinder
import com.movieland.data.model.MyList
import com.movieland.databinding.ItemMyListBinding

class MyListAdapter(
    private val mylistListener: MyListListener? = null,
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
        return if (mylistListener != null) {
            MyListViewHolder(
                ItemMyListBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false,
                ),
                mylistListener,
            )
        } else {
            MyListViewHolder(
                ItemMyListBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false,
                ),
                mylistListener,
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
    private val myListListener: MyListListener?
) : RecyclerView.ViewHolder(binding.root), ViewHolderBinder<MyList> {
    override fun bind(item: MyList) {
        setMyListData(item)
    }

    private fun setMyListData(item: MyList) {
        with(binding){
            binding.ivMovieImage.load(item.movieImage){
                crossfade(true)
            }
        }
    }

}

interface MyListListener {
    fun onRemoveMyListClicked(item: MyList)
}
