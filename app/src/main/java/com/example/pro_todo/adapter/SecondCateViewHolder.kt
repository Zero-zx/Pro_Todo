package com.example.pro_todo.adapter

import android.content.res.Resources
import androidx.recyclerview.widget.RecyclerView
import com.example.pro_todo.databinding.TagViewBinding
import com.example.pro_todo.model.Cate

class SecondCateViewHolder(private val binding: TagViewBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(cate: Cate){
        binding.ivIcon.setImageResource(cate.icon)
    }
}