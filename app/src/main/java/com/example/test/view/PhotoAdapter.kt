package com.example.test.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.test.databinding.ItemPhotosBinding
import com.example.test.model.Photo

class PhotoAdapter : PagingDataAdapter<Photo,PhotoAdapter.ViewHolder>(diffCallBack) {

    inner class ViewHolder(private val binding: ItemPhotosBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(position: Int,context : Context){
            binding.desc.text = getItem(position)?.title
            Glide.with(context).load(getItem(position)?.url).into(binding.image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemPhotosBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position,holder.itemView.context)
    }

    companion object{
        val diffCallBack = object : DiffUtil.ItemCallback<Photo>(){
            override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
                return oldItem == newItem
            }

        }
    }

}

