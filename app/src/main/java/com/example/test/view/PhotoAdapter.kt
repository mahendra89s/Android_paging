package com.example.test.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.test.databinding.ItemPhotosBinding
import com.example.test.model.Photo

class PhotoAdapter(
    val reachedLastItem : (String) -> Unit
) : RecyclerView.Adapter<PhotoAdapter.ViewHolder>() {
    private val photos : MutableList<Photo> = mutableListOf()

    private var page : Int = 1
    inner class ViewHolder(private val binding: ItemPhotosBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(position: Int,context : Context){
            binding.desc.text = photos[position].title
            Glide.with(context).load(photos[position].url).into(binding.image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemPhotosBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun getItemCount(): Int {
        return photos.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position,holder.itemView.context)
        if(position == photos.size-1){
            page += 1
            reachedLastItem(page.toString())
        }
    }

    fun setData(list : List<Photo>){
        photos.addAll(list)
        notifyDataSetChanged()
    }

    val diffCallBack = object : DiffUtil.ItemCallback<Photo>(){
        override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return oldItem == newItem
        }

    }
}

