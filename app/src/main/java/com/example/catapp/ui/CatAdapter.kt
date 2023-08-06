package com.example.catapp.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.example.catapp.databinding.CatListItemBinding
import com.example.catapp.remote.data.CatModel


class CatAdapter(
    val context: MainActivity
): RecyclerView.Adapter<CatAdapter.CatViewHolder>() {

    private val differCallback = object : DiffUtil.ItemCallback<CatModel>(){
        override fun areItemsTheSame(oldItem: CatModel, newItem: CatModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CatModel, newItem: CatModel): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    inner class CatViewHolder(private val binding: CatListItemBinding): ViewHolder(binding.root) {
        fun bind(item: CatModel) {
            Glide.with(context)
                .load(item.url)
                .override(250, 250)
                .centerCrop()
                .transform(CircleCrop())
                .into(binding.catImageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
        val binding = CatListItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return  CatViewHolder(binding)
    }

    override fun getItemCount(): Int = differ.currentList.size

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

}