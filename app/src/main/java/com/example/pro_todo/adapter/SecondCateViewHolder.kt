package com.example.pro_todo.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.pro_todo.databinding.TagViewBinding
import com.example.pro_todo.model.Category

class SecondCateViewHolder(private val binding: TagViewBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(category: Category, onClick: (Category) -> Unit){
        binding.ivIcon.setImageResource(category.icon)
        binding.tagView.setOnClickListener{
            onClick(category)

        }
    }
}